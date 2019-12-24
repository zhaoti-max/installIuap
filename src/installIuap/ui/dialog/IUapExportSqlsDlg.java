package installIuap.ui.dialog;

import installIuap.common.base.IUapTreeData;
import installIuap.common.dao.IUapDao;
import installIuap.common.helper.IUapScriptHelper;
import installIuap.common.tools.IUapDeleteFile;
import installIuap.common.tools.IUapFileOperator;
import installIuap.common.tools.IUapUtil;
import installIuap.common.tools.IUapWaitingDlg;
import installIuap.common.tools.IUapXmlUtil;
import installIuap.common.xmlopt.IUapXmlCtrl;
import installIuap.consts.IUapBtnConsts;
import installIuap.consts.IUapConsts;
import installIuap.ui.tree.IUapCheckBoxTreeTool;
import installIuap.vo.bean.IUapDataSourceInfo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.trade.pub.IVOTreeDataByCode;

import org.dom4j.Document;

/**
 * 导出建表语句对话框
 * 
 * @author zhaoti
 * 
 */
public class IUapExportSqlsDlg extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	IUapCheckBoxTreeTool treeTool = null;

	boolean bcheckMenu = false;

	public IUapExportSqlsDlg() {
		setTitle("导出微服务的建表语句");
		setBounds(100, 100, 589, 435);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setLayout(new FlowLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "West");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(2));
		getContentPane().add(buttonPane, "South");
		
		JCheckBox checkMenuBox = new JCheckBox("是否导出菜单");
		checkMenuBox.setActionCommand("MenuBox");
		checkMenuBox.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_MENUBOX));
		buttonPane.add(checkMenuBox);

		JButton okButton = new JButton("确定");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_OK));
		buttonPane.add(okButton);

		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("取消");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_CANCEL));
		buttonPane.add(cancelButton);

		JPanel panel = new JPanel();
		getContentPane().add(panel, "East");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "Center");

		IVOTreeDataByCode data = new IUapTreeData();
		IUapCheckBoxTreeTool cbTree = getCheckBoxTreeTool(data);
		cbTree.setCheckBoxTreeModal();
		scrollPane.setViewportView(cbTree.getCheckTree());
	}

	public IUapCheckBoxTreeTool getCheckBoxTreeTool(IVOTreeDataByCode data) {
		if (this.treeTool == null) {
			this.treeTool = new IUapCheckBoxTreeTool(data, "微服务列表");
		}
		return this.treeTool;
	}

	class BtnListener implements ActionListener {
		int key = -1;
		JDialog dlg = null;

		public BtnListener() {
		}

		public BtnListener(int btnKey) {
			this.key = btnKey;
		}

		public void actionPerformed(ActionEvent arg0) {
			switch (this.key) {
			case IUapBtnConsts.OPT_BTN_EXPTABLE_OK:
				Thread thread = new Thread() {
					public void run() {
						onOkPress(bcheckMenu);
					}
				};
				new IUapWaitingDlg(getContentPane(), thread,
						"正在进行导出操作,请稍候···", "建表语句导出到<" + IUapUtil.getFilePath() + "export" + File.separator +">成功，请查看").showModal();
				break;
			case IUapBtnConsts.OPT_BTN_EXPTABLE_CANCEL:
				IUapExportSqlsDlg.this.setVisible(false);
				break;
			case IUapBtnConsts.OPT_BTN_EXPTABLE_MENUBOX:
				bcheckMenu = ((JCheckBox) arg0.getSource()).isSelected();
				break;
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void onOkPress(boolean bcheckMenu) {
			IUapUtil util = new IUapUtil();
			List nodeLst = IUapExportSqlsDlg.this.treeTool.getCheckedLeafNode();
			try {
				if ((nodeLst == null) || (nodeLst.size() == 0)) {
					System.out.println("请选择要导出的节点!");
					return;
				}
				IUapDeleteFile.delete(IUapUtil.getFilePath() + "export");
				IUapDeleteFile.delete(IUapUtil.getFilePath() + "exportmysql");
				IUapDeleteFile.delete(IUapUtil.getFilePath() + "log");
				exportTableSQL(nodeLst, util, bcheckMenu);
				exportDataSQL(nodeLst, util, bcheckMenu);
				System.out.println("导出成功!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("导出异常!");
			}
		}

		@SuppressWarnings({ "unchecked" })
		public void exportTableSQL(List<String> serverList, IUapUtil util, boolean bcheckMenu) throws Exception {
			IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
			IUapScriptHelper sqlHelper = new IUapScriptHelper();
			IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
			IUapDao dao = new IUapDao(info);
			
			Map<String,Map<String,LinkedHashMap<String,Object>>> dataMap = new HashMap<String,Map<String,LinkedHashMap<String,Object>>>();
			
			if(serverList != null && serverList.size() > 0) {
				for(String serverName : serverList) {
					String wheresql = sqlHelper.buildsqlWhere(serverName);
					if(wheresql != null && wheresql.length() > 0) {
						if(wheresql.contains("or")) {
							wheresql = wheresql.substring(0, wheresql.lastIndexOf("or"));
						}
						List<String[]> table_list = (List<String[]>) dao.queryBySql("select table_name from user_tables t where " + wheresql + "", new ArrayListProcessor());
						StringBuffer createTable = new StringBuffer();
						StringBuffer mysqlcreateTable = new StringBuffer();
						
						if (table_list != null && table_list.size() > 0) {
							
							for (Object[] table_names : table_list) {
								String sql = "select column_name, data_type, data_length, data_precision, data_scale, nullable from user_tab_columns where table_name = '" + table_names[0].toString()
										+ "' order by column_id";
								List<String[]> column_list = (List<String[]>) dao.queryBySql(sql, new ArrayListProcessor());
								sql = "select * from user_col_comments where table_name = '" + table_names[0].toString() + "' ";
								List<String[]> col_comments_list = (List<String[]>) dao.queryBySql(sql, new ArrayListProcessor());
								createTable.append(sqlHelper.exportCreateTableScript(table_names[0].toString(), column_list, col_comments_list, true, IUapConsts.IUAP_DATA_TYPE.ORACEL));
								mysqlcreateTable.append(sqlHelper.exportCreateTableScript(table_names[0].toString(), column_list, col_comments_list, true, IUapConsts.IUAP_DATA_TYPE.MYSQL));
								Map<String,LinkedHashMap<String,Object>> columns = IUapXmlUtil.convertQryres2Map(column_list);
								dataMap.put(table_names[0].toString(), columns);
								
							}
						}
						String diffPath = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "createtable" + File.separator + serverName + "_createTable.sql";
						String diffPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + serverName + File.separator + "createtable" + File.separator + serverName + "_createTable.sql";
						new IUapFileOperator().writeFile(diffPath, createTable.toString());
						new IUapFileOperator().writeFile(diffPathmysql, mysqlcreateTable.toString());
					}
					// 数据结构写入xml
					Document xmlDoc = IUapXmlUtil.buildXmlbyMap(dataMap);
					String xmlPath =  IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "update" + File.separator + "db.xml";
					new IUapFileOperator().writeXmlDoc(xmlPath, xmlDoc);
				}
				
			}
		}
		
		public void exportDataSQL(List<String> serverList, IUapUtil util, boolean bcheckMenu) throws Exception {
			IUapScriptHelper sqlHelper = new IUapScriptHelper();
			if(serverList != null && serverList.size() > 0) {
				for(String serverName : serverList) {
					String dataInitsqls = sqlHelper.exportDataScript(serverName, false);
					String dataInitPath = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "init" + File.separator + "initdata.sql";
					new IUapFileOperator().writeFile(dataInitPath, dataInitsqls);
					String dataInitPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + serverName + File.separator + "init" + File.separator + "initdata.sql";
					new IUapFileOperator().writeFile(dataInitPathmysql, dataInitsqls.replace("to_date", "str_to_date"));
					if(bcheckMenu) {
						dataInitsqls = sqlHelper.exportDataScript(serverName, true);
						dataInitPath = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "menu" + File.separator + "menudata.sql";
						new IUapFileOperator().writeFile(dataInitPath, dataInitsqls);
						dataInitPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + serverName + File.separator + "menu" + "menudata.sql";
						new IUapFileOperator().writeFile(dataInitPathmysql, dataInitsqls.replace("to_date", "str_to_date"));
						
						String menudatasqls = sqlHelper.exportVirtualMemuDataScript(serverName);
						String menudataPath = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "menu" + File.separator + "virtualmenudata.sql";
						new IUapFileOperator().writeFile(menudataPath, menudatasqls);
						String menudataPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + serverName + File.separator + "menu" + File.separator + "virtualmenudata.sql";
						new IUapFileOperator().writeFile(menudataPathmysql, menudatasqls.replace("to_date", "str_to_date"));
					}
					// 抽取配置的数据库信息
					String itemsqls = sqlHelper.exportItemsDataScript();
					String itemsPath = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "itemdata" + File.separator + "itemssqlfiles.sql";
					new IUapFileOperator().writeFile(itemsPath, itemsqls);
					String itemsPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + serverName + File.separator + "itemdata" + File.separator + "itemssqlfiles.sql";
					new IUapFileOperator().writeFile(itemsPathmysql, itemsqls.replace("to_date", "str_to_date"));
				}
			}
		}
	}

}