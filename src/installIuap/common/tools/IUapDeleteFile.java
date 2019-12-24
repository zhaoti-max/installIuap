package installIuap.common.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * ɾ���ļ�
 * 
 * @author zhaoti
 * 
 */
public class IUapDeleteFile {
	public static final int MAX_DOT_NUM = 30;
	public static List<File> fileList = new ArrayList<File>();

	public static void main(String[] args) {

		try {
			System.out.print("��ʼ���������ĵȴ�...");
			long startTime = System.currentTimeMillis();
			int count = delete("D:\\install\\temp");
			long stopTime = System.currentTimeMillis();
			System.out.print("��������������ļ�(��Ŀ¼)" + count + "����");
			System.out.println("����ʱ��:" + ((stopTime - startTime) / 1000) + "��");
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
			if (tmp.isDirectory()) { /* �����ļ��� */
				File[] files = tmp.listFiles();
				if (files.length > 0) { /* ������ļ����°����ļ������ļ��� */

					for (File file : files) {
						fileList.add(file);
					}
				} else {
					fileList.remove(fileList.size() - 1);
					tmp.getAbsoluteFile().delete();
					count++;
				}
			} else { /* �����ļ� */
				fileList.remove(fileList.size() - 1);
				tmp.delete();
				count++;
			}
		}
		return count;
	}

}