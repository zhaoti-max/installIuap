package installIuap.vo;

import java.lang.reflect.Field;

/**
 * IUAPBaseVO
 * 
 * @author zhaoti
 * 
 */
public abstract class IUapBaseVO implements IIUapBaseVO {
	public String createSqlByVO() {
		StringBuffer sql = new StringBuffer();

		sql.append(" insert into ").append(getTableCode());
		sql.append(" (").append(getSqlFieldLst()).append(") values(");
		sql.append(getSqlValueLst());
		sql.append(");");

		return sql.toString();
	}

	private String getSqlFieldLst() {
		StringBuffer sbField = new StringBuffer();
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			sbField.append(",").append(field.getName());
		}

		return sbField.substring(1);
	}

	private String getSqlValueLst() {
		StringBuffer sbValue = new StringBuffer();
		Field[] fields = getClass().getFields();
		Object tmpVal = null;
		for (Field field : fields) {
			try {
				tmpVal = field.get(this);
			} catch (Exception e) {
				e.printStackTrace();
			}

			sbValue.append(",").append(getSqlByValue(tmpVal));
		}

		return sbValue.substring(1);
	}

	public String getSqlByValue(Object value) {
		String rtnVal = "";
		if (value == null) {
			return "null";
		}

		if ((value instanceof String))
			rtnVal = "'" + String.valueOf(value) + "'";
		else if (((value instanceof Integer)) || ((value instanceof Double)))
			rtnVal = String.valueOf(value);
		else if ((value instanceof Boolean))
			rtnVal = ((Boolean) value).booleanValue() ? "'Y'" : "'N'";
		else if((value instanceof java.sql.Date)) {
			rtnVal = "to_date('" + String.valueOf(value) + "', 'yyyy-mm-dd hh24:mi:ss')";
		} else {
			rtnVal = "'" + String.valueOf(value) + "'";
		}

		return rtnVal;
	}

	public String delSqlByVO() {
		StringBuffer sql = new StringBuffer();
		String pk = null;
		try {
			pk = (String) getClass().getField(getPkField()).get(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" delete from " + getTableCode() + " where " + getPkField() + " = '" + pk + "'; ");

		return sql.toString();
	}

	public String delSqlByVO(String delKey) {
		StringBuffer sql = new StringBuffer();
		String pk = null;
		try {
			pk = (String) getClass().getField(delKey).get(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" delete from " + getTableCode() + " where " + delKey + " = '" + pk + "'; ");

		return sql.toString();
	}

	public String updSqlByVO(IUapBaseVO srcVo) {
		return null;
	}

	public String getQrySqlByCondition(String sqlWhere) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ").append(getSqlFieldLst()).append(" from ").append(getTableCode());
		if ((sqlWhere != null) && (sqlWhere.length() > 0)) {
			sql.append(" where ").append(sqlWhere);
		}

		return sql.toString();
	}

	public String getPkValue() {
		String pkFieldName = getPkField();
		String pkValue = null;
		try {
			pkValue = (String) getClass().getField(pkFieldName).get(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pkValue;
	}

	public abstract String getPkField();

	public abstract String getTableCode();
}