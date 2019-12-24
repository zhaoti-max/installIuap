package installIuap.common.helper;

import installIuap.common.dao.IUapDao;
import installIuap.common.tools.IUapVOTools;
import installIuap.common.xmlopt.IUapXmlCtrl;
import installIuap.consts.IUapConsts;
import installIuap.vo.ServerScriptVO;
import installIuap.vo.UapMdmScriptVO;
import installIuap.vo.bean.IUapDataSourceInfo;
import installIuap.vo.bean.IUapItemsInfo;
import installIuap.vo.bean.IUapTableItemsInfo;
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
import installIuap.vo.bean.uapmdm.UapmdmCategory;
import installIuap.vo.bean.uapmdm.UapmdmDesigninfo;
import installIuap.vo.bean.uapmdm.UapmdmEntity;
import installIuap.vo.bean.uapmdm.UapmdmEntityitems;
import installIuap.vo.bean.uapmdm.UapmdmEnumtype;
import installIuap.vo.bean.uapmdm.UapmdmEnumvalue;
import installIuap.vo.bean.uapmdm.UapmdmFieldref;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.jdbc.framework.processor.ArrayListProcessor;

/**
 * IUAP脚本抽取
 * 
 * @author zhaoti
 * 
 */
public class IUapScriptHelper {

	public String exportCreateTableScript(String tablename, List<String[]> column_list, List<String[]> col_comments_list, boolean bprimarykey, Integer data_type)
			throws SQLException {
		StringBuffer createtablesqls = new StringBuffer();
		createtablesqls.append(dataTitle(1)).append("\n");
		StringBuffer createsql = new StringBuffer();
		if (IUapConsts.IUAP_DATA_TYPE.ORACEL == data_type) {
			createsql.append("create table ").append(tablename).append("( ");
			createsql.append(buildCreateSqls(column_list));
			createtablesqls.append(createsql.toString().substring(0, createsql.toString().length() - 1));
			createtablesqls.append(IUapConsts.TABLE_SPACE_REMARK);
			StringBuffer commentsql = new StringBuffer();
			commentsql.append(buildCommentSqls(col_comments_list));
			createtablesqls.append(commentsql.toString()).append("\n");
			if (bprimarykey) {
				createtablesqls.append("alter table ").append(tablename).append(" add constraint PK_").append(tablename).append(" primary key (ID)");
				createtablesqls.append(IUapConsts.TABLE_INDEX_REMARK).append("\n");
			}
		} else if (IUapConsts.IUAP_DATA_TYPE.MYSQL == data_type) {
			createsql.append("create table ").append(tablename).append("( ");
			createsql.append(buildCreateMySqls(column_list, col_comments_list));
			createtablesqls.append(createsql.toString().substring(0, createsql.toString().length() - 1));
			createtablesqls.append("\n");
		}
		return createtablesqls.toString();
	}

	private Object buildCreateMySqls(List<String[]> column_list, List<String[]> col_comments_list) {
		Map<Object, Object> commentMap = new HashMap<Object, Object>();
		if (col_comments_list != null && col_comments_list.size() > 0) {
			for (Object[] col_comments : col_comments_list) {
				if (col_comments[1] != null && col_comments[1].toString().length() > 0) {
					commentMap.put(col_comments[1], col_comments[2]);
				}
			}
		}

		StringBuffer createsql = new StringBuffer();
		if (column_list != null && column_list.size() > 0) {
			for (Object[] columns : column_list) {
				switch (columns[1].toString()) {
				case IUapConsts.VARCHAR2:
				case IUapConsts.NVARCHAR2:
					if (columns[5].equals("N")) {
						createsql.append("\n").append(" ");
						createsql.append(" `").append(columns[0]).append("` varchar(").append(columns[2]).append(") NOT NULL COMMENT '主键',");
					} else {
						createsql.append("\n").append(" ");
						createsql.append(" `").append(columns[0]).append("` varchar(").append(columns[2]).append(") DEFAULT NULL COMMENT '").append(commentMap.get(columns[0]))
								.append("',");
					}
					break;
				case IUapConsts.NUMBER:
					if (columns[3] == null || columns[3].toString().length() <= 0) {
						createsql.append("\n").append(" ");
						createsql.append(" `").append(columns[0]).append("` int(11) DEFAULT NULL COMMENT '").append(commentMap.get(columns[0])).append("',");
					} else if (columns[4] == null || columns[4].toString().length() <= 0) {
						createsql.append("\n").append(" ");
						createsql.append(" `").append(columns[0]).append("` numeric(").append(columns[3]).append(") DEFAULT NULL COMMENT '").append(commentMap.get(columns[0]))
								.append("',");
					} else {
						createsql.append("\n").append(" ");
						createsql.append(" `").append(columns[0]).append("` numeric(").append(columns[3]).append(",").append(columns[4]).append(") DEFAULT NULL COMMENT '")
								.append(commentMap.get(columns[0])).append("',");
					}
					break;
				case IUapConsts.FLOAT:
					createsql.append("\n").append(" ");
					createsql.append(" `").append(columns[0]).append("` float DEFAULT NULL COMMENT '").append(commentMap.get(columns[0])).append("',");
					break;
				case IUapConsts.CHAR:
				case IUapConsts.NCHAR:
					createsql.append("\n").append(" ");
					createsql.append(" `").append(columns[0]).append("` char(").append(columns[2]).append(") DEFAULT NULL COMMENT '").append(commentMap.get(columns[0]))
							.append("',");
					break;
				case IUapConsts.BLOB:
				case IUapConsts.NCLOB:
				case IUapConsts.CLOB:
				case IUapConsts.DATE:
					createsql.append("\n").append(" ");
					createsql.append(" `").append(columns[0]).append("` ").append(columns[1]).append(" DEFAULT NULL COMMENT '").append(commentMap.get(columns[0])).append("',");
					break;
				default:
					break;
				}
			}
		}
		return createsql;
	}

	public String exportDataScript(String serverName, boolean bcheckMenu) throws Exception {
		StringBuffer dataInitsqls = new StringBuffer();
		dataInitsqls.append(dataTitle(2)).append("\n");
		dataInitsqls.append(dataInitsqls(serverName, bcheckMenu));
		return dataInitsqls.toString();
	}

	public String exportVirtualMemuDataScript(String serverName) throws Exception {
		StringBuffer virtualmenuInitsqls = new StringBuffer();
		virtualmenuInitsqls.append(dataTitle(3)).append("\n");
		// virtualmenuInitsqls.append("/*======================================== 0.清空数据========================================*/").append("\n");
		virtualmenuInitsqls.append("delete from wb_app_menu where is_virtual_node = 'Y' and func_id = '" + serverName + "' or parent_id in (select id from wb_app_menu where is_virtual_node = 'Y' and func_id = '" + serverName + "' );");
		virtualmenuInitsqls.append("\n");
		virtualmenuInitsqls.append("commit;");
		virtualmenuInitsqls.append("\n");
		// virtualmenuInitsqls.append("/*======================================== 1.微服务虚拟菜单的脚本========================================*/").append("\n");
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
		IUapDao dao = new IUapDao(info);
		IUapVOTools voTools = new IUapVOTools();
		ResultSet rs = dao.queryBySql(new WbAppMenu().getQrySqlByCondition(" is_virtual_node = 'Y'and func_id = '" + serverName + "' or parent_id in (select id from wb_app_menu where is_virtual_node = 'Y' and func_id = '" + serverName + "' )"));
		List<WbAppMenu> wbAppMenulist = voTools.setInfo2Vos(WbAppMenu.class, rs);
		if (wbAppMenulist != null && wbAppMenulist.size() > 0) {
			for (WbAppMenu wbappmenu : wbAppMenulist) {
				virtualmenuInitsqls.append(wbappmenu.createSqlByVO()).append("\n");
			}
		}
		// virtualmenuInitsqls.append("/*======================================== 2.提交脚本========================================*/").append("\n");
		virtualmenuInitsqls.append("commit;").append("\n");
		return virtualmenuInitsqls.toString();
	}

	public String exportUapmdmDataScript(String mdmcode, Integer data_type, boolean bcheckMdm) throws Exception {
		StringBuffer uapmdmInitsqls = new StringBuffer();
		uapmdmInitsqls.append(dataTitle(4)).append("\n");
		// uapmdmInitsqls.append("/*======================================== 0.清空数据========================================*/").append("\n");
		uapmdmInitsqls.append(deleteUapmdmDataSqls(mdmcode));
		// uapmdmInitsqls.append("/*======================================== 1.微服务自定义档案数据的脚本 ========================================*/").append("\n");
		uapmdmInitsqls.append(initUapmdmdatasqls(mdmcode));
		// uapmdmInitsqls.append("/*======================================== 2.微服务自定义档案创建表的脚本 ========================================*/").append("\n");
		uapmdmInitsqls.append(createUapmdmsqls(mdmcode, data_type, bcheckMdm));
		return uapmdmInitsqls.toString();
	}

	private String deleteUapmdmDataSqls(String mdmcode) {
		StringBuffer deleteupmdmdatasqls = new StringBuffer();
		deleteupmdmdatasqls.append("delete from uapmdm_category where code = '" + mdmcode + "';").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append("delete from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode + "');").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append(
				"delete from uapmdm_entity where pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from uapmdm_entityitems where pk_mdentity in (select pk_mdentity from uapmdm_entity where pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append(
				"delete from uapmdm_fieldref where pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append("delete from uapmdm_enumvalue;").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append("delete from uapmdm_enumtype;").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls.append(
				"delete from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode
						+ "'));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from pap_bcr_rulebase where pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from pap_bcr_elem pk_billcodebase in(select pk_billcodebase from pap_bcr_rulebase where pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from pap_bcr_sn where pk_billcodebase in(select pk_billcodebase from pap_bcr_rulebase where pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from biz_object_field where pk_biz_obj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		deleteupmdmdatasqls
				.append("delete from wb_label_relation where sourceid in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')));").append("\n");
		deleteupmdmdatasqls.append("commit;").append("\n");
		return deleteupmdmdatasqls.toString();
	}

	private String initUapmdmdatasqls(String mdmcode) throws Exception {
		StringBuffer inituapmdmdatasqls = new StringBuffer();
		UapMdmScriptVO uapmdmScriptVo = findUapDmdScript(mdmcode);
		// inituapmdmdatasqls.append("/*======================================== 2.1.自定义档案树的虚节点的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmCategorylist() != null && uapmdmScriptVo.getUapmdmCategorylist().size() > 0) {
			for (UapmdmCategory uapmdmCategory : uapmdmScriptVo.getUapmdmCategorylist()) {
				inituapmdmdatasqls.append(uapmdmCategory.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.2自定义档案的设计信息的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmDesigninfolist() != null && uapmdmScriptVo.getUapmdmDesigninfolist().size() > 0) {
			for (UapmdmDesigninfo uapmdmDesigninfo : uapmdmScriptVo.getUapmdmDesigninfolist()) {
				inituapmdmdatasqls.append(uapmdmDesigninfo.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.3存在字段的自定义档案的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmEntitylist() != null && uapmdmScriptVo.getUapmdmEntitylist().size() > 0) {
			for (UapmdmEntity uapmdmEntity : uapmdmScriptVo.getUapmdmEntitylist()) {
				inituapmdmdatasqls.append(uapmdmEntity.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.4自定义档案所包含的字段的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmEntityitemslist() != null && uapmdmScriptVo.getUapmdmEntityitemslist().size() > 0) {
			for (UapmdmEntityitems uapmdmEntityitems : uapmdmScriptVo.getUapmdmEntityitemslist()) {
				inituapmdmdatasqls.append(uapmdmEntityitems.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.5带参照的自定义档案的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmFieldreflist() != null && uapmdmScriptVo.getUapmdmFieldreflist().size() > 0) {
			for (UapmdmFieldref uapmdmFieldref : uapmdmScriptVo.getUapmdmFieldreflist()) {
				inituapmdmdatasqls.append(uapmdmFieldref.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.6自定义档案枚举类型的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmEnumtypelist() != null && uapmdmScriptVo.getUapmdmEnumtypelist().size() > 0) {
			for (UapmdmEnumtype uapmdmEnumtype : uapmdmScriptVo.getUapmdmEnumtypelist()) {
				inituapmdmdatasqls.append(uapmdmEnumtype.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.7自定义档案枚举值的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getUapmdmEnumvaluelist() != null && uapmdmScriptVo.getUapmdmEnumvaluelist().size() > 0) {
			for (UapmdmEnumvalue uapmdmEnumvalue : uapmdmScriptVo.getUapmdmEnumvaluelist()) {
				inituapmdmdatasqls.append(uapmdmEnumvalue.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.8自定义档案实体的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getBizObjectlist() != null && uapmdmScriptVo.getBizObjectlist().size() > 0) {
			for (BizObject bizObject : uapmdmScriptVo.getBizObjectlist()) {
				inituapmdmdatasqls.append(bizObject.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.9自定义档案编码规则的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getPapBcrRulebaselist() != null && uapmdmScriptVo.getPapBcrRulebaselist().size() > 0) {
			for (PapBcrRulebase papBcrRulebase : uapmdmScriptVo.getPapBcrRulebaselist()) {
				inituapmdmdatasqls.append(papBcrRulebase.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.10自定义档案编码规则元素的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getPapBcrElemlist() != null && uapmdmScriptVo.getPapBcrElemlist().size() > 0) {
			for (PapBcrElem papBcrElem : uapmdmScriptVo.getPapBcrElemlist()) {
				inituapmdmdatasqls.append(papBcrElem.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.11自定义档案编码规则定义的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getPapBcrSnlist() != null && uapmdmScriptVo.getPapBcrSnlist().size() > 0) {
			for (PapBcrSn papBcrSn : uapmdmScriptVo.getPapBcrSnlist()) {
				inituapmdmdatasqls.append(papBcrSn.createSqlByVO()).append("\n");
			}
		}

		// inituapmdmdatasqls.append("/*======================================== 2.12自定义档案实体列的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getBizObjectFieldlist() != null && uapmdmScriptVo.getBizObjectFieldlist().size() > 0) {
			for (BizObjectField bizObjectField : uapmdmScriptVo.getBizObjectFieldlist()) {
				inituapmdmdatasqls.append(bizObjectField.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.13自定义档案标签关联的脚本 ========================================*/").append("\n");
		if (uapmdmScriptVo.getWbLabelRelationlist() != null && uapmdmScriptVo.getWbLabelRelationlist().size() > 0) {
			for (WbLabelRelation wbLabelRelation : uapmdmScriptVo.getWbLabelRelationlist()) {
				inituapmdmdatasqls.append(wbLabelRelation.createSqlByVO()).append("\n");
			}
		}
		// inituapmdmdatasqls.append("/*======================================== 2.14提交脚本 ========================================*/").append("\n");
		inituapmdmdatasqls.append("commit;").append("\n");
		return inituapmdmdatasqls.toString();
	}

	@SuppressWarnings("unchecked")
	private String createUapmdmsqls(String mdmcode, Integer data_type, boolean bcheckMdm) throws Exception {
		StringBuffer createuapmdmsqls = new StringBuffer();
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
		IUapDao dao = new IUapDao(info);
		List<String[]> mdmdesignlist = (List<String[]>) dao.queryBySql("select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode + "')", new ArrayListProcessor());
		if(mdmdesignlist !=  null && mdmdesignlist.size() > 0) {
			StringBuffer sqllike = new StringBuffer();
			sqllike.append(" (1 = 2 ");
			for(Object[] mdmdesigns : mdmdesignlist) {
				sqllike.append(" or (table_name like upper('%").append(mdmdesigns[0]).append("%')) ");
			}
			sqllike.append(")");
			List<String[]> table_list = (List<String[]>) dao.queryBySql("select table_name from user_tables t where " + sqllike.toString() + " order by table_name",
					new ArrayListProcessor());
			if (table_list != null && table_list.size() > 0) {
				for (Object[] table_names : table_list) {
					String sql = "select column_name, data_type, data_length, data_precision, data_scale, nullable from user_tab_columns where table_name = '"
							+ table_names[0].toString() + "' order by column_id";
					List<String[]> column_list = (List<String[]>) dao.queryBySql(sql, new ArrayListProcessor());
					createuapmdmsqls.append(exportCreateTableScript(table_names[0].toString(), column_list, null, false, data_type));
					if (bcheckMdm) {
						createuapmdmsqls.append("delete from " + table_names[0].toString() + " where 1 = 1;").append("\n");
						createuapmdmsqls.append("commit;").append("\n");
						createuapmdmsqls.append(dao.excuteInsertSqls(" 1 = 1 ", table_names[0].toString()));
						createuapmdmsqls.append("commit;").append("\n");
					}
				}
			}
		}
		return createuapmdmsqls.toString();
	}

	private String dataTitle(int type) {
		StringBuffer titlesqls = new StringBuffer();
		titlesqls.append("/*===============================================================+").append("\n").append("\n");
		switch (type) {
		case 1:
			titlesqls.append("-------------------------导出建表语句----------------------------").append("\n");
			break;
		case 2:
			titlesqls.append("-------------------------导出服务脚本语句----------------------------").append("\n");
			break;
		case 3:
			titlesqls.append("-------------------------导出虚拟菜单语句----------------------------").append("\n");
			break;
		case 4:
			titlesqls.append("-------------------------导出自定义档案语句----------------------------").append("\n");
			break;
		default:
			break;
		}
		titlesqls.append("\n").append("+===============================================================*/").append("\n");
		titlesqls = new StringBuffer();
		return titlesqls.toString();
	}

	private String dataInitsqls(String serverName, boolean bcheckMenu) throws Exception {
		ServerScriptVO serverScriptVo = findServerScript(serverName);
		StringBuffer wbareasSqls = new StringBuffer();
		// wbareasSqls.append("/*======================================== 0.清空数据========================================*/").append("\n");
		wbareasSqls.append(deleteDataSqls(serverName, bcheckMenu)).append("\n");
		if(!bcheckMenu) {
	
			// wbareasSqls.append("/*======================================== 1.微服务注册的脚本 ========================================*/").append("\n");
			wbareasSqls.append(serverScriptVo.getWbAreas().createSqlByVO()).append("\n");
	
			// wbareasSqls.append("/*======================================== 2.微服务应用的脚本 ========================================*/").append("\n");
			if (serverScriptVo.getWbAppAppslist() != null && serverScriptVo.getWbAppAppslist().size() > 0) {
				for (WbAppApps wbappapps : serverScriptVo.getWbAppAppslist()) {
					wbareasSqls.append(wbappapps.createSqlByVO()).append("\n");
				}
			}
	
			// wbareasSqls.append("/*======================================= 3.微服务因应用分组的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getWbAppGroupslist() != null && serverScriptVo.getWbAppGroupslist().size() > 0) {
				for (WbAppGroups wbappgroups : serverScriptVo.getWbAppGroupslist()) {
					wbareasSqls.append(wbappgroups.createSqlByVO()).append("\n");
				}
			}
	
			// wbareasSqls.append("/*======================================= 4.微服务标签关联的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getWbLabelRelationlist() != null && serverScriptVo.getWbLabelRelationlist().size() > 0) {
				for (WbLabelRelation wblabelrelation : serverScriptVo.getWbLabelRelationlist()) {
					wbareasSqls.append(wblabelrelation.createSqlByVO()).append("\n");
				}
			}
	
			// wbareasSqls.append("/*======================================= 5.微服务功能注册的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getIeopFunctionlist() != null && serverScriptVo.getIeopFunctionlist().size() > 0) {
				for (IeopFunction ieopfunction : serverScriptVo.getIeopFunctionlist()) {
					wbareasSqls.append(ieopfunction.createSqlByVO()).append("\n");
				}
			}
	
			// wbareasSqls.append("/*======================================= 6.微服务功能按钮的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getIeopFunctionActivitylist() != null && serverScriptVo.getIeopFunctionActivitylist().size() > 0) {
				for (IeopFunctionActivity ieopfunctionactivity : serverScriptVo.getIeopFunctionActivitylist()) {
					wbareasSqls.append(ieopfunctionactivity.createSqlByVO()).append("\n");
				}
			}
		} else {
			// wbareasSqls.append("/*======================================= 7.微服务菜单的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getWbAppMenulist() != null && serverScriptVo.getWbAppMenulist().size() > 0) {
				for (WbAppMenu wbappmenu : serverScriptVo.getWbAppMenulist()) {
					wbareasSqls.append(wbappmenu.createSqlByVO()).append("\n");
				}
			}

			// wbareasSqls.append("/*======================================= 8.微服务菜单分组的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getWbMenuGrouplist() != null && serverScriptVo.getWbMenuGrouplist().size() > 0) {
				for (WbMenuGroup wbappmenugroup : serverScriptVo.getWbMenuGrouplist()) {
					wbareasSqls.append(wbappmenugroup.createSqlByVO()).append("\n");
				}
			}
	
			// wbareasSqls.append("/*======================================= 9.微服务菜单集合的脚本 =======================================*/").append("\n");
			if (serverScriptVo.getWbMenuCollectionlist() != null && serverScriptVo.getWbMenuCollectionlist().size() > 0) {
				for (WbMenuCollection wbmenucollection : serverScriptVo.getWbMenuCollectionlist()) {
					wbareasSqls.append(wbmenucollection.createSqlByVO()).append("\n");
				}
			}

		}
		// wbareasSqls.append("/*======================================= 10.提交脚本 =======================================*/").append("\n");
		wbareasSqls.append("commit;").append("\n");

		return wbareasSqls.toString();
	}

	private ServerScriptVO findServerScript(String serverName) throws Exception {
		ResultSet rs = null;
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
		IUapDao dao = new IUapDao(info);
		IUapVOTools voTools = new IUapVOTools();
		ServerScriptVO scriptVo = new ServerScriptVO();

		rs = dao.queryBySql(new WbAreas().getQrySqlByCondition(" area_code = '" + serverName + "' "));
		WbAreas wbAreas = (WbAreas) voTools.setInfo2Vo(WbAreas.class, rs);
		scriptVo.setWbAreas(wbAreas);

		rs = dao.queryBySql(new WbAppApps().getQrySqlByCondition(" domain_id in (select id from wb_areas where area_code = '" + serverName + "') "));
		List<WbAppApps> wbAppAppslist = voTools.setInfo2Vos(WbAppApps.class, rs);
		scriptVo.setWbAppAppslist(wbAppAppslist);

		rs = dao.queryBySql(new WbAppGroups().getQrySqlByCondition(" area_id in (select id from wb_areas where area_code = '" + serverName + "') "));
		List<WbAppGroups> wbAppGroupslist = voTools.setInfo2Vos(WbAppGroups.class, rs);
		scriptVo.setWbAppGroupslist(wbAppGroupslist);

		rs = dao.queryBySql(new WbLabelRelation().getQrySqlByCondition(" sourceid in (select id from wb_app_apps where domain_id in (select id from wb_areas where area_code ='"
				+ serverName + "')) "));
		List<WbLabelRelation> wbLabelRelationlist = voTools.setInfo2Vos(WbLabelRelation.class, rs);
		scriptVo.setWbLabelRelationlist(wbLabelRelationlist);

		rs = dao.queryBySql(new IeopFunction()
				.getQrySqlByCondition(" func_code in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code = '" + serverName + "')) "));
		List<IeopFunction> ieopFunctionlist = voTools.setInfo2Vos(IeopFunction.class, rs);
		scriptVo.setIeopFunctionlist(ieopFunctionlist);

		rs = dao.queryBySql(new IeopFunctionActivity()
				.getQrySqlByCondition(" func_id in (select id from ieop_function where func_code in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code = '"
						+ serverName + "'))) "));
		List<IeopFunctionActivity> ieopFunctionActivitylist = voTools.setInfo2Vos(IeopFunctionActivity.class, rs);
		scriptVo.setIeopFunctionActivitylist(ieopFunctionActivitylist);

		rs = dao.queryBySql(new WbAppMenu().getQrySqlByCondition(" layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code = '"
				+ serverName + "')) "));
		List<WbAppMenu> wbAppMenulist = voTools.setInfo2Vos(WbAppMenu.class, rs);
		scriptVo.setWbAppMenulist(wbAppMenulist);

		rs = dao.queryBySql(new WbMenuGroup()
				.getQrySqlByCondition(" menu_id in(select id from wb_app_menu where layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code = '"
						+ serverName + "'))) "));
		List<WbMenuGroup> wbMenuGrouplist = voTools.setInfo2Vos(WbMenuGroup.class, rs);
		scriptVo.setWbMenuGrouplist(wbMenuGrouplist);

		rs = dao.queryBySql(new WbMenuCollection()
				.getQrySqlByCondition(" menu_id in(select id from wb_app_menu where layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code = '"
						+ serverName + "'))) "));
		List<WbMenuCollection> wbMenuCollectionlist = voTools.setInfo2Vos(WbMenuCollection.class, rs);
		scriptVo.setWbMenuCollectionlist(wbMenuCollectionlist);

		return scriptVo;
	}

	private UapMdmScriptVO findUapDmdScript(String mdmcode) throws Exception {
		ResultSet rs = null;
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
		IUapDao dao = new IUapDao(info);
		IUapVOTools voTools = new IUapVOTools();
		UapMdmScriptVO scriptVo = new UapMdmScriptVO();

		rs = dao.queryBySql(new UapmdmCategory().getQrySqlByCondition(" code = '" + mdmcode + "' "));
		List<UapmdmCategory> uapmdmCategorylist = voTools.setInfo2Vos(UapmdmCategory.class, rs);
		scriptVo.setUapmdmCategorylist(uapmdmCategorylist);

		rs = dao.queryBySql(new UapmdmDesigninfo().getQrySqlByCondition(" pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode + "') "));
		List<UapmdmDesigninfo> uapmdmDesigninfolist = voTools.setInfo2Vos(UapmdmDesigninfo.class, rs);
		scriptVo.setUapmdmDesigninfolist(uapmdmDesigninfolist);

		rs = dao.queryBySql(new UapmdmEntity()
				.getQrySqlByCondition(" pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode
						+ "')) "));
		List<UapmdmEntity> uapmdmEntitylist = voTools.setInfo2Vos(UapmdmEntity.class, rs);
		scriptVo.setUapmdmEntitylist(uapmdmEntitylist);

		rs = dao.queryBySql(new UapmdmEntityitems()
				.getQrySqlByCondition(" pk_mdentity in (select pk_mdentity from uapmdm_entity where pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))) "));
		List<UapmdmEntityitems> uapmdmEntityitemslist = voTools.setInfo2Vos(UapmdmEntityitems.class, rs);
		scriptVo.setUapmdmEntityitemslist(uapmdmEntityitemslist);

		rs = dao.queryBySql(new UapmdmFieldref()
				.getQrySqlByCondition(" pk_gd in (select pk_gd from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode
						+ "')) "));
		List<UapmdmFieldref> uapmdmFieldreflist = voTools.setInfo2Vos(UapmdmFieldref.class, rs);
		scriptVo.setUapmdmFieldreflist(uapmdmFieldreflist);

		rs = dao.queryBySql(new UapmdmEnumtype().getQrySqlByCondition(" 1 = 1 "));
		List<UapmdmEnumtype> uapmdmEnumtypelist = voTools.setInfo2Vos(UapmdmEnumtype.class, rs);
		scriptVo.setUapmdmEnumtypelist(uapmdmEnumtypelist);

		rs = dao.queryBySql(new UapmdmEnumvalue().getQrySqlByCondition(" 1 = 1 "));
		List<UapmdmEnumvalue> uapmdmEnumvaluelist = voTools.setInfo2Vos(UapmdmEnumvalue.class, rs);
		scriptVo.setUapmdmEnumvaluelist(uapmdmEnumvaluelist);

		rs = dao.queryBySql(new BizObject()
				.getQrySqlByCondition(" code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '" + mdmcode
						+ "')) "));
		List<BizObject> bizObjectlist = voTools.setInfo2Vos(BizObject.class, rs);
		scriptVo.setBizObjectlist(bizObjectlist);

		rs = dao.queryBySql(new PapBcrRulebase()
				.getQrySqlByCondition(" pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))) "));
		List<PapBcrRulebase> papBcrRulebaselist = voTools.setInfo2Vos(PapBcrRulebase.class, rs);
		scriptVo.setPapBcrRulebaselist(papBcrRulebaselist);

		rs = dao.queryBySql(new PapBcrElem()
				.getQrySqlByCondition(" pk_billcodebase in(select pk_billcodebase from pap_bcr_rulebase where pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')))) "));
		List<PapBcrElem> papBcrElemlist = voTools.setInfo2Vos(PapBcrElem.class, rs);
		scriptVo.setPapBcrElemlist(papBcrElemlist);

		rs = dao.queryBySql(new PapBcrSn()
				.getQrySqlByCondition(" pk_billcodebase in(select pk_billcodebase from pap_bcr_rulebase where pkbillobj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "')))) "));
		List<PapBcrSn> papBcrSnlist = voTools.setInfo2Vos(PapBcrSn.class, rs);
		scriptVo.setPapBcrSnlist(papBcrSnlist);

		rs = dao.queryBySql(new BizObjectField()
				.getQrySqlByCondition(" pk_biz_obj in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))) "));
		List<BizObjectField> bizObjectFieldlist = voTools.setInfo2Vos(BizObjectField.class, rs);
		scriptVo.setBizObjectFieldlist(bizObjectFieldlist);

		rs = dao.queryBySql(new WbLabelRelation()
				.getQrySqlByCondition(" sourceid in (select id from biz_object where code in (select code from uapmdm_designinfo where pk_category in (select pk_category from uapmdm_category where code = '"
						+ mdmcode + "'))) "));
		List<WbLabelRelation> wbLabelRelationlist = voTools.setInfo2Vos(WbLabelRelation.class, rs);
		scriptVo.setWbLabelRelationlist(wbLabelRelationlist);

		return scriptVo;
	}

	private String deleteDataSqls(String serverName, boolean bcheckMenu) {
		StringBuffer deleteDatasqls = new StringBuffer();
		if(bcheckMenu) {
			deleteDatasqls
					.append("delete from wb_menu_collection where menu_id in(select id from wb_app_menu where layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
							+ serverName + "'))));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls
					.append("delete from wb_menu_group where menu_id in(select id from wb_app_menu where layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
							+ serverName + "'))));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from wb_app_menu where layout_id in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
					+ serverName + "')));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
		} else {
			deleteDatasqls
					.append("delete from ieop_function_activity where func_id in (select id from ieop_function where func_code in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
							+ serverName + "'))));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from ieop_function where func_code in (select app_code from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
					+ serverName + "')));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from wb_label_relation where sourceid in (select id from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('"
					+ serverName + "')));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from wb_app_groups where area_id in (select id from wb_areas where area_code in ('" + serverName + "'));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from wb_app_apps where domain_id in (select id from wb_areas where area_code in ('" + serverName + "'));");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("delete from wb_areas where area_code in ('" + serverName + "');");
			deleteDatasqls.append("\n");
			deleteDatasqls.append("commit;");
			deleteDatasqls.append("\n");
		}
		return deleteDatasqls.toString();
	}

	private StringBuffer buildCreateSqls(List<String[]> column_list) {
		StringBuffer createsql = new StringBuffer();
		if (column_list != null && column_list.size() > 0) {
			for (Object[] columns : column_list) {
				switch (columns[1].toString()) {
				case IUapConsts.VARCHAR2:
				case IUapConsts.NVARCHAR2:
					if (columns[5].equals("N")) {
						createsql.append("\n").append(" ");
						createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[2]).append(") not null,");
					} else {
						createsql.append("\n").append(" ");
						createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[2]).append("),");
					}
					break;
				case IUapConsts.NUMBER:
					if (columns[3] == null || columns[3].toString().length() <= 0) {
						createsql.append("\n").append(" ");
						createsql.append(columns[0]).append(" INTEGER,");
					} else if (columns[4] == null || columns[4].toString().length() <= 0) {
						createsql.append("\n").append(" ");
						createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[3]).append("),");
					} else {
						createsql.append("\n").append(" ");
						createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[3]).append(",").append(columns[4]).append("),");
					}
					break;
				case IUapConsts.FLOAT:
					createsql.append("\n").append(" ");
					createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[3]).append("),");
					break;
				case IUapConsts.CHAR:
				case IUapConsts.NCHAR:
					createsql.append("\n").append(" ");
					createsql.append(columns[0]).append(" ").append(columns[1]).append("(").append(columns[2]).append("),");
					break;
				case IUapConsts.BLOB:
				case IUapConsts.NCLOB:
				case IUapConsts.CLOB:
				case IUapConsts.DATE:
					createsql.append("\n").append(" ");
					createsql.append(columns[0]).append(" ").append(columns[1]).append(",");
					break;
				default:
					break;
				}
			}
		}
		return createsql;
	}

	private String buildCreateSqls(String table_name, Map<String, LinkedHashMap<String, Object>> columns) {
		StringBuffer createSql = new StringBuffer();
		createSql.append("CREATE TABLE " + table_name + " ( \n");

		for (String column_name : columns.keySet()) {
			LinkedHashMap<String, Object> column = columns.get(column_name);
			String column_datatype = column.keySet().toArray()[0].toString();
			String column_len = column.values().toArray()[0].toString();
			createSql.append("  ").append(column_name).append(" ").append(column_datatype);
			if (column_len != null && column_len.length() > 0) {
				createSql.append("(").append(column_len).append(")");
			}
			if (column_name.toLowerCase().equals("id")) {
				createSql.append(" NOT NULL");
			}
			createSql.append(",\n");
		}

		return createSql.substring(0, createSql.length() - 2) + "\n);\n";
	}

	private StringBuffer buildCommentSqls(List<String[]> col_comments_list) {
		StringBuffer commentsql = new StringBuffer();
		if (col_comments_list != null && col_comments_list.size() > 0) {
			for (Object[] col_comments : col_comments_list) {
				if (col_comments[1] != null && col_comments[1].toString().length() > 0) {
					commentsql.append("\n");
					commentsql.append("comment on column ").append(col_comments[0]).append(".").append(col_comments[1]);
					commentsql.append("\n");
					commentsql.append(" is '").append(col_comments[2]).append("';");
				}
			}
		}
		return commentsql;
	}

	public StringBuffer getSql(ResultSet rs, ResultSetMetaData rsmd, int nColumnCount) throws SQLException {
		StringBuffer sqlbuffer = new StringBuffer();
		for (int j = 1; j <= nColumnCount; j++) {
			Object column_value = null;
			String strObj = null;
			column_value = rs.getString(j);
			if (column_value != null) {
				strObj = column_value.toString().trim();
				sqlbuffer.append(strObj);
			}
		}
		return sqlbuffer;
	}

	public String buildUpgradeScript(Map<String, Map<String, LinkedHashMap<String, Object>>> oldData, Map<String, Map<String, LinkedHashMap<String, Object>>> newData) {

		List<String> createTables = new ArrayList<String>();
		List<String> alterTables = new ArrayList<String>();
		StringBuffer createSql = new StringBuffer();
		StringBuffer alterSql = new StringBuffer();

		/** 新旧库对比 **/
		if (newData.equals(oldData))
			return "";

		// 1. 匹配表
		for (String newTable : newData.keySet()) {
			if (!oldData.containsKey(newTable)) { // 旧的版本中不存在新版本的该表
				createTables.add(newTable);
			} else {
				alterTables.add(newTable);
			}
		}

		// 2. 对新旧版本都存在的表 - 匹配列
		for (String table : alterTables) {

			Map<String, LinkedHashMap<String, Object>> oldTable = oldData.get(table);
			Map<String, LinkedHashMap<String, Object>> newTable = newData.get(table);

			if (!oldTable.equals(newTable)) { // 表信息存在差异

				// (1) oldColumn-key匹配newColumn-key
				for (String oldColumn : oldTable.keySet()) {
					if (!newTable.containsKey(oldColumn)) { // 新的版本中不存在旧版本的该列
						alterSql.append(buildColumnSql("DROP", table, oldColumn, null, null));
					}
				}

				// (2) newColumn-key匹配oldColumn-key
				for (String newColumn : newTable.keySet()) {

					LinkedHashMap<String, Object> newAttribute = newTable.get(newColumn);
					String newAttriType = newAttribute.keySet().toArray()[0].toString();
					String newAttriLen = newAttribute.values().toArray()[0].toString();

					if (!oldTable.containsKey(newColumn)) { // 旧的版本中不存在新版本的该列
						alterSql.append(buildColumnSql("ADD", table, newColumn, newAttriType, newAttriLen));
					} else {

						LinkedHashMap<String, Object> oldAttribute = oldTable.get(newColumn);
						String oldAttriType = oldAttribute.keySet().toArray()[0].toString();
						String oldAttriLen = oldAttribute.values().toArray()[0].toString();

						if (!oldAttriType.equals(newAttriType)) { // 列属性不一致
							// 先删列再建列
							alterSql.append(buildColumnSql("DROP", table, newColumn, oldAttriType, oldAttriLen));
							alterSql.append(buildColumnSql("ADD", table, newColumn, newAttriType, newAttriLen));
						} else {
							if (!oldAttriLen.equals(newAttriLen)) { // 字段长度不一致
								alterSql.append(buildColumnSql("MODIFY", table, newColumn, newAttriType, newAttriLen));
							}
						}
					}
				}

			}

		}

		/** 构建脚本 **/

		// 构建 create table 语句
		for (String table : createTables) {
			createSql.append(buildCreateSqls(table, newData.get(table)));
		}

		StringBuffer upgradeScript = new StringBuffer();
		// upgradeScript.append("-------------------------升级脚本----------------------------").append("\n");
		// upgradeScript.append("------ 1  创建新表  ------").append("\n");
		// upgradeScript.append(createSql).append("\n");
		// upgradeScript.append("------ 2  修改旧表  ------").append("\n");
		upgradeScript.append(alterSql).append("COMMIT;\n");

		return upgradeScript.toString();
	}

	public String buildColumnSql(String optype, String table_name, String column_name, String column_type, String column_length) {
		StringBuffer columnSql = new StringBuffer();
		switch (optype) {
		case "ADD":
			columnSql.append("alter table ").append(table_name).append(" add ").append(column_name).append(" ").append(column_type);
			if (column_length != null && column_length.length() > 0) {
				columnSql.append("(").append(column_length).append(");");
			}
			break;

		case "DROP":
			columnSql.append("alter table ").append(table_name).append(" drop column ").append(column_name).append(";");
			break;

		case "MODIFY":
			columnSql.append("alter table ").append(table_name).append(" modify ").append(column_name).append(" ").append(column_type);
			if (column_length != null && column_length.length() > 0) {
				columnSql.append("(").append(column_length).append(");");
			}
			break;

		default:
			break;
		}
		return columnSql.append("\n").toString();
	}

	public String exportItemsDataScript() throws Exception {
		StringBuffer itemssqlfiles = new StringBuffer();
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		IUapDataSourceInfo info = xmlCtrl.getDefPropInfo(null);
		IUapDao dao = new IUapDao(info);
		Map<String, IUapItemsInfo> mapitemsInfo = xmlCtrl.getItemsMap();
		if (mapitemsInfo != null && mapitemsInfo.size() > 0) {
			for (Entry<String, IUapItemsInfo> et : mapitemsInfo.entrySet()) {
				IUapItemsInfo itemsinfo = et.getValue();
				String tableName = et.getKey();
				itemssqlfiles.append("delete from " + tableName + " where " + itemsinfo.getField("fixedWhere") + ";").append("\n");
				itemssqlfiles.append("commit;").append("\n");
				itemssqlfiles.append(dao.excuteInsertSqls(itemsinfo.getField("fixedWhere"), tableName));
				itemssqlfiles.append("commit;").append("\n");
			}
		}
		return itemssqlfiles.toString();
	}

	public String buildsqlWhere(String serverId) {
		StringBuffer sqlwhere = new StringBuffer();
		IUapXmlCtrl xmlCtrl = new IUapXmlCtrl();
		Map<String, IUapTableItemsInfo> mapitemsInfo = xmlCtrl.getTableitemsMap();
		if (mapitemsInfo != null && mapitemsInfo.size() > 0) {
			IUapTableItemsInfo itemsinfo = mapitemsInfo.get(serverId);
			if (itemsinfo != null && itemsinfo.getField("tablelike") != null) {
				String[] tablelikes = itemsinfo.getField("tablelike").split(",");
				if (tablelikes != null) {
					for (String tablelike : tablelikes) {
						if (tablelike != null && tablelike.length() > 0) {
							sqlwhere.append("t.table_name like Upper('" + tablelike.trim() + "%') or ");
						}
					}
				}
			}
			if (itemsinfo != null && itemsinfo.getField("tablevalue") != null) {
				String[] tablevalues = itemsinfo.getField("tablevalue").split(",");
				if (tablevalues != null) {
					for (String tablevalue : tablevalues) {
						if (tablevalue != null && tablevalue.length() > 0) {
							sqlwhere.append("t.table_name = Upper('" + tablevalue.trim() + "') or ");
						}
					}
				}
			}
		}
		return sqlwhere.toString();
	}
}