package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class PapBcrRulebase extends IUapBaseVO {

	public String pk_billcodebase;
	public String pkbillobj;
	public String rulecode;
	public String rulename;
	public String codemode;
	public String iseditable;
	public String isautofill;
	public String format;
	public String isdefault;
	public String isused;
	public String islenvar;
	public String isgetpk;
	public String renterid;
	public String sysid;
	public java.sql.Date createdate;
	public String rulename2;
	public String rulename3;
	public String rulename4;
	public String rulename5;
	public String rulename6;
	
	public String getPk_billcodebase() {
		return pk_billcodebase;
	}

	public void setPk_billcodebase(String pk_billcodebase) {
		this.pk_billcodebase = pk_billcodebase;
	}

	public String getPkbillobj() {
		return pkbillobj;
	}

	public void setPkbillobj(String pkbillobj) {
		this.pkbillobj = pkbillobj;
	}

	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getCodemode() {
		return codemode;
	}

	public void setCodemode(String codemode) {
		this.codemode = codemode;
	}

	public String getIseditable() {
		return iseditable;
	}

	public void setIseditable(String iseditable) {
		this.iseditable = iseditable;
	}

	public String getIsautofill() {
		return isautofill;
	}

	public void setIsautofill(String isautofill) {
		this.isautofill = isautofill;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getIslenvar() {
		return islenvar;
	}

	public void setIslenvar(String islenvar) {
		this.islenvar = islenvar;
	}

	public String getIsgetpk() {
		return isgetpk;
	}

	public void setIsgetpk(String isgetpk) {
		this.isgetpk = isgetpk;
	}

	public String getRenterid() {
		return renterid;
	}

	public void setRenterid(String renterid) {
		this.renterid = renterid;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public java.sql.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.sql.Date createdate) {
		this.createdate = createdate;
	}

	public String getRulename2() {
		return rulename2;
	}

	public void setRulename2(String rulename2) {
		this.rulename2 = rulename2;
	}

	public String getRulename3() {
		return rulename3;
	}

	public void setRulename3(String rulename3) {
		this.rulename3 = rulename3;
	}

	public String getRulename4() {
		return rulename4;
	}

	public void setRulename4(String rulename4) {
		this.rulename4 = rulename4;
	}

	public String getRulename5() {
		return rulename5;
	}

	public void setRulename5(String rulename5) {
		this.rulename5 = rulename5;
	}

	public String getRulename6() {
		return rulename6;
	}

	public void setRulename6(String rulename6) {
		this.rulename6 = rulename6;
	}

	@Override
	public String getPkField() {
		return "pk_billcodebase";
	}

	@Override
	public String getTableCode() {
		return "pap_bcr_rulebase";
	}

}
