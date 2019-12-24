package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class UapmdmDesigninfo extends IUapBaseVO {

	public String pk_gd;
	public String pk_category;
	public String code;
	public String name;
	public Integer uistyle;
	public Integer isworkflow;
	public Integer isstart_us_v;
	public String treeref_foreignkey;
	public String treeparentid;
	public String treemasterid;
	public String treeref_pkgd;
	public String link_pkgd;
	public String link_field;
	public Integer orderno;
	public String pk_creator;
	public String createtime;
	public String pk_modifier;
	public Integer dr;
	public Integer publish;
	public Integer state;
	public Integer isstatistics;
	public String ts;
	
	public String getPk_gd() {
		return pk_gd;
	}

	public void setPk_gd(String pk_gd) {
		this.pk_gd = pk_gd;
	}

	public String getPk_category() {
		return pk_category;
	}

	public void setPk_category(String pk_category) {
		this.pk_category = pk_category;
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

	public Integer getUistyle() {
		return uistyle;
	}

	public void setUistyle(Integer uistyle) {
		this.uistyle = uistyle;
	}

	public Integer getIsworkflow() {
		return isworkflow;
	}

	public void setIsworkflow(Integer isworkflow) {
		this.isworkflow = isworkflow;
	}

	public Integer getIsstart_us_v() {
		return isstart_us_v;
	}

	public void setIsstart_us_v(Integer isstart_us_v) {
		this.isstart_us_v = isstart_us_v;
	}

	public String getTreeref_foreignkey() {
		return treeref_foreignkey;
	}

	public void setTreeref_foreignkey(String treeref_foreignkey) {
		this.treeref_foreignkey = treeref_foreignkey;
	}

	public String getTreeparentid() {
		return treeparentid;
	}

	public void setTreeparentid(String treeparentid) {
		this.treeparentid = treeparentid;
	}

	public String getTreemasterid() {
		return treemasterid;
	}

	public void setTreemasterid(String treemasterid) {
		this.treemasterid = treemasterid;
	}

	public String getTreeref_pkgd() {
		return treeref_pkgd;
	}

	public void setTreeref_pkgd(String treeref_pkgd) {
		this.treeref_pkgd = treeref_pkgd;
	}

	public String getLink_pkgd() {
		return link_pkgd;
	}

	public void setLink_pkgd(String link_pkgd) {
		this.link_pkgd = link_pkgd;
	}

	public String getLink_field() {
		return link_field;
	}

	public void setLink_field(String link_field) {
		this.link_field = link_field;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getPk_creator() {
		return pk_creator;
	}

	public void setPk_creator(String pk_creator) {
		this.pk_creator = pk_creator;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPk_modifier() {
		return pk_modifier;
	}

	public void setPk_modifier(String pk_modifier) {
		this.pk_modifier = pk_modifier;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsstatistics() {
		return isstatistics;
	}

	public void setIsstatistics(Integer isstatistics) {
		this.isstatistics = isstatistics;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public String getPkField() {
		return "pk_gd";
	}

	@Override
	public String getTableCode() {
		return "uapmdm_designinfo";
	}

}
