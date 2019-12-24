package installIuap.ui.listen;

import installIuap.consts.IUapBtnConsts;
import installIuap.ui.dialog.IUapDatasourceDlg;
import installIuap.ui.dialog.IUapExportMdmSqlsDlg;
import installIuap.ui.dialog.IUapExportSqlsDlg;
import installIuap.ui.dialog.IUapImportSqlsDlg;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 界面监听事件
 * 
 * @author zhaoti
 * 
 */
public class IUapFrameChangeListener implements ActionListener {
	int key = -1;
	private Container context;
	private IUapDatasourceDlg datasourcedlg;
	private IUapExportSqlsDlg exportsqldlg;
	private IUapExportMdmSqlsDlg exportmdmsqldlg;
	
	public void setDatasourcedlg(IUapDatasourceDlg datasourcedlg) {
		this.datasourcedlg = datasourcedlg;
	}

	public void setExportsqldlg(IUapExportSqlsDlg exportsqldlg) {
		this.exportsqldlg = exportsqldlg;
	}
	
	public void setExportMdmsqldlg(IUapExportMdmSqlsDlg exportmdmsqldlg) {
		this.exportmdmsqldlg = exportmdmsqldlg;
	}

	public IUapFrameChangeListener() {
	}

	public IUapFrameChangeListener(int btnKey) {
		this.key = btnKey;
	}

	public IUapFrameChangeListener(int btnKey, Container context) {
		this.key = btnKey;
		this.context = context;
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		switch (this.key) {
		case IUapBtnConsts.FRAME_DATA_SOURCE_TAG:
			if(datasourcedlg == null) datasourcedlg = new IUapDatasourceDlg();
			datasourcedlg.setVisible(true);
			break;
		case IUapBtnConsts.FRAME_EXPORT_TABLE_TAG:
			if(exportsqldlg == null) exportsqldlg = new IUapExportSqlsDlg();
			exportsqldlg.setVisible(true);
			break;
		case IUapBtnConsts.FRAME_IMPORT_TABLE_TAG:
			new IUapImportSqlsDlg().importTable(this.context);
			break;
		case IUapBtnConsts.FRAME_EXPORT_MDM_TAG:
			if(exportmdmsqldlg == null) exportmdmsqldlg = new IUapExportMdmSqlsDlg();
			exportmdmsqldlg.setVisible(true);
			break;
		}
	}

}