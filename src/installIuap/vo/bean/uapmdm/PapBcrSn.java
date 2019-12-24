package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class PapBcrSn extends IUapBaseVO {

	public String pk_billcodesn;
	public String pk_billcodebase;
	public String markstr;
	public String lastsn;
	public String markstrdesc;

	public String getPk_billcodesn() {
		return pk_billcodesn;
	}

	public void setPk_billcodesn(String pk_billcodesn) {
		this.pk_billcodesn = pk_billcodesn;
	}

	public String getPk_billcodebase() {
		return pk_billcodebase;
	}

	public void setPk_billcodebase(String pk_billcodebase) {
		this.pk_billcodebase = pk_billcodebase;
	}

	public String getMarkstr() {
		return markstr;
	}

	public void setMarkstr(String markstr) {
		this.markstr = markstr;
	}

	public String getLastsn() {
		return lastsn;
	}

	public void setLastsn(String lastsn) {
		this.lastsn = lastsn;
	}

	public String getMarkstrdesc() {
		return markstrdesc;
	}

	public void setMarkstrdesc(String markstrdesc) {
		this.markstrdesc = markstrdesc;
	}

	@Override
	public String getPkField() {
		return "pk_billcodesn";
	}

	@Override
	public String getTableCode() {
		return "pap_bcr_sn";
	}

}
