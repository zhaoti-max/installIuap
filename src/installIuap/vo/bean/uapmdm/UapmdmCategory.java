package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmCategory extends IUapBaseVO {
	
	public String pk_category;
	public String pk_parent;
	public String code;
	public String name;
	public Integer builtin;
	public Integer orderno;
	public Integer dr;
	public String ts;
	
	public String getPk_category() {
		return pk_category;
	}

	public void setPk_category(String pk_category) {
		this.pk_category = pk_category;
	}

	public String getPk_parent() {
		return pk_parent;
	}

	public void setPk_parent(String pk_parent) {
		this.pk_parent = pk_parent;
	}

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

	public Integer getBuiltin() {
		return builtin;
	}

	public void setBuiltin(Integer builtin) {
		this.builtin = builtin;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public String getPkField() {
		return "pk_category";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_category";
	}

}
