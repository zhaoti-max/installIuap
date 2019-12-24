package installIuap.ui.dialog;

import installIuap.common.dao.IUapDao;
import installIuap.common.helper.IUapScriptHelper;
import installIuap.common.tools.IUapFileOperator;
import installIuap.common.tools.IUapUtil;
import installIuap.common.tools.IUapWaitingDlg;
import installIuap.common.tools.IUapXmlUtil;
import installIuap.common.xmlopt.IUapXmlCtrl;
import installIuap.consts.IUapConsts;
import installIuap.vo.bean.IUapDataSourceInfo;

import java.awt.Container;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import nc.jdbc.framework.processor.ArrayListProcessor;

/**
 * ���뽨��Ի���
 * 
 * @author zhaoti
 * 
 */
public class IUapImportSqlsDlg extends JDialog {
	private static final long serialVersionUID = 1L;

	public static void importTable(Container context) {
		Thread thread = new Thread() {
			public void run() {
				upgrade();
			}
		};
		new IUapWaitingDlg(context, thread,
				"���ڽ�����������,���Ժ򡤡���", "�����ɹ���").showModal();
	}
	
	
	@SuppressWarnings("unchecked")
	public static void upgrade() {
	
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapScriptHelper sqlHelper = new IUapScriptHelper();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo("upgrade");
		
		try {
			IUapDao dao = new IUapDao(info);
			List<String[]> table_list = (List<String[]>) dao.queryBySql("select table_name from user_tables where table_name not like 'IUAP_%' and table_name not like 'BPM_%' " , new ArrayListProcessor());
			StringBuffer createTable = new StringBuffer();
			
			if (table_list != null && table_list.size() > 0) {
				
				Map<String,Map<String,LinkedHashMap<String,Object>>> dataMap = new HashMap<String,Map<String,LinkedHashMap<String,Object>>>();
				
				for (Object[] table_names : table_list) {
					String sql = "select column_name, data_type, data_length, data_precision, data_scale, nullable from user_tab_columns where table_name = '" + table_names[0].toString()
							+ "' order by column_id";
					List<String[]> column_list = (List<String[]>) dao.queryBySql(sql, new ArrayListProcessor());
					sql = "select * from user_col_comments where table_name = '" + table_names[0].toString() + "' ";
					List<String[]> col_comments_list = (List<String[]>) dao.queryBySql(sql, new ArrayListProcessor());
					createTable.append(sqlHelper.exportCreateTableScript(table_names[0].toString(), column_list, col_comments_list, true, IUapConsts.IUAP_DATA_TYPE.ORACEL));
					
					Map<String,LinkedHashMap<String,Object>> columns = IUapXmlUtil.convertQryres2Map(column_list);
					dataMap.put(table_names[0].toString(), columns);
		
				}
				List<String> serverlist = new IUapFileOperator().getFileList(IUapUtil.getFilePath() + "export" + File.separator);
				if(serverlist != null && serverlist.size() > 0) {
					for(String serverName : serverlist) {
						if(serverName.contains("mdmdoc")) continue;
						String path = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + "update" + File.separator;
						File file = new File(path + "db.xml");
						if(file.exists()) {
							String upgradeSql = sqlHelper.buildUpgradeScript(dataMap, IUapXmlUtil.readXmltoMap(path + "db.xml"));
							new IUapFileOperator().writeFile(path + "update.sql", upgradeSql);
						}
						// 1.ִ�н������exportcreatesql
						System.out.println("��ʼִ�н������createtable");
						executefiles("createtable", dao, serverName);
						System.out.println("������佨�����createtableִ�����");
						// 2.ִ�и��½ű�upgrade
						System.out.println("��ʼִ�и��½ű�upgrade");
						File f = new File(path + "update.sql");
						if(f.exists()) {
							dao.executeSqlFiles(path + "update.sql");
						}
						System.out.println("���½ű�updateִ�����");
						// 3.ִ�й��ܽű�exportdata
						System.out.println("��ʼִ�й��ܽű�init��ʼ������");
						executefiles("init", dao, serverName);
						System.out.println("���ܽű�init��ʼ������ִ�����");
						
						// 5.ִ����˵��ű�exportvirtualmenu
						System.out.println("��ʼִ�в˵�menu�ű�");
						executefiles("menu", dao, serverName);
						System.out.println("�˵��ű�menuִ�����");
						// 6.ִ��Ԥ�����ݽű�
						System.out.println("��ʼִ�����ݿ�Ԥ������itemdata�ű�");
						executefiles("itemdata", dao, serverName);
						System.out.println("���ݿ�Ԥ�����ݽű�itemdataִ�����");
					}
				}
				List<String> mdmlist = new IUapFileOperator().getFileList(IUapUtil.getFilePath() + "export" + File.separator + "mdmdoc" + File.separator);
				if(mdmlist != null && mdmlist.size() > 0) {
					for(String mdmcode : mdmlist) {
						// 4.ִ���Զ��嵵���ű�exportuapmdm
						System.out.println("��ʼִ��mdmdata�Զ��嵵���ű�");
						executefiles("mdmdata", dao, mdmcode);
						System.out.println("�Զ��嵵��mdmdata�ű�ִ�����");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	private static void executefiles(String exportfile, IUapDao dao, String serverName) throws Exception {
		String directory;
		if(exportfile.equals("mdmdata")) {
			directory = IUapUtil.getFilePath() + "export" + File.separator + "mdmdoc" + File.separator + serverName + File.separator + exportfile;
		} else {
			directory = IUapUtil.getFilePath() + "export" + File.separator + serverName + File.separator + exportfile;
		}
		List<String> filelist = new IUapFileOperator().getFileList(directory);
		if(filelist != null && filelist.size() > 0) {
			for(String sqlfile : filelist) {
				String sqlpath = directory + File.separator + sqlfile;
				dao.executeSqlFiles(sqlpath);
			}
		}
	}

}