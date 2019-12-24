package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmEnumtype extends IUapBaseVO {

	public String pk_enumtype;
	public String code;
	public String name;
	public String v_range;
	public String pk_obinfo;
	public String ob_enum_id;
	public String descri;
	public Integer orderno;

	public Integer dr;
	public String ts;

	public String getPk_enumtype() {
		return pk_enumtype;
	}

	public void setPk_enumtype(String pk_enumtype) {
		this.pk_enumtype = pk_enumtype;
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

	public String getV_range() {
		return v_range;
	}

	public void setV_range(String v_range) {
		this.v_range = v_range;
	}

	public String getPk_obinfo() {
		return pk_obinfo;
	}

	public void setPk_obinfo(String pk_obinfo) {
		this.pk_obinfo = pk_obinfo;
	}

	public String getOb_enum_id() {
		return ob_enum_id;
	}

	public void setOb_enum_id(String ob_enum_id) {
		this.ob_enum_id = ob_enum_id;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
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
		return "pk_enumtype";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_enumtype";
	}

}
