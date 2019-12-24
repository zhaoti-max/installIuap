package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class IeopFunctionActivity extends IUapBaseVO {

	public String id;
	public String activity_name;
	public String activity_code;
	public String activity_url;
	public String func_id;
	public String isactive;
	public java.sql.Date create_date;
	public String sys_id;
	public String tenant_id;
	public String activity_name2;
	public String activity_name3;
	public String activity_name4;
	public String activity_name5;
	public String activity_name6;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_code() {
		return activity_code;
	}

	public void setActivity_code(String activity_code) {
		this.activity_code = activity_code;
	}

	public String getActivity_url() {
		return activity_url;
	}

	public void setActivity_url(String activity_url) {
		this.activity_url = activity_url;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
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

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getActivity_name2() {
		return activity_name2;
	}

	public void setActivity_name2(String activity_name2) {
		this.activity_name2 = activity_name2;
	}

	public String getActivity_name3() {
		return activity_name3;
	}

	public void setActivity_name3(String activity_name3) {
		this.activity_name3 = activity_name3;
	}

	public String getActivity_name4() {
		return activity_name4;
	}

	public void setActivity_name4(String activity_name4) {
		this.activity_name4 = activity_name4;
	}

	public String getActivity_name5() {
		return activity_name5;
	}

	public void setActivity_name5(String activity_name5) {
		this.activity_name5 = activity_name5;
	}

	public String getActivity_name6() {
		return activity_name6;
	}

	public void setActivity_name6(String activity_name6) {
		this.activity_name6 = activity_name6;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "ieop_function_activity";
	}

}
