package installIuap.common.dao;

import installIuap.common.tools.IUapFileOperator;
import installIuap.common.tools.IUapUtil;
import installIuap.vo.bean.IUapDataSourceInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.jdbc.framework.processor.ResultSetProcessor;

/**
 * IUAP封装数据访问层
 * 
 * @author zhaoti
 * 
 */
public class IUapDao {
	private Connection conn = null;

	private Statement stmt = null;

	private String db_type = "";

	public static void testConn(IUapDataSourceInfo info) throws Exception {
	}

	public IUapDao(IUapDataSourceInfo info) throws Exception {
		String url = "";

		this.db_type = info.getField("dbtype");
		String ip = info.getField("ip");
		String username = info.getField("username");
		String password = info.getField("psw");
		String dbName = info.getField("dbname");

		if (this.db_type.equals("SQLSERVER"))
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		else {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}

		if (this.conn == null) {
			if ("SQLSERVER".equals(this.db_type)) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				url = "jdbc:sqlserver://" + ip + ":1433;DatabaseName=" + dbName;
				this.conn = DriverManager.getConnection(url, username, password);
			} else {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				url = "jdbc:oracle:thin:@" + ip + ":1521:" + dbName;
				this.conn = DriverManager.getConnection(url, username, password);
			}

		}

		this.conn.isClosed();
		this.conn.setAutoCommit(false);
		this.stmt = this.conn.createStatement();
	}

	public void commit() {
		try {
			this.conn.commit();
		} catch (SQLException e) {
			rollback();
		}
	}

	public ResultSet queryBySql(String sql) throws SQLException {
		if ("ORACLE".equals(this.db_type)) {
			sql = sql.replaceAll("isnull", "nvl");
		}

		ResultSet rs = this.stmt.executeQuery(sql);
		return rs;
	}

	public Object queryBySql(String sql, ResultSetProcessor processor) throws SQLException {
		Object result = null;
		if ("ORACLE".equals(this.db_type)) {
			sql = sql.replaceAll("isnull", "nvl");
		}

		ResultSet rs = this.stmt.executeQuery(sql);
		result = processor.handleResultSet(rs);
		return result;
	}

	public void addBatch(String sql) throws SQLException, Exception {
		this.stmt.addBatch(sql);
	}

	public void clearBatch() throws SQLException, Exception {
		this.stmt.clearBatch();
	}

	public void excuteBatch() throws SQLException {
		this.stmt.executeBatch();
	}

	public void rollback() {
		try {
			this.conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public int updateBySql(String sql) throws Exception {
		return this.stmt.executeUpdate(sql);
	}

	public void updateBySqls(List<String> lstSql) throws Exception {
		clearBatch();

		for (String sql : lstSql) {
			addBatch(sql);
		}

		excuteBatch();
	}

	public void executeSqlFiles(String sqlFile) throws Exception {
		if (sqlFile != null && sqlFile.length() > 0) {
			List<String> lstSql = loadSql(sqlFile);
			if (lstSql != null && lstSql.size() > 0) {
				for (String sql : lstSql) {
					try {
						updateBySql(sql);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						sql = sql + ";\n";
						new IUapFileOperator().writeFileAdd(IUapUtil.getFilePath() + "log" + File.separator + "sqllog.log", sql);
					}
				}
			}
		}
	}

	@SuppressWarnings("resource")
	private List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();

		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);
			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}

			// Windows 下换行是 /r/n, Linux 下是 /n
			String[] sqlArr = sqlSb.toString().split("(;\r\n)|(;\n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 获取sql文件
	 * 
	 * @param listSql
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public StringBuffer excuteInsertSqls(String sqlWhere, String tableName) throws SQLException {
		StringBuffer insertSQL = new StringBuffer();
		ResultSet rs = this.stmt.executeQuery(" select * from " + tableName + " where " + sqlWhere);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		while (rs.next()) {
			StringBuffer columnName = new StringBuffer();
			StringBuffer columnValue = new StringBuffer();
			for (int i = 1; i <= columnCount; i++) {
				String value = getSqlByValue(rs.getObject(i));
				if (value != null) {
					value = value.trim();
				} else {
					continue;
				}
				if (i == 1 || i == columnCount) {
					if (i == columnCount) {
						columnName.append(",");
					}
					columnName.append(rsmd.getColumnName(i));
					if (i == 1) {
						if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
							columnValue.append("'").append(value).append("',");
						} else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i)
								|| Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i)
								|| Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
							columnValue.append(value).append(",");
						} else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
							columnValue.append("to_date('").append(value).append("', 'yyyy-mm-dd hh24:mi:ss'), ");
						} else {
							columnValue.append(value).append(",");
						}
					} else {
						if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
							columnValue.append("'").append(value).append("'");
						} else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i)
								|| Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i)
								|| Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
							columnValue.append(value);
						} else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
							columnValue.append("to_date('").append(value).append("', 'yyyy-mm-dd hh24:mi:ss') ");
						} else {
							columnValue.append(value);

						}
					}

				} else {
					columnName.append("," + rsmd.getColumnName(i));
					if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
						columnValue.append("'").append(value).append("'").append(",");
					} else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i)
							|| Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i)
							|| Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
						columnValue.append(value).append(",");
					} else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
						columnValue.append("to_date('").append(value).append("', 'yyyy-mm-dd hh24:mi:ss'), ");
					} else {
						columnValue.append(value).append(",");
					}
				}
			}
			insertSQL.append(insertSQL(columnName, columnValue, tableName));
		}
		return insertSQL;
	}

	public String getSqlByValue(Object value) {
		String rtnVal = "";
		if (value == null) {
			return null;
		}

		if ((value instanceof String))
			rtnVal = String.valueOf(value);
		else if (((value instanceof Integer)) || ((value instanceof Double)))
			rtnVal = String.valueOf(value);
		else if ((value instanceof Boolean))
			rtnVal = ((Boolean) value).booleanValue() ? "Y" : "N";
		else if ((value instanceof java.sql.Date)) {
			rtnVal = "to_date('" + String.valueOf(value) + "', 'yyyy-mm-dd hh24:mi:ss')";
		} else {
			rtnVal = String.valueOf(value);
		}

		return rtnVal;
	}

	/**
	 * 拼装sql
	 * 
	 * @paramColumnName
	 * @paramColumnValue
	 */
	private static StringBuffer insertSQL(StringBuffer ColumnName, StringBuffer ColumnValue, String tableName) {
		StringBuffer insertSQL = new StringBuffer();
		String value = ColumnValue.toString();
		if(value.lastIndexOf(",") == value.length() - 1) {
			value = value.substring(0, value.lastIndexOf(","));
		}
		insertSQL.append("insert into ").append(tableName).append("(").append(ColumnName.toString()).append(") VALUES (").append(value).append(");").append("\n");
		return insertSQL;
	}
}