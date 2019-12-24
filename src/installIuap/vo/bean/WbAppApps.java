package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbAppApps extends IUapBaseVO {
	
	public String id;
	public String app_name;
	public String app_index;
	public String group_id;
	public String domain_id;
	public String url;
	public String app_chinese;
	public String app_desc;
	public String app_icon;
	public String app_groupcode;
	public String app_code;
	public String dyna_url;
	public String tenant_id;
	public String urltype;
	public Integer version;
	public String system;
	public String label;
	public String showway;
	public String creator;
	public String reviser;
	public java.sql.Date create_date;
	public java.sql.Date modify_date;
	public String app_name2;
	public String app_name3;
	public String app_name4;
	public String app_name5;
	public String app_name6;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_index() {
		return app_index;
	}

	public void setApp_index(String app_index) {
		this.app_index = app_index;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApp_chinese() {
		return app_chinese;
	}

	public void setApp_chinese(String app_chinese) {
		this.app_chinese = app_chinese;
	}

	public String getApp_desc() {
		return app_desc;
	}

	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}

	public String getApp_icon() {
		return app_icon;
	}

	public void setApp_icon(String app_icon) {
		this.app_icon = app_icon;
	}

	public String getApp_groupcode() {
		return app_groupcode;
	}

	public void setApp_groupcode(String app_groupcode) {
		this.app_groupcode = app_groupcode;
	}

	public String getApp_code() {
		return app_code;
	}

	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}

	public String getDyna_url() {
		return dyna_url;
	}

	public void setDyna_url(String dyna_url) {
		this.dyna_url = dyna_url;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getShowway() {
		return showway;
	}

	public void setShowway(String showway) {
		this.showway = showway;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getReviser() {
		return reviser;
	}

	public void setReviser(String reviser) {
		this.reviser = reviser;
	}

	public java.sql.Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(java.sql.Date create_date) {
		this.create_date = create_date;
	}

	public java.sql.Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(java.sql.Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getApp_name2() {
		return app_name2;
	}

	public void setApp_name2(String app_name2) {
		this.app_name2 = app_name2;
	}

	public String getApp_name3() {
		return app_name3;
	}

	public void setApp_name3(String app_name3) {
		this.app_name3 = app_name3;
	}

	public String getApp_name4() {
		return app_name4;
	}

	public void setApp_name4(String app_name4) {
		this.app_name4 = app_name4;
	}

	public String getApp_name5() {
		return app_name5;
	}

	public void setApp_name5(String app_name5) {
		this.app_name5 = app_name5;
	}

	public String getApp_name6() {
		return app_name6;
	}

	public void setApp_name6(String app_name6) {
		this.app_name6 = app_name6;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "wb_app_apps";
	}

}
