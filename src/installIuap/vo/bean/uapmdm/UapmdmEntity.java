package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmEntity extends IUapBaseVO {

	public String pk_mdentity;
	public String pk_gd;
	public String pk_parent;
	public Integer zztype;
	public String code;
	public String name;
	public String tablename;
	public Integer orderno;
	public String pk_obinfo;
	public String ob_table;
	public String pk_obid;
	public String pk_propname;
	public String ts;
	public Integer dr;
	public Integer usebillcode;
	public String tenant_id;

	public String getPk_mdentity() {
		return pk_mdentity;
	}

	public void setPk_mdentity(String pk_mdentity) {
		this.pk_mdentity = pk_mdentity;
	}

	public String getPk_gd() {
		return pk_gd;
	}

	public void setPk_gd(String pk_gd) {
		this.pk_gd = pk_gd;
	}

	public String getPk_parent() {
		return pk_parent;
	}

	public void setPk_parent(String pk_parent) {
		this.pk_parent = pk_parent;
	}

	public Integer getZztype() {
		return zztype;
	}

	public void setZztype(Integer zztype) {
		this.zztype = zztype;
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

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getPk_obinfo() {
		return pk_obinfo;
	}

	public void setPk_obinfo(String pk_obinfo) {
		this.pk_obinfo = pk_obinfo;
	}

	public String getOb_table() {
		return ob_table;
	}

	public void setOb_table(String ob_table) {
		this.ob_table = ob_table;
	}

	public String getPk_obid() {
		return pk_obid;
	}

	public void setPk_obid(String pk_obid) {
		this.pk_obid = pk_obid;
	}

	public String getPk_propname() {
		return pk_propname;
	}

	public void setPk_propname(String pk_propname) {
		this.pk_propname = pk_propname;
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

	public Integer getUsebillcode() {
		return usebillcode;
	}

	public void setUsebillcode(Integer usebillcode) {
		this.usebillcode = usebillcode;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	@Override
	public String getPkField() {
		return "pk_mdentity";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_entity";
	}

}
