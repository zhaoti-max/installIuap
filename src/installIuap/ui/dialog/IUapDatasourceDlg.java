package installIuap.ui.dialog;

import installIuap.common.dao.IUapDao;
import installIuap.common.xmlopt.IUapXmlCtrl;
import installIuap.consts.IUapBtnConsts;
import installIuap.vo.bean.IUapDataSourceInfo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 数据源配置对话框
 * 
 * @author zhaoti
 * 
 */
public class IUapDatasourceDlg extends JDialog {
	private static final long serialVersionUID = 1L;

	private IUapXmlCtrl xmlCtrl = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUser;
	private JTextField tfDBName;
	private JTextField tfIP;
	private JTextField tfPort;
	private JPasswordField tfPsw;
	private JLabel lblName;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxName;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxDBType;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IUapDatasourceDlg() {
		setTitle("数据库配置");
		setBounds(100, 100, 600, 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.setAlwaysOnTop(true);
		setResizable(false);
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "Center");

		this.xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = this.xmlCtrl.getDefPropInfo(null);
		List names = this.xmlCtrl.getInfoNames();
		String defName = this.xmlCtrl.getDefProp();

		this.contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		this.contentPanel.add(panel);
		panel.setLayout(new FlowLayout(0, 5, 5));

		this.lblName = new JLabel("    选择配置：");
		this.lblName.setHorizontalAlignment(2);
		panel.add(this.lblName);

		this.cbxName = new JComboBox();
		this.cbxName.setMaximumRowCount(20);
		for (int idx = 0; idx < names.size(); idx++) {
			this.cbxName.addItem(names.get(idx));
			if (defName.equals(names.get(idx))) {
				this.cbxName.setSelectedIndex(idx);
			}
		}
		this.cbxName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					String propName = (String) IUapDatasourceDlg.this.cbxName.getSelectedItem();
					IUapDataSourceInfo info = IUapDatasourceDlg.this.xmlCtrl.getPropByName(propName);
					IUapDatasourceDlg.this.tfPort.setText(info.getField("port"));
					IUapDatasourceDlg.this.tfIP.setText(info.getField("ip"));
					IUapDatasourceDlg.this.cbxDBType.setSelectedItem(info.getField("dbtype"));
					IUapDatasourceDlg.this.tfDBName.setText(info.getField("dbname"));
					IUapDatasourceDlg.this.tfUser.setText(info.getField("username"));
					IUapDatasourceDlg.this.tfPsw.setText(info.getField("psw"));
				}
			}
		});
		panel.add(this.cbxName);

		JButton btnTestConn = new JButton("测试连接");
		btnTestConn.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_DATASOURCE_TEST));
		panel.add(btnTestConn);

		JPanel panelport = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelport.getLayout();
		flowLayout.setAlignment(0);
		this.contentPanel.add(panelport);

		JLabel lblDBType = new JLabel("数据库类型：");
		lblDBType.setHorizontalAlignment(2);
		panelport.add(lblDBType);

		this.cbxDBType = new JComboBox();
		this.cbxDBType.setModel(new DefaultComboBoxModel(new String[] { "ORACLE" }));
		this.cbxDBType.setSelectedItem(info.getField("dbtype"));
		panelport.add(this.cbxDBType);

		JLabel lblPort = new JLabel("端口：");
		lblPort.setHorizontalAlignment(2);
		panelport.add(lblPort);

		this.tfPort = new JTextField();
		this.tfPort.setHorizontalAlignment(2);
		this.tfPort.setText(info.getField("port"));
		panelport.add(this.tfPort);
		this.tfPort.setColumns(4);

		JPanel panelip = new JPanel();
		FlowLayout flowLayoutip = (FlowLayout) panelip.getLayout();
		flowLayoutip.setAlignment(0);
		this.contentPanel.add(panelip);

		JLabel lblIp = new JLabel("               IP   ：");
		lblIp.setHorizontalAlignment(2);
		panelip.add(lblIp);

		this.tfIP = new JTextField();
		this.tfIP.setText(info.getField("ip"));
		panelip.add(this.tfIP);
		this.tfIP.setColumns(15);

		JPanel paneldbuser = new JPanel();
		FlowLayout flowLayoutdbuser = (FlowLayout) paneldbuser.getLayout();
		flowLayoutdbuser.setAlignment(0);
		this.contentPanel.add(paneldbuser);

		JLabel lblDBName = new JLabel("    数据库名：");
		lblDBName.setHorizontalAlignment(2);
		paneldbuser.add(lblDBName);

		this.tfDBName = new JTextField();
		this.tfDBName.setHorizontalAlignment(2);
		this.tfDBName.setText(info.getField("dbname"));
		paneldbuser.add(this.tfDBName);
		this.tfDBName.setColumns(40);

		JPanel paneluser = new JPanel();
		FlowLayout flowLayouuser = (FlowLayout) paneluser.getLayout();
		flowLayouuser.setAlignment(0);
		this.contentPanel.add(paneluser);

		JLabel lblUser = new JLabel("             用户：");
		lblUser.setHorizontalAlignment(2);
		paneluser.add(lblUser);

		this.tfUser = new JTextField();
		this.tfUser.setText(info.getField("username"));
		paneluser.add(this.tfUser);
		this.tfUser.setColumns(20);

		JLabel lblPsw = new JLabel("密码：");
		lblPsw.setHorizontalAlignment(2);
		paneluser.add(lblPsw);

		this.tfPsw = new JPasswordField();
		this.tfPsw.setColumns(10);
		this.tfPsw.setText(info.getField("psw"));
		paneluser.add(this.tfPsw);

		JPanel panelexp = new JPanel();
		FlowLayout flowLayouexp = (FlowLayout) panelexp.getLayout();
		flowLayouexp.setAlignment(0);
		this.contentPanel.add(panelexp);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(2));
		getContentPane().add(buttonPane, "South");

		JButton btnUse = new JButton("保存");
		btnUse.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_DATASOURCE_USE));
		buttonPane.add(btnUse);

		JButton btnCancel = new JButton("退出");
		btnCancel.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_DATASOURCE_CANCEL));
		buttonPane.add(btnCancel);

		JButton btnOK = new JButton("保存并退出");
		btnOK.addActionListener(new BtnListener(IUapBtnConsts.OPT_BTN_DATASOURCE_OK));
		buttonPane.add(btnOK);
		getRootPane().setDefaultButton(btnOK);
	}

	private void editPropInfo() {
		IUapDataSourceInfo info = getInputInfo();
		try {
			new IUapDao(info);
			this.xmlCtrl.editPropName(info);
			this.xmlCtrl.updateXML();
			JOptionPane.showMessageDialog(this, "保存成功！");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "保存失败，数据库连接错误！");
			info = this.xmlCtrl.getDefPropInfo(null);
			resetInputInfo(info);
		}
	}

	private void resetInputInfo(IUapDataSourceInfo info) {
		this.tfPort.setText(info.getField("port"));
		this.tfIP.setText(info.getField("ip"));
		this.cbxDBType.setSelectedItem(info.getField("dbtype"));
		this.tfDBName.setText(info.getField("dbname"));
		this.tfUser.setText(info.getField("username"));
		this.tfPsw.setText(info.getField("psw"));
	}

	private IUapDataSourceInfo getInputInfo() {
		IUapDataSourceInfo info = new IUapDataSourceInfo();
		info.setField("propname", (String) this.cbxName.getSelectedItem());
		info.setField("dbtype", (String) this.cbxDBType.getSelectedItem());
		info.setField("port", this.tfPort.getText());
		info.setField("ip", this.tfIP.getText());
		info.setField("dbname", this.tfDBName.getText());
		info.setField("username", this.tfUser.getText());
		info.setField("psw", String.valueOf(this.tfPsw.getPassword()));

		return info;
	}

	private void testConnect() {
		try {
			IUapDataSourceInfo info = getInputInfo();
			new IUapDao(info);
			JOptionPane.showMessageDialog(this, "测试通过！");
			System.out.println("测试通过！");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "测试失败！");
			System.out.println("测试失败！");
			e.printStackTrace();
		}
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
			case IUapBtnConsts.OPT_BTN_DATASOURCE_TEST:
				IUapDatasourceDlg.this.testConnect();
				break;
			case IUapBtnConsts.OPT_BTN_DATASOURCE_USE:
				IUapDatasourceDlg.this.editPropInfo();
				break;
			case IUapBtnConsts.OPT_BTN_DATASOURCE_OK:
				IUapDatasourceDlg.this.editPropInfo();
				IUapDatasourceDlg.this.setVisible(false);
				break;
			case IUapBtnConsts.OPT_BTN_DATASOURCE_CANCEL:
				IUapDatasourceDlg.this.setVisible(false);
			case 47:
			case 48:
			case 49:
			case 50:
			}
		}
	}

}