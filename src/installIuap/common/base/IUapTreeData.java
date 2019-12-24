package installIuap.common.base;

import installIuap.common.dao.IUapDao;
import installIuap.common.xmlopt.IUapXmlCtrl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import nc.ui.trade.pub.IVOTreeDataByCode;
import nc.vo.pub.SuperVO;

/**
 * IUap构造树结构
 * 
 * @author zhaoti
 * 
 */
public class IUapTreeData implements IVOTreeDataByCode {
	public String getShowFieldName() {
		return "area_code + area_name";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SuperVO[] getTreeVO() {
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		xmlCtrl.parserXml();

		List nodes = new ArrayList();
		try {
			IUapDao dao = new IUapDao(xmlCtrl.getDefPropInfo(null));
			String sql = "select area_code, area_name from wb_areas order by area_code";
			ResultSet rs = dao.queryBySql(sql);
			IUapTreeDataBean node = null;
			while (rs.next()) {
				node = new IUapTreeDataBean();
				node.setArea_code(rs.getString("area_code"));
				node.setArea_name(rs.getString("area_name"));
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (SuperVO[]) nodes.toArray(new IUapTreeDataBean[0]);
	}

	public String getCodeFieldName() {
		return "area_code";
	}

	public String getCodeRule() {
		return "";
	}
}