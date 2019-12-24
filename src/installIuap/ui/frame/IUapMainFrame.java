package installIuap.ui.frame;

import installIuap.common.dao.IUapDao;
import installIuap.common.xmlopt.IUapXmlCtrl;
import installIuap.consts.IUapBtnConsts;
import installIuap.ui.listen.IUapFrameChangeListener;
import installIuap.vo.bean.IUapDataSourceInfo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 主对话框
 * 
 * @author zhaoti
 * 
 */
public class IUapMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private IUapXmlCtrl xmlCtrl = null;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUapMainFrame frame = new IUapMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IUapMainFrame() {
		setTitle("金融公司IUAP(开发->测试)数据同步功能");
		setDefaultCloseOperation(3);
		setSize(650, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);

		JLabel label = new JLabel("金融公司IUAP(开发->测试)数据同步功能");
		label.setFont(new Font("宋体", 1, 30));
		label.setHorizontalAlignment(0);
		this.contentPane.add(label, BorderLayout.NORTH);

		JPanel centerpanel = new JPanel();
		this.contentPane.add(centerpanel, BorderLayout.CENTER);
		centerpanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		centerpanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(1, 3, 3));

		JButton btnconfig = new JButton("数据库配置");
		btnconfig.addActionListener(new IUapFrameChangeListener(IUapBtnConsts.FRAME_DATA_SOURCE_TAG));
		panel_1.add(btnconfig);

		JButton btnExpTable = new JButton("微服务脚本打包");
		btnExpTable.addActionListener(new IUapFrameChangeListener(IUapBtnConsts.FRAME_EXPORT_TABLE_TAG));
		panel_1.add(btnExpTable);
		
		JButton btnExpMdmTable = new JButton("微服务自定义档案脚本打包");
		btnExpMdmTable.addActionListener(new IUapFrameChangeListener(IUapBtnConsts.FRAME_EXPORT_MDM_TAG));
		panel_1.add(btnExpMdmTable);

		JButton btnImpTable = new JButton("微服务数据升级");
		btnImpTable.addActionListener(new IUapFrameChangeListener(IUapBtnConsts.FRAME_IMPORT_TABLE_TAG, this.getContentPane()));
		panel_1.add(btnImpTable);

		JPanel panelport = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelport.getLayout();
		flowLayout.setAlignment(0);
		centerpanel.add(panelport);

		this.xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = this.xmlCtrl.getDefPropInfo(null);
		List names = this.xmlCtrl.getInfoNames();
		String defName = this.xmlCtrl.getDefProp();

		this.lblName = new JLabel("    选择配置：");
		this.lblName.setHorizontalAlignment(2);
		centerpanel.add(lblName);

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
					String propName = (String) IUapMainFrame.this.cbxName.getSelectedItem();
					IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
					IUapDataSourceInfo info = xmlCtrl.getPropByName(propName);
					IUapMainFrame.this.tfPort.setText(info.getField("port"));
					IUapMainFrame.this.tfIP.setText(info.getField("ip"));
					IUapMainFrame.this.cbxDBType.setSelectedItem(info.getField("dbtype"));
					IUapMainFrame.this.tfDBName.setText(info.getField("dbname"));
					IUapMainFrame.this.tfUser.setText(info.getField("username"));
					IUapMainFrame.this.tfPsw.setText(info.getField("psw"));
				}
			}
		});
		panelport.add(this.cbxName);

		JButton btnTestConn = new JButton("测试连接");
		btnTestConn.addActionListener(new BtnListener(IUapBtnConsts.FRAME_TEST_CONNECT_TAG));
		panelport.add(btnTestConn);

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
		centerpanel.add(panelip);

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
		centerpanel.add(paneldbuser);

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
		centerpanel.add(paneluser);

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
		centerpanel.add(panelexp);

		JLabel labelsouth = new JLabel("版权：用友金融信息技术股份有限公司 版本：V1.0 技术支持：zhaoti@yonyou.com 18201004755");
		labelsouth.setFont(new Font("宋体", 0, 14));
		labelsouth.setHorizontalAlignment(0);
		this.contentPane.add(labelsouth, BorderLayout.SOUTH);
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
			case IUapBtnConsts.FRAME_TEST_CONNECT_TAG:
				testConnect();
				System.out.println("测试数据库连接");
				break;
			case 000:
			}
		}
	}

	public void testConnect() {
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
}