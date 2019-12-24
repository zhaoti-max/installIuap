package installIuap.common.tools;

import installIuap.vo.IUapBaseVO;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IUAPπ§æﬂ¿‡
 * 
 * @author zhaoti
 * 
 */
public class IUapVOTools {
	public <T> IUapBaseVO setInfo2Vo(Class<T> clazz, ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		IUapBaseVO vo = null;
		if (rs.next()) {
			vo = (IUapBaseVO) clazz.newInstance();
			Field[] fields = vo.getClass().getDeclaredFields();
			setFieldsValue(vo, fields, rs);
		}

		return vo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IUapBaseVO> List<T> setInfo2Vos(Class<T> clazz, ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		IUapBaseVO vo = null;
		List voLst = new ArrayList();
		while (rs.next()) {
			vo = (IUapBaseVO) clazz.newInstance();
			Field[] fields = vo.getClass().getDeclaredFields();
			setFieldsValue(vo, fields, rs);

			voLst.add(vo);
		}

		return voLst;
	}

	private <T extends IUapBaseVO> void setFieldsValue(T vo, Field[] fields, ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Object val = null;
		for (Field field : fields) {
			val = rs.getObject(field.getName());

			if ((val instanceof Short))
				val = new Integer(((Short) val).shortValue());
			else if ((val instanceof String)) {
				if ("N".equals(val))
					val = Boolean.valueOf(false);
				else if ("Y".equals(val))
					val = Boolean.valueOf(true);
			} else if ((val instanceof BigDecimal)) {
				val = Integer.valueOf(((BigDecimal) val).intValue());
			}

			if ((val instanceof Boolean)) {
				val = ((Boolean) val).booleanValue() ? "Y" : "N";
			}

			if ((val instanceof Integer)) {
				if (field.getType().toString().equals("class java.lang.String")) {
					field.set(vo, val.toString());
					continue;
				}
			} else if (((val instanceof String)) && (field.getType().toString().equals("class java.lang.Integer"))) {
				field.set(vo, Integer.valueOf(Integer.parseInt((String) val)));
			}
			
			if((val instanceof Timestamp)) {
				Date date = new Date(((Timestamp) val).getTime());
				val = date;
			}

			field.set(vo, val);
		}
	}

	@SuppressWarnings("rawtypes")
	public <T extends IUapBaseVO> String compareVo(T srcVo, T descVo) {
		StringBuffer setSql = new StringBuffer();
		String srcVal = null;
		String descVal = null;
		Map srcVoMap = getFieldValMap(srcVo);
		Map descVoMap = getFieldValMap(descVo);

		for (Object fName : srcVoMap.keySet()) {
			srcVal = (String) srcVoMap.get(fName);
			descVal = (String) descVoMap.get(fName);

			if (((srcVal != null) && (srcVal.indexOf("\n") >= 0)) || ((descVal != null) && (descVal.indexOf("\n") >= 0))) {
				if (srcVal != null) {
					srcVal = srcVal.replaceAll("\n", "");
					srcVal = srcVal.replaceAll(" ", "");
				}

				if (descVal != null) {
					descVal = descVal.replaceAll("\n", "");
					descVal = descVal.replaceAll(" ", "");
				}

			}

			if (((srcVal.equals("null")) && ("''".equals(descVal))) || (("''".equals(srcVal)) && (descVal.equals("null")))) {
				continue;
			}
			if ((fName.equals("ts")) || (((srcVal != null) || (descVal == null)) && ((srcVal == null) || (srcVal.equals(descVal)))))
				continue;
			setSql.append(",").append(fName + " = " + (String) descVoMap.get(fName));
		}

		StringBuffer updateSql = new StringBuffer();
		if (setSql.length() > 0) {
			updateSql.append(" update " + srcVo.getTableCode() + " set " + setSql.substring(1) + " where " + srcVo.getPkField() + " = '" + srcVo.getPkValue() + "'; ").append("\n");
		}

		return updateSql.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends IUapBaseVO> String compareVos(List<T> srcVoLst, List<T> descVoLst) {
		StringBuffer insertSql = new StringBuffer();
		StringBuffer deleteSql = new StringBuffer();
		StringBuffer updateSql = new StringBuffer();

		Map srcVoMap = new HashMap();
		Map descVoMap = new HashMap();

		if (srcVoLst != null) {
			for (IUapBaseVO srcVo : srcVoLst) {
				srcVoMap.put(srcVo.getPkValue(), srcVo);
			}

		}

		if (descVoLst != null) {
			for (IUapBaseVO descVo : descVoLst) {
				descVoMap.put(descVo.getPkValue(), descVo);
			}
		}

		for (Object srcPk : srcVoMap.keySet()) {
			if (!descVoMap.containsKey(srcPk)) {
				deleteSql.append(((IUapBaseVO) srcVoMap.get(srcPk)).delSqlByVO()).append("\n");
			} else {
				updateSql.append(compareVo((IUapBaseVO) srcVoMap.get(srcPk), (IUapBaseVO) descVoMap.get(srcPk)));
			}
		}

		for (Object descPk : descVoMap.keySet()) {
			if (!srcVoMap.containsKey(descPk)) {
				insertSql.append(((IUapBaseVO) descVoMap.get(descPk)).delSqlByVO()).append("\n");
				insertSql.append(((IUapBaseVO) descVoMap.get(descPk)).createSqlByVO()).append("\n");
			}
		}

		StringBuffer rtnSql = new StringBuffer();
		rtnSql.append(insertSql);
		rtnSql.append(deleteSql);
		rtnSql.append(updateSql);
		return rtnSql.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T extends IUapBaseVO> Map<String, String> getFieldValMap(T vo) {
		if (vo == null) {
			return new HashMap();
		}

		Map fvMap = new HashMap();
		Object srcObjVal = null;
		String srcVal = null;
		for (Field field : vo.getClass().getDeclaredFields()) {
			try {
				srcObjVal = field.get(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			srcVal = vo.getSqlByValue(srcObjVal);
			fvMap.put(field.getName(), srcVal);
		}

		return fvMap;
	}
}