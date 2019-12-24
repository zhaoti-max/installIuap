package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbAreas extends IUapBaseVO {

	public String id;
	public String area_code;
	public String area_name;
	public String isenable;
	public int sort;
	public String tenant_id;
	public String area_name2;
	public String area_name3;
	public String area_name4;
	public String area_name5;
	public String area_name6;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getArea_name2() {
		return area_name2;
	}

	public void setArea_name2(String area_name2) {
		this.area_name2 = area_name2;
	}

	public String getArea_name3() {
		return area_name3;
	}

	public void setArea_name3(String area_name3) {
		this.area_name3 = area_name3;
	}

	public String getArea_name4() {
		return area_name4;
	}

	public void setArea_name4(String area_name4) {
		this.area_name4 = area_name4;
	}

	public String getArea_name5() {
		return area_name5;
	}

	public void setArea_name5(String area_name5) {
		this.area_name5 = area_name5;
	}

	public String getArea_name6() {
		return area_name6;
	}

	public void setArea_name6(String area_name6) {
		this.area_name6 = area_name6;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "wb_areas";
	}

}
