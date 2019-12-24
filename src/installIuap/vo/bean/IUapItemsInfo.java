package installIuap.vo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * IUAP数据库预置脚本信息
 * 
 * @author Administrator
 * 
 */
public class IUapItemsInfo {
	public static final String ITEMS = "items";
	public static final String ITEM = "item";
	public static final String ITEMNAME = "itemName";
	public static final String ITEMTABLE = "itemTable";
	public static final String FIXEDWHERE = "fixedWhere";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, String> itemsMap = new HashMap();

	public Map<String, String> getItemsMap() {
		return itemsMap;
	}

	public void setItemsMap(Map<String, String> itemsMap) {
		this.itemsMap = itemsMap;
	}

	public String getField(String field) {
		String val = (String) this.itemsMap.get(field);
		val = val == null ? "" : val.toString();
		return val;
	}

	public void setField(String field, String value) {
		this.itemsMap.put(field, value);
	}

}