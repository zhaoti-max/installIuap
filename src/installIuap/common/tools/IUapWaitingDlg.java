package installIuap.common.tools;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


/**
 * <p>
 * 导出进程等待框
 * <p>
 */

public class IUapWaitingDlg extends JDialog {

	private static final long serialVersionUID = -8804502968774744420L;

	private Container parentUI = null;
	private JPanel mainPane = null;

	private JProgressBar progressBar = null;

	private JLabel lbStatus = null;
	private Thread thread = null;
	private JDialog currDlg = null;

	private String resultInfo = null;
	private String statusInfo = null;

	public IUapWaitingDlg(Container parentUI, Thread thread, String statusInfo, String resultInfo) {
		super();
		this.resultInfo = resultInfo;
		this.statusInfo = statusInfo;
		this.parentUI = parentUI;
		this.thread = thread;
		this.currDlg = this;
		this.init();
	}
	
	/**
	 * 显示对话框
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void showModal() {
		setModal(true);
		if (!isShowing()) {
				Dimension screan = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension dlgsize = this.getSize();
				setLocation((screan.width - dlgsize.width) / 2,(screan.height - dlgsize.height) / 2);
			show();
		}
	}

	public void init() {
		this.getContentPane().add(getMainPane());
		this.setUndecorated(true);
		this.setResizable(true);
		this.setSize(230, 60);
		this.setLocationRelativeTo(parentUI);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.prcess();
	}

	public JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(Color.LIGHT_GRAY);
			lbStatus = new JLabel(statusInfo);
			lbStatus.setBounds(20, 50, 350, 25);
			mainPane.add(getProgressBar());
			mainPane.add(lbStatus);
		}
		return mainPane;
	}

	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setBounds(20, 20, 350, 15);
			progressBar.setIndeterminate(true);
		}
		return progressBar;
	}

	public void prcess() {
		new Thread() {
			public void run() {
				try {
					thread.start();
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					currDlg.dispose();
					if (resultInfo != null && resultInfo.trim().length() > 0) {
						JOptionPane.showMessageDialog(parentUI, resultInfo, "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}.start();
	}
}
