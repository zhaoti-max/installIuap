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
public class IUapMdmTreeData implements IVOTreeDataByCode {
	public String getShowFieldName() {
		return "code + name";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SuperVO[] getTreeVO() {
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		xmlCtrl.parserXml();

		List nodes = new ArrayList();
		try {
			IUapDao dao = new IUapDao(xmlCtrl.getDefPropInfo(null));
			String sql = "select code, name from uapmdm_category order by code";
			ResultSet rs = dao.queryBySql(sql);
			IUapMdmTreeDataBean node = null;
			while (rs.next()) {
				node = new IUapMdmTreeDataBean();
				node.setCode(rs.getString("code"));
				node.setName(rs.getString("name"));
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (SuperVO[]) nodes.toArray(new IUapMdmTreeDataBean[0]);
	}

	public String getCodeFieldName() {
		return "code";
	}

	public String getCodeRule() {
		return "";
	}
}