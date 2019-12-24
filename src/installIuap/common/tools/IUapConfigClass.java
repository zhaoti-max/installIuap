package installIuap.common.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * IUAP数据库配置文件
 * 
 * @author zhaoti
 * 
 */
public class IUapConfigClass {
	public static final String OUT_PATH;
	public static final String DB_TYPE;
	public static final String IP;
	public static final String USERNAME;
	public static final String PASSWORD;
	public static final String DB_NAME;

	static {
		String proFilePath = System.getProperty("user.dir") + "\\config.properties";
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(proFilePath));
		} catch (FileNotFoundException e1) {
			throw new RuntimeException(e1);
		}
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		OUT_PATH = getStr(prop, "OUT_PATH");
		DB_TYPE = getStr(prop, "DB_TYPE");
		IP = getStr(prop, "IP");
		USERNAME = getStr(prop, "USERNAME");
		PASSWORD = getStr(prop, "PASSWORD");
		DB_NAME = getStr(prop, "DB_NAME");
	}

	private static String getStr(Properties prop, String key) {
		String value = (String) prop.get(key);
		try {
			if (value != null) {
				value = value.trim();
				return new String(value.getBytes("ISO-8859-1"), "UTF-8");
			}
			return "";
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
}