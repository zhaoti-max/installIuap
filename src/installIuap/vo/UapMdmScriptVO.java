package installIuap.vo;

import installIuap.vo.bean.WbLabelRelation;
import installIuap.vo.bean.uapmdm.BizObject;
import installIuap.vo.bean.uapmdm.BizObjectField;
import installIuap.vo.bean.uapmdm.PapBcrElem;
import installIuap.vo.bean.uapmdm.PapBcrRulebase;
import installIuap.vo.bean.uapmdm.PapBcrSn;
import installIuap.vo.bean.uapmdm.UapmdmCategory;
import installIuap.vo.bean.uapmdm.UapmdmDesigninfo;
import installIuap.vo.bean.uapmdm.UapmdmEntity;
import installIuap.vo.bean.uapmdm.UapmdmEntityitems;
import installIuap.vo.bean.uapmdm.UapmdmEnumtype;
import installIuap.vo.bean.uapmdm.UapmdmEnumvalue;
import installIuap.vo.bean.uapmdm.UapmdmFieldref;

import java.util.List;

public class UapMdmScriptVO {

	private List<UapmdmCategory> uapmdmCategorylist;

	private List<UapmdmDesigninfo> uapmdmDesigninfolist;

	private List<UapmdmEntity> uapmdmEntitylist;

	private List<UapmdmEntityitems> uapmdmEntityitemslist;

	private List<UapmdmFieldref> uapmdmFieldreflist;
	
	private List<UapmdmEnumtype> uapmdmEnumtypelist;
	
	private List<UapmdmEnumvalue> uapmdmEnumvaluelist;

	private List<PapBcrRulebase> papBcrRulebaselist;

	private List<PapBcrElem> papBcrElemlist;

	private List<PapBcrSn> papBcrSnlist;

	private List<BizObject> bizObjectlist;

	private List<BizObjectField> bizObjectFieldlist;

	private List<WbLabelRelation> wbLabelRelationlist;

	public List<UapmdmCategory> getUapmdmCategorylist() {
		return uapmdmCategorylist;
	}

	public void setUapmdmCategorylist(List<UapmdmCategory> uapmdmCategorylist) {
		this.uapmdmCategorylist = uapmdmCategorylist;
	}

	public List<UapmdmDesigninfo> getUapmdmDesigninfolist() {
		return uapmdmDesigninfolist;
	}

	public void setUapmdmDesigninfolist(List<UapmdmDesigninfo> uapmdmDesigninfolist) {
		this.uapmdmDesigninfolist = uapmdmDesigninfolist;
	}

	public List<UapmdmEntity> getUapmdmEntitylist() {
		return uapmdmEntitylist;
	}

	public void setUapmdmEntitylist(List<UapmdmEntity> uapmdmEntitylist) {
		this.uapmdmEntitylist = uapmdmEntitylist;
	}

	public List<UapmdmEntityitems> getUapmdmEntityitemslist() {
		return uapmdmEntityitemslist;
	}

	public void setUapmdmEntityitemslist(List<UapmdmEntityitems> uapmdmEntityitemslist) {
		this.uapmdmEntityitemslist = uapmdmEntityitemslist;
	}

	public List<UapmdmFieldref> getUapmdmFieldreflist() {
		return uapmdmFieldreflist;
	}

	public void setUapmdmFieldreflist(List<UapmdmFieldref> uapmdmFieldreflist) {
		this.uapmdmFieldreflist = uapmdmFieldreflist;
	}
	

	public List<UapmdmEnumtype> getUapmdmEnumtypelist() {
		return uapmdmEnumtypelist;
	}

	public void setUapmdmEnumtypelist(List<UapmdmEnumtype> uapmdmEnumtypelist) {
		this.uapmdmEnumtypelist = uapmdmEnumtypelist;
	}

	public List<UapmdmEnumvalue> getUapmdmEnumvaluelist() {
		return uapmdmEnumvaluelist;
	}

	public void setUapmdmEnumvaluelist(List<UapmdmEnumvalue> uapmdmEnumvaluelist) {
		this.uapmdmEnumvaluelist = uapmdmEnumvaluelist;
	}

	public List<PapBcrRulebase> getPapBcrRulebaselist() {
		return papBcrRulebaselist;
	}

	public void setPapBcrRulebaselist(List<PapBcrRulebase> papBcrRulebaselist) {
		this.papBcrRulebaselist = papBcrRulebaselist;
	}

	public List<PapBcrElem> getPapBcrElemlist() {
		return papBcrElemlist;
	}

	public void setPapBcrElemlist(List<PapBcrElem> papBcrElemlist) {
		this.papBcrElemlist = papBcrElemlist;
	}

	public List<PapBcrSn> getPapBcrSnlist() {
		return papBcrSnlist;
	}

	public void setPapBcrSnlist(List<PapBcrSn> papBcrSnlist) {
		this.papBcrSnlist = papBcrSnlist;
	}

	public List<BizObject> getBizObjectlist() {
		return bizObjectlist;
	}

	public void setBizObjectlist(List<BizObject> bizObjectlist) {
		this.bizObjectlist = bizObjectlist;
	}

	public List<BizObjectField> getBizObjectFieldlist() {
		return bizObjectFieldlist;
	}

	public void setBizObjectFieldlist(List<BizObjectField> bizObjectFieldlist) {
		this.bizObjectFieldlist = bizObjectFieldlist;
	}

	public List<WbLabelRelation> getWbLabelRelationlist() {
		return wbLabelRelationlist;
	}

	public void setWbLabelRelationlist(List<WbLabelRelation> wbLabelRelationlist) {
		this.wbLabelRelationlist = wbLabelRelationlist;
	}

}