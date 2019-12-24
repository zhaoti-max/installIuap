package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmFieldref extends IUapBaseVO {

	public String pk_fieldref;
	public String pk_gd;
	public String pk_mdentity;
	public String pk_entityitem;
	public String ref_pkgd;
	public Integer reftype;
	public String treeref_pkgd;
	public String treeparentid;
	public String treelabelfield;
	public String treelistlabelfield;
	public String treeref_foreignkey;
	public Integer dr;
	public String ts;

	public String getPk_fieldref() {
		return pk_fieldref;
	}

	public void setPk_fieldref(String pk_fieldref) {
		this.pk_fieldref = pk_fieldref;
	}

	public String getPk_gd() {
		return pk_gd;
	}

	public void setPk_gd(String pk_gd) {
		this.pk_gd = pk_gd;
	}

	public String getPk_mdentity() {
		return pk_mdentity;
	}

	public void setPk_mdentity(String pk_mdentity) {
		this.pk_mdentity = pk_mdentity;
	}

	public String getPk_entityitem() {
		return pk_entityitem;
	}

	public void setPk_entityitem(String pk_entityitem) {
		this.pk_entityitem = pk_entityitem;
	}

	public String getRef_pkgd() {
		return ref_pkgd;
	}

	public void setRef_pkgd(String ref_pkgd) {
		this.ref_pkgd = ref_pkgd;
	}

	public Integer getReftype() {
		return reftype;
	}

	public void setReftype(Integer reftype) {
		this.reftype = reftype;
	}

	public String getTreeref_pkgd() {
		return treeref_pkgd;
	}

	public void setTreeref_pkgd(String treeref_pkgd) {
		this.treeref_pkgd = treeref_pkgd;
	}

	public String getTreeparentid() {
		return treeparentid;
	}

	public void setTreeparentid(String treeparentid) {
		this.treeparentid = treeparentid;
	}

	public String getTreelabelfield() {
		return treelabelfield;
	}

	public void setTreelabelfield(String treelabelfield) {
		this.treelabelfield = treelabelfield;
	}

	public String getTreelistlabelfield() {
		return treelistlabelfield;
	}

	public void setTreelistlabelfield(String treelistlabelfield) {
		this.treelistlabelfield = treelistlabelfield;
	}

	public String getTreeref_foreignkey() {
		return treeref_foreignkey;
	}

	public void setTreeref_foreignkey(String treeref_foreignkey) {
		this.treeref_foreignkey = treeref_foreignkey;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public String getPkField() {
		return "pk_fieldref";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_fieldref";
	}

}
