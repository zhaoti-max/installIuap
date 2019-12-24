package installIuap.common.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * 删除文件
 * 
 * @author zhaoti
 * 
 */
public class IUapDeleteFile {
	public static final int MAX_DOT_NUM = 30;
	public static List<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {

		try {
			System.out.print("开始处理，请耐心等待...");
			long startTime = System.currentTimeMillis();
			int count = delete("D:\\install\\temp");
			long stopTime = System.currentTimeMillis();
			System.out.print("处理结束，处理文件(或目录)" + count + "个，");
			System.out.println("花费时长:" + ((stopTime - startTime) / 1000) + "秒");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static int delete(String filename) throws IOException {
		int count = 0;
		boolean bPlayMovies = true;
		File f = new File(filename);
		if (!f.exists()) {
			return 0;
		}
		fileList.add(f);
		boolean isplus = false;
		while (fileList.size() > 0) {
			if (bPlayMovies) {
				if (count % MAX_DOT_NUM == 0) {
					isplus = !isplus;
				}
			}
			File tmp = fileList.get(fileList.size() - 1);
			if (tmp.isDirectory()) { /* 处理文件夹 */
				File[] files = tmp.listFiles();
				if (files.length > 0) { /* 如果该文件夹下包含文件或子文件夹 */

					for (File file : files) {
						fileList.add(file);
					}
				} else {
					fileList.remove(fileList.size() - 1);
					tmp.getAbsoluteFile().delete();
					count++;
				}
			} else { /* 处理文件 */
				fileList.remove(fileList.size() - 1);
				tmp.delete();
				count++;
			}
		}
		return count;
	}

}