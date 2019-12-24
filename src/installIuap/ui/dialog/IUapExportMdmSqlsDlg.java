package installIuap.ui.dialog;

import installIuap.common.base.IUapMdmTreeData;
import installIuap.common.helper.IUapScriptHelper;
import installIuap.common.tools.IUapDeleteFile;
import installIuap.common.tools.IUapFileOperator;
import installIuap.common.tools.IUapUtil;
import installIuap.common.tools.IUapWaitingDlg;
import installIuap.consts.IUapBtnConsts;
import installIuap.consts.IUapConsts;
import installIuap.ui.tree.IUapMdmCheckBoxTreeTool;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import nc.ui.trade.pub.IVOTreeDataByCode;

/**
 * �����������Ի���
 * 
 * @author zhaoti
 * 
 */
public class IUapExportMdmSqlsDlg extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	IUapMdmCheckBoxTreeTool treeTool = null;

	boolean bcheckMdm = false;

	public IUapExportMdmSqlsDlg() {
		setTitle("����΢�����Զ��嵵�������");
		setBounds(100, 100, 589, 435);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setLayout(new FlowLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "West");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(2));
		getContentPane().add(buttonPane, "South");
		
		JCheckBox checkMdmBox = new JCheckBox("�Ƿ񵼳��Զ��嵵������");
		checkMdmBox.setActionCommand("MdmBox");
		checkMdmBox.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_MDMBOX));
		buttonPane.add(checkMdmBox);

		JButton okButton = new JButton("ȷ��");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_OK));
		buttonPane.add(okButton);

		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("ȡ��");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_EXPTABLE_CANCEL));
		buttonPane.add(cancelButton);

		JPanel panel = new JPanel();
		getContentPane().add(panel, "East");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "Center");

		IVOTreeDataByCode data = new IUapMdmTreeData();
		IUapMdmCheckBoxTreeTool cbTree = getCheckBoxTreeTool(data);
		cbTree.setCheckBoxTreeModal();
		scrollPane.setViewportView(cbTree.getCheckTree());
	}

	public IUapMdmCheckBoxTreeTool getCheckBoxTreeTool(IVOTreeDataByCode data) {
		if (this.treeTool == null) {
			this.treeTool = new IUapMdmCheckBoxTreeTool(data, "΢�����Զ��嵵���б�");
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
						onOkPress(bcheckMdm);
					}
				};
				new IUapWaitingDlg(getContentPane(), thread,
						"���ڽ��е�������,���Ժ򡤡���", "������䵼����<" + IUapUtil.getFilePath() + "export" + File.separator +">�ɹ�����鿴").showModal();
				break;
			case IUapBtnConsts.OPT_BTN_EXPTABLE_CANCEL:
				IUapExportMdmSqlsDlg.this.setVisible(false);
				break;
			case IUapBtnConsts.OPT_BTN_EXPTABLE_MDMBOX:
				bcheckMdm = ((JCheckBox) arg0.getSource()).isSelected();
				break;
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void onOkPress(boolean bcheckMdm) {
			IUapUtil util = new IUapUtil();
			List nodeLst = IUapExportMdmSqlsDlg.this.treeTool.getCheckedLeafNode();
			try {
				if ((nodeLst == null) || (nodeLst.size() == 0)) {
					System.out.println("��ѡ��Ҫ�����Ľڵ�!");
					return;
				}
				exportDataSQL(nodeLst, util, bcheckMdm);
				System.out.println("�����ɹ�!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("�����쳣!");
			}
		}
		
		public void exportDataSQL(List<String> serverList, IUapUtil util, boolean bcheckMdm) throws Exception {
			IUapScriptHelper sqlHelper = new IUapScriptHelper();
			if(serverList != null && serverList.size() > 0) {
				for(String serverName : serverList) {
					String uapmdmsqls = sqlHelper.exportUapmdmDataScript(serverName, IUapConsts.IUAP_DATA_TYPE.ORACEL, bcheckMdm);
					String uapmdmPath = IUapUtil.getFilePath() + "export" + File.separator + "mdmdoc" + File.separator + serverName + File.separator + "mdmdata" + File.separator + "uapmdmdata.sql";
					IUapDeleteFile.delete(uapmdmPath);
					new IUapFileOperator().writeFile(uapmdmPath, uapmdmsqls);
					String uapmdmsqlsmysql = sqlHelper.exportUapmdmDataScript(serverName, IUapConsts.IUAP_DATA_TYPE.MYSQL, bcheckMdm);
					String uapmdmPathmysql = IUapUtil.getFilePath() + "exportmysql" + File.separator + "mdmdoc" + File.separator + serverName + File.separator + "mdmdata" + File.separator + "uapmdmdata.sql";
					IUapDeleteFile.delete(uapmdmPathmysql);
					new IUapFileOperator().writeFile(uapmdmPathmysql, uapmdmsqlsmysql.replace("to_date", "str_to_date"));
				}
			}
		}
	}

}