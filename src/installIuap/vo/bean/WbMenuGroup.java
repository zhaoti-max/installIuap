package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbMenuGroup extends IUapBaseVO {

	public String id;
	public String menu_id;
	public String pk_info;
	public String tenant_id;
	public String sys_id;
	public java.sql.Date create_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getPk_info() {
		return pk_info;
	}

	public void setPk_info(String pk_info) {
		this.pk_info = pk_info;
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

	public java.sql.Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(java.sql.Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "wb_menu_group";
	}

}
