package installIuap.common.base;

import nc.vo.pub.SuperVO;

/**
 * IUAP树结构所需要的bean
 * 
 * @author zhaoti
 * 
 */
public class IUapTreeDataBean extends SuperVO {
	private static final long serialVersionUID = 1L;
	String area_code;
	String area_name;

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getPKFieldName() {
		return null;
	}

	public String getParentPKFieldName() {
		return null;
	}

	public String getTableName() {
		return "wb_areas";
	}
}