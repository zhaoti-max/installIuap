package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmEnumvalue extends IUapBaseVO {

	public String pk_enumvalue;
	public String pk_enumtype;
	public String category_code;
	public String code;
	public String name;
	public String enum_value;
	public String pk_obinfo;
	public String ob_enum_id;
	public String ob_enumitem_id;
	public String descri;
	public Integer orderno;
	public Integer dr;
	public String ts;

	public String getPk_enumvalue() {
		return pk_enumvalue;
	}

	public void setPk_enumvalue(String pk_enumvalue) {
		this.pk_enumvalue = pk_enumvalue;
	}

	public String getPk_enumtype() {
		return pk_enumtype;
	}

	public void setPk_enumtype(String pk_enumtype) {
		this.pk_enumtype = pk_enumtype;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
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

	public String getEnum_value() {
		return enum_value;
	}

	public void setEnum_value(String enum_value) {
		this.enum_value = enum_value;
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

	public String getOb_enumitem_id() {
		return ob_enumitem_id;
	}

	public void setOb_enumitem_id(String ob_enumitem_id) {
		this.ob_enumitem_id = ob_enumitem_id;
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
		return "pk_enumvalue";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_enumvalue";
	}

}
