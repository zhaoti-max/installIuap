package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class PapBcrElem extends IUapBaseVO {

	public String pk_billcodeelem;
	public String pk_billcodebase;
	public Integer elemtype;
	public String elemvalue;
	public Integer elemlenth;
	public Integer isrefer;
	public Integer eorder;
	public Integer fillstyle;
	public String datedisplayformat;
	public String fillsign;
	public java.sql.Date createdate;
	
	public String getPk_billcodeelem() {
		return pk_billcodeelem;
	}

	public void setPk_billcodeelem(String pk_billcodeelem) {
		this.pk_billcodeelem = pk_billcodeelem;
	}

	public String getPk_billcodebase() {
		return pk_billcodebase;
	}

	public void setPk_billcodebase(String pk_billcodebase) {
		this.pk_billcodebase = pk_billcodebase;
	}

	public Integer getElemtype() {
		return elemtype;
	}

	public void setElemtype(Integer elemtype) {
		this.elemtype = elemtype;
	}

	public String getElemvalue() {
		return elemvalue;
	}

	public void setElemvalue(String elemvalue) {
		this.elemvalue = elemvalue;
	}

	public Integer getElemlenth() {
		return elemlenth;
	}

	public void setElemlenth(Integer elemlenth) {
		this.elemlenth = elemlenth;
	}

	public Integer getIsrefer() {
		return isrefer;
	}

	public void setIsrefer(Integer isrefer) {
		this.isrefer = isrefer;
	}

	public Integer getEorder() {
		return eorder;
	}

	public void setEorder(Integer eorder) {
		this.eorder = eorder;
	}

	public Integer getFillstyle() {
		return fillstyle;
	}

	public void setFillstyle(Integer fillstyle) {
		this.fillstyle = fillstyle;
	}

	public String getDatedisplayformat() {
		return datedisplayformat;
	}

	public void setDatedisplayformat(String datedisplayformat) {
		this.datedisplayformat = datedisplayformat;
	}

	public String getFillsign() {
		return fillsign;
	}

	public void setFillsign(String fillsign) {
		this.fillsign = fillsign;
	}

	public java.sql.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.sql.Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String getPkField() {
		return "pk_billcodeelem";
	}

	@Override
	public String getTableCode() {
		return "pap_bcr_elem";
	}

}
