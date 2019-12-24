package installIuap.common.tools;

import installIuap.consts.IUapConsts;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class IUapXmlUtil {
	
	public static Document buildXmlbyMap(Map<String,Map<String,LinkedHashMap<String,Object>>> dataMap) throws IOException{
		
		// <data>
		Element root = DocumentHelper.createElement("data");
		Document document = DocumentHelper.createDocument(root);
		// <tables>
		Element tables = root.addElement("tables");
		for (String tableName : dataMap.keySet()) {
			// <table name = "">
			Element table = tables.addElement("table");
			table.addAttribute("name", tableName);
			
			/*
			// <name> table_name </name>
			Element table_name = table.addElement("name");
			table_name.setText(tableName);
			*/
			
			// <columns>
			Element columns = table.addElement("columns");
			Map<String, LinkedHashMap<String, Object>> columnsMap = dataMap.get(tableName);
			for (String columnName : columnsMap.keySet()) {
				// <column name = "">
				Element column = columns.addElement("column");
				column.addAttribute("name", columnName);
				
				/*
				// <name> column_name </name> 
				Element column_name = column.addElement("name");
				column_name.setText(columnName);
				*/
				
				// <datatype> column_type </datatype>
				String datatype = columnsMap.get(columnName).keySet().toArray()[0].toString();
				Element column_type = column.addElement("datatype");
				column_type.setText(datatype);
				// <length> column_len </length>
				String datalen = columnsMap.get(columnName).values().toArray()[0].toString();
				Element column_len = column.addElement("length");
				column_len.setText(datalen);
			}
		}

        return document;

	}
 
	@SuppressWarnings("unchecked")
	public static Map<String,Map<String,LinkedHashMap<String,Object>>> readXmltoMap(String filepath) throws DocumentException{
		
		SAXReader reader = new SAXReader();
        Document document =  reader.read(filepath);
    
		List<Element> tables = (List<Element>)document.selectNodes("data/tables/table");
		Map<String,Map<String,LinkedHashMap<String,Object>>> map1 = new HashMap<String,Map<String,LinkedHashMap<String,Object>>>();
		
		for(Element table:tables){
			
			String table_name = table.attributeValue("name");
		
			List<Element> columns = table.element("columns").elements();	
			Map<String,LinkedHashMap<String,Object>> map2 = new HashMap<String,LinkedHashMap<String,Object>>();
			
			for(Element column:columns){
				
				LinkedHashMap<String,Object> map3 = new LinkedHashMap<String,Object>();
				
				String column_name = column.attributeValue("name");
				String column_type = column.elementText("datatype");
				String column_len = column.elementText("length");
				
				map3.put(column_type, column_len);
				map2.put(column_name,map3);
				
			}
			
			map1.put(table_name, map2);

		}
		return map1;
	}
	
	public static Map<String, LinkedHashMap<String, Object>> convertQryres2Map(List<String[]> qrylist) {
		
		Map<String,LinkedHashMap<String,Object>> columns = new HashMap<String,LinkedHashMap<String,Object>>();
		
		
		for(Object[] data:qrylist){
					
			LinkedHashMap<String,Object> columnMap = new LinkedHashMap<String,Object>();
			String column_name = data[0].toString();
			String data_type = data[1].toString();
			String data_length = "";
			switch(data_type){
				case IUapConsts.VARCHAR2:
				case IUapConsts.NVARCHAR2:
				case IUapConsts.CHAR:
				case IUapConsts.NCHAR:
					data_length = data[2].toString();
					break;
				case IUapConsts.NUMBER:
					if( data[3] == null ||  data[3].toString().length() == 0 ){
						data_type = "INTEGER";
					} else {
						if ( data[4] == null ||  data[4].toString().length() == 0 ){
							data_length =  data[3].toString();
						} else {
							data_length =  data[3].toString() + "," + data[4].toString();
						}
					}
					break;
				case IUapConsts.FLOAT:
					data_length =  data[3].toString();
					break;
				case IUapConsts.BLOB:
				case IUapConsts.NCLOB:
				case IUapConsts.CLOB:
				case IUapConsts.DATE:
					data_length = "";
					break;
				default:
					break;
			}
			columnMap.put(data_type, data_length);
			columns.put(column_name, columnMap);
		}
		
		return columns;
	}

}
