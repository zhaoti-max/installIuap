package installIuap.vo;

import installIuap.vo.bean.IeopFunction;
import installIuap.vo.bean.IeopFunctionActivity;
import installIuap.vo.bean.WbAppApps;
import installIuap.vo.bean.WbAppGroups;
import installIuap.vo.bean.WbAppMenu;
import installIuap.vo.bean.WbAreas;
import installIuap.vo.bean.WbLabelRelation;
import installIuap.vo.bean.WbMenuCollection;
import installIuap.vo.bean.WbMenuGroup;
import installIuap.vo.bean.uapmdm.BizObject;
import installIuap.vo.bean.uapmdm.BizObjectField;
import installIuap.vo.bean.uapmdm.PapBcrElem;
import installIuap.vo.bean.uapmdm.PapBcrRulebase;
import installIuap.vo.bean.uapmdm.PapBcrSn;

import java.util.List;

public class ServerScriptVO {
	private WbAreas wbAreas;
	
	private List<WbAppApps> wbAppAppslist;
	
	private List<WbAppGroups> wbAppGroupslist;
	
	private List<WbLabelRelation> wbLabelRelationlist;
	
	private List<IeopFunction> ieopFunctionlist;
	
	private List<IeopFunctionActivity> ieopFunctionActivitylist;
	
	private List<WbAppMenu> wbAppMenulist;
	
	private List<WbMenuGroup> wbMenuGrouplist;
	
	private List<WbMenuCollection> wbMenuCollectionlist;

	private List<PapBcrRulebase> papBcrRulebaselist;

	private List<PapBcrElem> papBcrElemlist;

	private List<PapBcrSn> papBcrSnlist;

	private List<BizObject> bizObjectlist;

	private List<BizObjectField> bizObjectFieldlist;
	
	public WbAreas getWbAreas() {
		return wbAreas;
	}

	public void setWbAreas(WbAreas wbAreas) {
		this.wbAreas = wbAreas;
	}

	public List<WbAppApps> getWbAppAppslist() {
		return wbAppAppslist;
	}

	public void setWbAppAppslist(List<WbAppApps> wbAppAppslist) {
		this.wbAppAppslist = wbAppAppslist;
	}

	public List<WbAppGroups> getWbAppGroupslist() {
		return wbAppGroupslist;
	}

	public void setWbAppGroupslist(List<WbAppGroups> wbAppGroupslist) {
		this.wbAppGroupslist = wbAppGroupslist;
	}

	public List<WbLabelRelation> getWbLabelRelationlist() {
		return wbLabelRelationlist;
	}

	public void setWbLabelRelationlist(List<WbLabelRelation> wbLabelRelationlist) {
		this.wbLabelRelationlist = wbLabelRelationlist;
	}

	public List<IeopFunction> getIeopFunctionlist() {
		return ieopFunctionlist;
	}

	public void setIeopFunctionlist(List<IeopFunction> ieopFunctionlist) {
		this.ieopFunctionlist = ieopFunctionlist;
	}

	public List<IeopFunctionActivity> getIeopFunctionActivitylist() {
		return ieopFunctionActivitylist;
	}

	public void setIeopFunctionActivitylist(List<IeopFunctionActivity> ieopFunctionActivitylist) {
		this.ieopFunctionActivitylist = ieopFunctionActivitylist;
	}

	public List<WbAppMenu> getWbAppMenulist() {
		return wbAppMenulist;
	}

	public void setWbAppMenulist(List<WbAppMenu> wbAppMenulist) {
		this.wbAppMenulist = wbAppMenulist;
	}

	public List<WbMenuGroup> getWbMenuGrouplist() {
		return wbMenuGrouplist;
	}

	public void setWbMenuGrouplist(List<WbMenuGroup> wbMenuGrouplist) {
		this.wbMenuGrouplist = wbMenuGrouplist;
	}

	public List<WbMenuCollection> getWbMenuCollectionlist() {
		return wbMenuCollectionlist;
	}

	public void setWbMenuCollectionlist(List<WbMenuCollection> wbMenuCollectionlist) {
		this.wbMenuCollectionlist = wbMenuCollectionlist;
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
	
}