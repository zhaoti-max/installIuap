package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmEntityitems extends IUapBaseVO {

	public String pk_entityitem;
	public String pk_mdentity;
	public String code;
	public String name;
	public Integer fieldtype;
	public String fieldlength;
	public Integer isunique;
	public Integer queryatt;
	public Integer required;
	public Integer isarray;
	public String pk_obinfo;
	public String pk_obid;
	public String ob_column_id;
	public String ob_table;
	public String ob_column;
	public String ob_code;
	public String ref_pkgd;
	public Integer isvisible;
	public String defaultvalue;
	public Integer isvisibleonreflist;
	public String pk_validate_rule;
	public String selfdefinename;
	public Integer selfdefinewidth;
	public Integer selfdefineheight;
	public Integer validatetype;
	public Integer billcodetype;
	public String regexvalidateclass;
	public String validateprompt;
	public Integer orderno;
	public String ts;
	public Integer dr;
	public String tenant_id;

	public String getPk_entityitem() {
		return pk_entityitem;
	}

	public void setPk_entityitem(String pk_entityitem) {
		this.pk_entityitem = pk_entityitem;
	}

	public String getPk_mdentity() {
		return pk_mdentity;
	}

	public void setPk_mdentity(String pk_mdentity) {
		this.pk_mdentity = pk_mdentity;
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

	public Integer getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}

	public String getFieldlength() {
		return fieldlength;
	}

	public void setFieldlength(String fieldlength) {
		this.fieldlength = fieldlength;
	}

	public Integer getIsunique() {
		return isunique;
	}

	public void setIsunique(Integer isunique) {
		this.isunique = isunique;
	}

	public Integer getQueryatt() {
		return queryatt;
	}

	public void setQueryatt(Integer queryatt) {
		this.queryatt = queryatt;
	}

	public Integer getRequired() {
		return required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	public Integer getIsarray() {
		return isarray;
	}

	public void setIsarray(Integer isarray) {
		this.isarray = isarray;
	}

	public String getPk_obinfo() {
		return pk_obinfo;
	}

	public void setPk_obinfo(String pk_obinfo) {
		this.pk_obinfo = pk_obinfo;
	}

	public String getPk_obid() {
		return pk_obid;
	}

	public void setPk_obid(String pk_obid) {
		this.pk_obid = pk_obid;
	}

	public String getOb_column_id() {
		return ob_column_id;
	}

	public void setOb_column_id(String ob_column_id) {
		this.ob_column_id = ob_column_id;
	}

	public String getOb_table() {
		return ob_table;
	}

	public void setOb_table(String ob_table) {
		this.ob_table = ob_table;
	}

	public String getOb_column() {
		return ob_column;
	}

	public void setOb_column(String ob_column) {
		this.ob_column = ob_column;
	}

	public String getOb_code() {
		return ob_code;
	}

	public void setOb_code(String ob_code) {
		this.ob_code = ob_code;
	}

	public String getRef_pkgd() {
		return ref_pkgd;
	}

	public void setRef_pkgd(String ref_pkgd) {
		this.ref_pkgd = ref_pkgd;
	}

	public Integer getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(Integer isvisible) {
		this.isvisible = isvisible;
	}

	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public Integer getIsvisibleonreflist() {
		return isvisibleonreflist;
	}

	public void setIsvisibleonreflist(Integer isvisibleonreflist) {
		this.isvisibleonreflist = isvisibleonreflist;
	}

	public String getPk_validate_rule() {
		return pk_validate_rule;
	}

	public void setPk_validate_rule(String pk_validate_rule) {
		this.pk_validate_rule = pk_validate_rule;
	}

	public String getSelfdefinename() {
		return selfdefinename;
	}

	public void setSelfdefinename(String selfdefinename) {
		this.selfdefinename = selfdefinename;
	}

	public Integer getSelfdefinewidth() {
		return selfdefinewidth;
	}

	public void setSelfdefinewidth(Integer selfdefinewidth) {
		this.selfdefinewidth = selfdefinewidth;
	}

	public Integer getSelfdefineheight() {
		return selfdefineheight;
	}

	public void setSelfdefineheight(Integer selfdefineheight) {
		this.selfdefineheight = selfdefineheight;
	}

	public Integer getValidatetype() {
		return validatetype;
	}

	public void setValidatetype(Integer validatetype) {
		this.validatetype = validatetype;
	}

	public Integer getBillcodetype() {
		return billcodetype;
	}

	public void setBillcodetype(Integer billcodetype) {
		this.billcodetype = billcodetype;
	}

	public String getRegexvalidateclass() {
		return regexvalidateclass;
	}

	public void setRegexvalidateclass(String regexvalidateclass) {
		this.regexvalidateclass = regexvalidateclass;
	}

	public String getValidateprompt() {
		return validateprompt;
	}

	public void setValidateprompt(String validateprompt) {
		this.validateprompt = validateprompt;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	@Override
	public String getPkField() {
		return "PK_ENTITYITEM";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_entityitems";
	}

}
