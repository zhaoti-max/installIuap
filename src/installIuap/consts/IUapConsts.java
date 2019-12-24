package installIuap.consts;


/**
 * IUAP公共参数
 * 
 * @author zhaoti
 * 
 */
public class IUapConsts {
	public static final String FOLDER_TEMP = "tmp";
	public static final String FOLDER_DIFF = "diff";
	public static final String FOLDER_EXPORT = "export";
	public static final String FILE_SET_XML = "datasource.xml";
	public static final int TEMPLATE_BILL = 0;
	public static final int TEMPLATE_QUERY = 1;
	public static final int TEMPLATE_REPORT = 2;
	public static final int TEMPLATE_PRINT = 3;

	public static final String ID = "ID";
	public static final String VARCHAR2 = "VARCHAR2";
	public static final String NVARCHAR2 = "NVARCHAR2";
	public static final String NUMBER = "NUMBER";
	public static final String FLOAT = "FLOAT";
	public static final String CHAR = "CHAR";
	public static final String NCHAR = "NCHAR";
	public static final String DATE = "DATE";
	public static final String NCLOB = "NCLOB";
	public static final String CLOB = "CLOB";
	public static final String BLOB = "BLOB";
	
	public interface IUAP_DATA_TYPE {
		Integer ORACEL = 0;
		Integer MYSQL = 1;
		Integer DB2 = 2;
	}

	public static final String TABLE_SPACE_REMARK = "\n )\n tablespace NNC_DATA01\n pctfree 10\n initrans 1\n maxtrans 255\n storage\n (\n initial 256\n next 256\n minextents 1\n maxextents unlimited\n pctincrease 0\n );";
	public static final String TABLE_INDEX_REMARK = "\n using index\n tablespace NNC_DATA01\n pctfree 10\n initrans 2\n maxtrans 255\n storage\n (\n initial 256K\n next 256K\n minextents 1\n maxextents unlimited\n pctincrease 0\n );";
}