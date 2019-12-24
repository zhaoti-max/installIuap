package installIuap.vo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * IUAP数据库文件配置信息
 * 
 * @author Administrator
 * 
 */
public class IUapDataSourceInfo {
	public static final String PROPNAME = "propname";
	public static final String IP = "ip";
	public static final String USERNAME = "username";
	public static final String PSW = "psw";
	public static final String DBTYPE = "dbtype";
	public static final String DBNAME = "dbname";
	public static final String PORT = "port";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, String> propMap = new HashMap();

	public static final String[] ALL_FILED = { "propname", "ip", "username", "psw", "dbtype", "dbname", "port" };

	public Map<String, String> getPropMap() {
		return this.propMap;
	}

	public void setPropMap(Map<String, String> propMap) {
		this.propMap = propMap;
	}

	public void setField(String field, String value) {
		this.propMap.put(field, value);
	}

	public String getField(String field) {
		String val = (String) this.propMap.get(field);
		val = val == null ? "" : val.toString();

		return val;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IUapDataSourceInfo clone() {
		IUapDataSourceInfo info = new IUapDataSourceInfo();
		Map infoMap = new HashMap();

		for (String field : this.propMap.keySet()) {
			infoMap.put(field, (String) this.propMap.get(field));
		}

		info.setPropMap(infoMap);

		return info;
	}
}