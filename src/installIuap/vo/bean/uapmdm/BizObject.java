package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class BizObject extends IUapBaseVO {

	public String id;
	public String code;
	public String name;
	public String parentid;
	public String iscatalog;
	public String assigntype;
	public String sysid;
	public String tenantid;
	public String name2;
	public String name3;
	public String name4;
	public String name5;
	public String name6;
	public String mapping_entity;
	public String create_person;
	public String modify_person;
	public java.sql.Date create_date;
	public java.sql.Date modify_date;
	public Integer dr;
	public Integer status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getIscatalog() {
		return iscatalog;
	}

	public void setIscatalog(String iscatalog) {
		this.iscatalog = iscatalog;
	}

	public String getAssigntype() {
		return assigntype;
	}

	public void setAssigntype(String assigntype) {
		this.assigntype = assigntype;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
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

	public String getMapping_entity() {
		return mapping_entity;
	}

	public void setMapping_entity(String mapping_entity) {
		this.mapping_entity = mapping_entity;
	}

	public String getCreate_person() {
		return create_person;
	}

	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}

	public String getModify_person() {
		return modify_person;
	}

	public void setModify_person(String modify_person) {
		this.modify_person = modify_person;
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

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String getPkField() {
		return "id";
	}

	@Override
	public String getTableCode() {
		return "biz_object";
	}

}
