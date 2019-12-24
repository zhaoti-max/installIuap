package installIuap.vo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * IUAP数据库预置脚本信息
 * 
 * @author Administrator
 * 
 */
public class IUapTableItemsInfo {
	public static final String ITEMS = "items";
	public static final String ITEM = "item";
	public static final String ITEMNAME = "serverid";
	public static final String ITEMTABLE = "tablelike";
	public static final String FIXEDWHERE = "tablevalue";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, String> tableitemsMap = new HashMap();

	public Map<String, String> getTableitemsMap() {
		return tableitemsMap;
	}

	public void setTableitemsMap(Map<String, String> tableitemsMap) {
		this.tableitemsMap = tableitemsMap;
	}

	public String getField(String field) {
		String val = (String) this.tableitemsMap.get(field);
		val = val == null ? "" : val.toString();
		return val;
	}

	public void setField(String field, String value) {
		this.tableitemsMap.put(field, value);
	}

}