package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbAppMenu extends IUapBaseVO {

	public String id;
	public String func_id;
	public String icon;
	public String isenable;
	public String isvisible;
	public String is_virtual_node;
	public String parent_id;
	public String classify;
	public String layout_id;
	public Integer sort;
	public java.sql.Date create_time;
	public String name;
	public String tenant_id;
	public String version;
	public String label;
	public String ts;
	public Integer dr;
	public String openview;
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

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}

	public String getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(String isvisible) {
		this.isvisible = isvisible;
	}

	public String getIs_virtual_node() {
		return is_virtual_node;
	}

	public void setIs_virtual_node(String is_virtual_node) {
		this.is_virtual_node = is_virtual_node;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getLayout_id() {
		return layout_id;
	}

	public void setLayout_id(String layout_id) {
		this.layout_id = layout_id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getOpenview() {
		return openview;
	}

	public void setOpenview(String openview) {
		this.openview = openview;
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
		return "wb_app_menu";
	}

}
