package installIuap.vo.bean;

import installIuap.vo.IUapBaseVO;

public class WbLabelRelation extends IUapBaseVO {

	public String id;
	public String labelcode;
	public String sourcecode;
	public java.sql.Date create_date;
	public String tenant_id;
	public String system;
	public String lableid;
	public String sourceid;
	public String type;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelcode() {
		return labelcode;
	}

	public void setLabelcode(String labelcode) {
		this.labelcode = labelcode;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public java.sql.Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(java.sql.Date create_date) {
		this.create_date = create_date;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getLableid() {
		return lableid;
	}

	public void setLableid(String lableid) {
		this.lableid = lableid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPkField() {
		return "ID";
	}

	@Override
	public String getTableCode() {
		return "wb_label_relation";
	}

}
