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
 * 导入建表对话框
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
				"正在进行升级操作,请稍候···", "升级成功！").showModal();
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
						// 1.执行建表语句exportcreatesql
						System.out.println("开始执行建表语句createtable");
						executefiles("createtable", dao, serverName);
						System.out.println("建表语句建表语句createtable执行完成");
						// 2.执行更新脚本upgrade
						System.out.println("开始执行更新脚本upgrade");
						File f = new File(path + "update.sql");
						if(f.exists()) {
							dao.executeSqlFiles(path + "update.sql");
						}
						System.out.println("更新脚本update执行完成");
						// 3.执行功能脚本exportdata
						System.out.println("开始执行功能脚本init初始化数据");
						executefiles("init", dao, serverName);
						System.out.println("功能脚本init初始化数据执行完成");
						
						// 5.执行虚菜单脚本exportvirtualmenu
						System.out.println("开始执行菜单menu脚本");
						executefiles("menu", dao, serverName);
						System.out.println("菜单脚本menu执行完成");
						// 6.执行预置数据脚本
						System.out.println("开始执行数据库预置数据itemdata脚本");
						executefiles("itemdata", dao, serverName);
						System.out.println("数据库预置数据脚本itemdata执行完成");
					}
				}
				List<String> mdmlist = new IUapFileOperator().getFileList(IUapUtil.getFilePath() + "export" + File.separator + "mdmdoc" + File.separator);
				if(mdmlist != null && mdmlist.size() > 0) {
					for(String mdmcode : mdmlist) {
						// 4.执行自定义档案脚本exportuapmdm
						System.out.println("开始执行mdmdata自定义档案脚本");
						executefiles("mdmdata", dao, mdmcode);
						System.out.println("自定义档案mdmdata脚本执行完成");
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