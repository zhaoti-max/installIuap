package installIuap.common.base;

import nc.vo.pub.SuperVO;

/**
 * IUAP树结构所需要的bean
 * 
 * @author zhaoti
 * 
 */
public class IUapMdmTreeDataBean extends SuperVO {
	private static final long serialVersionUID = 1L;
	String code;
	String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPKFieldName() {
		return null;
	}

	public String getParentPKFieldName() {
		return null;
	}

	public String getTableName() {
		return "uapmdm_category";
	}
}