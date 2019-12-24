package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbAppGroups extends IUapBaseVO {

	public String id;
	public String name;
	public Integer app_groupindex;
	public String area_id;
	public String tenant_id;
	public String name2;
	public String name3;
	public String name4;
	public String name5;
	public String name6;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getApp_groupindex() {
		return app_groupindex;
	}

	public void setApp_groupindex(Integer app_groupindex) {
		this.app_groupindex = app_groupindex;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getName5() {
		return name5;
	}

	public void setName5(String name5) {
		this.name5 = name5;
	}

	public String getName6() {
		return name6;
	}

	public void setName6(String name6) {
		this.name6 = name6;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "wb_app_groups";
	}

}
