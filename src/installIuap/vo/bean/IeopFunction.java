package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class IeopFunction extends IUapBaseVO {

	public String id;
	public String func_name;
	public String func_code;
	public String func_type;
	public String func_url;
	public String iscontrol;
	public String isactive;
	public java.sql.Date create_date;
	public String isleaf;
	public String parent_id;
	public String tenant_id;
	public String sys_id;
	public String enableaction;
	public String creator;
	public String menu_url;
	public String func_name2;
	public String func_name3;
	public String func_name4;
	public String func_name5;
	public String func_name6;
	public String isncc;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getFunc_code() {
		return func_code;
	}

	public void setFunc_code(String func_code) {
		this.func_code = func_code;
	}

	public String getFunc_type() {
		return func_type;
	}

	public void setFunc_type(String func_type) {
		this.func_type = func_type;
	}

	public String getFunc_url() {
		return func_url;
	}

	public void setFunc_url(String func_url) {
		this.func_url = func_url;
	}

	public String getIscontrol() {
		return iscontrol;
	}

	public void setIscontrol(String iscontrol) {
		this.iscontrol = iscontrol;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public java.sql.Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(java.sql.Date create_date) {
		this.create_date = create_date;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public String getEnableaction() {
		return enableaction;
	}

	public void setEnableaction(String enableaction) {
		this.enableaction = enableaction;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getFunc_name2() {
		return func_name2;
	}

	public void setFunc_name2(String func_name2) {
		this.func_name2 = func_name2;
	}

	public String getFunc_name3() {
		return func_name3;
	}

	public void setFunc_name3(String func_name3) {
		this.func_name3 = func_name3;
	}

	public String getFunc_name4() {
		return func_name4;
	}

	public void setFunc_name4(String func_name4) {
		this.func_name4 = func_name4;
	}

	public String getFunc_name5() {
		return func_name5;
	}

	public void setFunc_name5(String func_name5) {
		this.func_name5 = func_name5;
	}

	public String getFunc_name6() {
		return func_name6;
	}

	public void setFunc_name6(String func_name6) {
		this.func_name6 = func_name6;
	}

	public String getIsncc() {
		return isncc;
	}

	public void setIsncc(String isncc) {
		this.isncc = isncc;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "ieop_function";
	}

}
