package installIuap.vo.bean.uapmdm;

import installIuap.vo.IUapBaseVO;

public class BizObjectField extends IUapBaseVO {

	public String pk;
	public String pk_biz_obj;
	public String en_prop_name;
	public String display_name;
	public String elem_type;
	public String mapping_entity;
	public String display_name2;
	public String display_name3;
	public String display_name4;
	public String display_name5;
	public String display_name6;
	public String create_person;
	public String modify_person;
	public java.sql.Date create_date;
	public java.sql.Date modify_date;
	public Integer dr;
	public Integer status;

	@Override
	public String getPkField() {
		return "pk";
	}

	@Override
	public String getTableCode() {
		return "biz_object_field";
	}

}
