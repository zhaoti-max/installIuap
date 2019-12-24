package installIuap.ui.tree;

import installIuap.common.base.IUapTreeDataBean;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.trade.pub.IVOTreeDataByCode;
import nc.ui.uap.rbac.comp.CheckBoxTree;
import nc.ui.uap.rbac.comp.CheckTreeNode;
import nc.ui.uap.rbac.comp.selmode.DigInAndToRootMode;
import nc.ui.uap.rbac.comp.selmode.INodeCheckMode;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * IUAP勾选框工具
 * 
 * @author zhaoti
 * 
 */
public class IUapCheckBoxTreeTool {
	private UIPanel mainPane = null;

	private CheckBoxTree checkTree = null;

	protected INodeCheckMode checkMode = null;

	private IVOTreeDataByCode voTreeDataByCode = null;

	private TreeModelCreatorByCodeRule codeTreeModalCreator = null;
	private String rootName = null;

	public IUapCheckBoxTreeTool(IVOTreeDataByCode voTreeData, String rootName) {
		this.voTreeDataByCode = voTreeData;
		this.rootName = rootName;
	}

	protected INodeCheckMode initCheckMode() {
		return new DigInAndToRootMode();
	}

	public void setCheckBoxTreeModal() {
		this.checkMode = initCheckMode();
		try {
			DefaultTreeModel model = null;
			if (this.codeTreeModalCreator == null) {
				this.codeTreeModalCreator = new TreeModelCreatorByCodeRule(this.voTreeDataByCode);
			}
			model = this.codeTreeModalCreator.createTree();

			if (model == null) {
				model = new DefaultTreeModel(new CheckTreeNode("ROOT"));
				getCheckTree().setRootVisible(false);
			} else {
				getCheckTree().setRootVisible(true);
			}
			getCheckTree().setModel(model);
			getCheckTree().setCheckMode(this.checkMode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public UIPanel getCheckTreePanel() {
		if (this.mainPane == null) {
			this.mainPane = new UIPanel();

			this.mainPane.setLayout(new BorderLayout());

			UIScrollPane scrollPane = new UIScrollPane();
			scrollPane.setViewportView(getCheckTree());
			this.mainPane.add(scrollPane);
		}
		return this.mainPane;
	}

	public CheckBoxTree getCheckTree() {
		if (this.checkTree == null) {
			CheckTreeNode tmp = new CheckTreeNode();
			DefaultTreeModel model = new DefaultTreeModel(tmp);
			this.checkTree = new CheckBoxTree(model);
			this.checkTree.setRootVisible(true);
		}

		return this.checkTree;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getCheckedLeafNode() {
		CheckTreeNode[] node = getCheckTree().getTreeCheckedSet().getSelectedNodes();
		IUapTreeDataBean nodeBean = null;
		Object obj = null;
		List codeLst = new ArrayList();

		for (int idx = 0; idx < node.length; idx++) {
			if (node[idx].isLeaf()) {
				obj = node[idx].getUserObject();
				if ((obj instanceof IUapTreeDataBean)) {
					nodeBean = (IUapTreeDataBean) obj;
					codeLst.add(nodeBean.getArea_code());
				}
			}
		}

		return codeLst;
	}

	@SuppressWarnings("rawtypes")
	class TreeModelCreatorByCodeRule {
		private HashMap obj_code_map = new HashMap();
		private HashMap code_obj_map = new HashMap();
		private int[] codeSection = null;

		private IVOTreeDataByCode m_treedata = null;

		TreeModelCreatorByCodeRule(IVOTreeDataByCode voTreeDataByCode) {
			this.m_treedata = voTreeDataByCode;
		}

		@SuppressWarnings("unchecked")
		private DefaultTreeModel createTree(DefaultMutableTreeNode[] treenods, DefaultMutableTreeNode root) {
			this.code_obj_map.put("", root);
			for (int i = 0; i < treenods.length; i++) {
				String parentCode = getParentCode(this.obj_code_map.get(treenods[i]).toString());
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) this.code_obj_map.get(parentCode);

				if (parentNode != null) {
					parentNode.insert(treenods[i], parentNode.getChildCount());
				}
			}

			DefaultTreeModel treemodel = new DefaultTreeModel(root);
			return treemodel;
		}

		public DefaultTreeModel createTree() {
			return createTree(IUapCheckBoxTreeTool.this.voTreeDataByCode);
		}

		public DefaultTreeModel createTree(IVOTreeDataByCode voTreeDataByCode) {
			if (voTreeDataByCode == null)
				return null;
			return createTree(voTreeDataByCode.getTreeVO());
		}

		@SuppressWarnings("unchecked")
		public DefaultTreeModel createTree(Object[] objs) {
			try {
				if ((objs != null) && (objs.length > 0)) {
					String codeRule = this.m_treedata.getCodeRule();
					int size = objs.length;
					DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[size];
					parseCodeRule(codeRule);
					for (int i = 0; i < size; i++) {
						DefaultMutableTreeNode node = createTreeNode(objs[i]);
						nodes[i] = node;
						CircularlyAccessibleValueObject vo = (CircularlyAccessibleValueObject) objs[i];

						Object codeValue = vo.getAttributeValue(this.m_treedata.getCodeFieldName());
						this.obj_code_map.put(node, codeValue);
						this.code_obj_map.put(codeValue, node);
					}
					return createTree(nodes, new CheckTreeNode(IUapCheckBoxTreeTool.this.rootName));
				}
			} catch (Exception e) {
				System.out.println("数据有错,无法构建成树!");
			}
			return null;
		}

		public DefaultMutableTreeNode createTreeNode(Object obj) {
			CheckTreeNode node = new CheckTreeNode(obj);
			String[] showfieldnames = (String[]) null;
			showfieldnames = getShowField(this.m_treedata.getShowFieldName());
			if (showfieldnames != null) {
				CircularlyAccessibleValueObject vo = (CircularlyAccessibleValueObject) obj;
				String showText = "";
				for (int i = 0; i < showfieldnames.length; i++) {
					showText = showText + " " + vo.getAttributeValue(showfieldnames[i]);
				}

				node.setShowText(showText);
			}
			return node;
		}

		private String[] getShowField(String value) {
			StringTokenizer st = new StringTokenizer(value, " ,.+/\\", false);
			int count = st.countTokens();
			String[] showvalues = new String[count];
			int index = 0;
			try {
				while (st.hasMoreTokens())
					showvalues[(index++)] = st.nextToken().trim();
			} catch (Exception e) {
				System.out.println("解析显示字段出错！");
				e.printStackTrace();
			}

			return showvalues;
		}

		private void parseCodeRule(String codeRule) {
			StringTokenizer st = new StringTokenizer(codeRule, " ,./\\", false);
			int count = st.countTokens();

			this.codeSection = new int[count];
			int index = 0;
			try {
				while (st.hasMoreTokens())
					this.codeSection[(index++)] = Integer.parseInt(st.nextToken().trim());
			} catch (Exception e) {
				System.out.println("解析编码规则出错");
				this.codeSection = null;
			}
		}

		private String getParentCode(String childCode) {
			String parentCode = "";
			if (this.codeSection != null) {
				int index = 0;
				int sublength = 0;
				int length = childCode.length();
				while ((length > 0) && (index < this.codeSection.length)) {
					length -= this.codeSection[index];
					if (length > 0) {
						sublength += this.codeSection[index];
					}
					index++;
				}
				if (sublength > 0)
					parentCode = childCode.substring(0, sublength);
			}
			return parentCode;
		}
	}
}