package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbMenuCollection extends IUapBaseVO {

	public String id;
	public String menu_id;
	public String user_id;
	public String tenant_id;
	public Integer sort;
	public java.sql.Date create_time;
	public java.sql.Date ts;
	public Integer dr;

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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public java.sql.Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(java.sql.Date create_time) {
		this.create_time = create_time;
	}

	public java.sql.Date getTs() {
		return ts;
	}

	public void setTs(java.sql.Date ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "wb_menu_collection";
	}

}
