package installIuap.common.xmlopt;

import installIuap.common.tools.IUapUtil;
import installIuap.vo.bean.IUapDataSourceInfo;
import installIuap.vo.bean.IUapItemsInfo;
import installIuap.vo.bean.IUapTableItemsInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * IUAPxml½âÎö
 * 
 * @author zhaoti
 * 
 */
public class IUapXmlCtrl {
	private String defProp = "";
	private Map<String, IUapDataSourceInfo> propMap = null;
	private Map<String, IUapItemsInfo> itemsMap = null;
	private Map<String, IUapTableItemsInfo> tableitemsMap = null;
	Document document = null;

	public IUapXmlCtrl() {
		parserXml();
		parserItemsXml();
		parserTableItemsXml();
	}

	public void createXml() {
		this.document = DocumentHelper.createDocument();

		Element props = this.document.addElement("datasources");

		Element defselnode = props.addElement("defprop");
		defselnode.setText("disign");

		Element propInfo = props.addElement("datasource");

		Element name = propInfo.addElement("propname");
		name.setText("disign");
		Element ip = propInfo.addElement("ip");
		ip.setText("");
		Element username = propInfo.addElement("username");
		username.setText("");
		Element psw = propInfo.addElement("psw");
		psw.setText("");
		Element dbname = propInfo.addElement("dbname");
		dbname.setText("");
		Element dbtype = propInfo.addElement("dbtype");
		dbtype.setText("");
		Element port = propInfo.addElement("port");
		port.setText("");

		updateXML();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parserXml() {
		this.propMap = new HashMap();
		SAXReader saxReader = new SAXReader();
		try {
			String path = IUapUtil.getFilePath() + "config" + File.separator + "datasource.xml";
			File configxml = new File(path);
			this.document = saxReader.read(configxml);

			Element props = this.document.getRootElement();
			Element prop = null;
			Element property = null;
			IUapDataSourceInfo info = null;
			for (Iterator diffprops = props.elementIterator(); diffprops.hasNext();) {
				prop = (Element) diffprops.next();

				if (prop.getName().equals("defprop")) {
					this.defProp = prop.getText();
				} else {
					info = new IUapDataSourceInfo();
					for (Iterator propInfo = prop.elementIterator(); propInfo.hasNext();) {
						property = (Element) propInfo.next();
						info.setField(property.getName(), property.getText());
						this.propMap.put(info.getField("propname"), info);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parserItemsXml() {
		this.itemsMap = new HashMap();
		SAXReader saxReader = new SAXReader();
		try {
			String path = IUapUtil.getFilePath() + "config" + File.separator + "items.xml";
			File configxml = new File(path);
			Document document = saxReader.read(configxml);
			Element props = document.getRootElement();
			Element prop = null;
			Element property = null;
			IUapItemsInfo info = null;
			for (Iterator diffprops = props.elementIterator(); diffprops.hasNext();) {
				prop = (Element) diffprops.next();
				info = new IUapItemsInfo();
				for (Iterator itemsInfo = prop.elementIterator(); itemsInfo.hasNext();) {
					property = (Element) itemsInfo.next();
					info.setField(property.getName(), property.getText());
					this.itemsMap.put(info.getField("itemTable"), info);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void parserTableItemsXml() {
		this.tableitemsMap = new HashMap();
		SAXReader saxReader = new SAXReader();
		try {
			String path = IUapUtil.getFilePath() + "config" + File.separator + "tablenameitems.xml";
			File configxml = new File(path);
			Document document = saxReader.read(configxml);
			Element props = document.getRootElement();
			Element prop = null;
			Element property = null;
			IUapTableItemsInfo info = null;
			for (Iterator diffprops = props.elementIterator(); diffprops.hasNext();) {
				prop = (Element) diffprops.next();
				info = new IUapTableItemsInfo();
				for (Iterator itemsInfo = prop.elementIterator(); itemsInfo.hasNext();) {
					property = (Element) itemsInfo.next();
					info.setField(property.getName(), property.getText());
					this.tableitemsMap.put(info.getField("serverid"), info);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void delPropInfo(String name) {
		List itemList = this.document.selectNodes("//datasources/datasource");

		for (int x = 0; x < itemList.size(); x++) {
			Element itemElement = (Element) itemList.get(x);
			Element nameElement = itemElement.element("propname");
			if (nameElement.getText().equals(name)) {
				itemElement.detach();
				this.propMap.remove(name);

				if (name.equals(this.defProp)) {
					if (getInfoNames().size() > 0)
						this.defProp = ((String) getInfoNames().get(0));
					else {
						this.defProp = "";
					}
				}
				itemElement = (Element) this.document.selectNodes("//datasources//defprop").get(0);
				itemElement.setText(this.defProp);
				break;
			}
		}
	}

	public void updateXML() {
		try {
			String path = IUapUtil.getFilePath() + "config" + File.separator + "datasource.xml";
			File configxml = new File(path);
			Writer fileWriter = new FileWriter(configxml);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(this.document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ByteArrayOutputStream parseInputStream(InputStream in) {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		try {
			while ((ch = in.read()) != -1) {
				swapStream.write(ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return swapStream;
	}

	public void addPropIntoXml(IUapDataSourceInfo info) {
		try {
			Element props = this.document.getRootElement();

			Element propInfo = props.addElement("datasource");

			for (String key : IUapDataSourceInfo.ALL_FILED) {
				Element ele = propInfo.addElement(key);
				ele.setText(info.getField(key));
			}

			this.propMap.put(info.getField("propname"), info);

			resetPropName(getDefProp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetPropName(String fileName) {
		setDefProp(fileName);

		Element itemElement = (Element) this.document.selectNodes("//datasources//datasource").get(0);
		itemElement.setText(getDefProp());
	}

	@SuppressWarnings("rawtypes")
	public void editPropName(IUapDataSourceInfo info) {
		putPropMap(info.getField("propname"), info);
		resetPropName(info.getField("propname"));

		List itemList = this.document.selectNodes("//datasources/datasource");
		List infoLst = null;
		Element item = null;
		for (int x = 0; x < itemList.size(); x++) {
			Element itemElement = (Element) itemList.get(x);
			Element nameElement = itemElement.element("propname");
			if (nameElement.getText().equals(info.getField("propname"))) {
				for (String field : IUapDataSourceInfo.ALL_FILED) {
					infoLst = itemElement.selectNodes(field);

					if ((infoLst != null) && (infoLst.size() > 0))
						item = (Element) infoLst.get(0);
					else {
						item = itemElement.addElement(field);
					}

					item.setText(info.getField(field));
				}
			}
		}
	}

	public String getDefProp() {
		return this.defProp;
	}

	public void setDefProp(String defProp) {
		this.defProp = defProp;
	}

	public Map<String, IUapDataSourceInfo> getPropMap() {
		return this.propMap;
	}

	public void setPropMap(Map<String, IUapDataSourceInfo> propMap) {
		this.propMap = propMap;
	}

	public Map<String, IUapItemsInfo> getItemsMap() {
		return itemsMap;
	}

	public void setItemsMap(Map<String, IUapItemsInfo> itemsMap) {
		this.itemsMap = itemsMap;
	}

	public Map<String, IUapTableItemsInfo> getTableitemsMap() {
		return tableitemsMap;
	}

	public void setTableitemsMap(Map<String, IUapTableItemsInfo> tableitemsMap) {
		this.tableitemsMap = tableitemsMap;
	}

	public void putPropMap(String key, IUapDataSourceInfo info) {
		this.propMap.put(key, info);
	}

	public IUapDataSourceInfo getDefPropInfo(String defPorp) {
		defPorp = defPorp != null ? defPorp : "dev";
		return (IUapDataSourceInfo) this.propMap.get(defPorp);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getInfoNames() {
		List names = new ArrayList();
		for (String key : this.propMap.keySet()) {
			names.add(key);
		}

		return names;
	}

	public IUapDataSourceInfo getPropByName(String name) {
		return (IUapDataSourceInfo) this.propMap.get(name);
	}

}