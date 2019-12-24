package installIuap.common.tools;

import java.io.File;

/**
 * IUAP公共参数
 * 
 * @author zhaoti
 * 
 */
public class IUapUtil {

	public static String getFilePath() {
		String path = System.getProperty("user.dir");
		path = path + File.separator;
		return path;
	}

}