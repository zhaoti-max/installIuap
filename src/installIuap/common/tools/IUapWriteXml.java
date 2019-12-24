package installIuap.common.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

/**
 * ����jar����xml�ļ�
 * 
 * @author zhaoti
 * 
 */
public class IUapWriteXml {

	/**
	 * @MethodName : writeXml
	 * @Description : �޸İ���jar���е�xml�����ļ� 1����Դ�ļ��ƶ���tempPathĿ¼�� 2����Դ�ļ����洴�� �ļ���Ϊ
	 *              Դ�ļ������ļ� 3�����ļ�����ʱ�ļ�·����ȡ��Դ�ļ�·���� 4��ɾ����ʱ�ļ�
	 * @param calendarId
	 * @throws IOException
	 */
	public static void writeXml(Document document) throws IOException {
		/**
		 * 1����Դ�ļ��ƶ���tempPathĿ¼��
		 */

		// ��ȡ��ǰJar�ļ�������������룬��ֹ������������
		String jarfile = URLDecoder.decode(IUapWriteXml.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "InstallIuap.jar", "UTF-8");

		// �ļ�ԭ��ַ
		File oldFile = new File(jarfile);

		// ��ʱ�ļ��ļ���λ��
		String tempPath = jarfile.substring(0, jarfile.lastIndexOf("/") + 1) + "temp";

		// newһ�����ļ���
		File fnewpath = new File(tempPath);

		// �ж��ļ����Ƿ����
		if (!fnewpath.exists())
			fnewpath.mkdirs();

		// ���ļ���ȫ·��
		String tempJarPath = tempPath + "/" + oldFile.getName();

		// ���ļ��Ƶ����ļ���
		File fnew = new File(tempJarPath);
		oldFile.renameTo(fnew);
		
		/**
		 * 2����Դ�ļ����洴�� �ļ���Ϊ Դ�ļ������ļ�
		 */
		JarOutputStream jaros = new JarOutputStream(new FileOutputStream(jarfile));
		JarFile jarf = new JarFile(tempJarPath);

		/**
		 * 3�����ļ�����ʱ�ļ�·����ȡ��Դ�ļ�·���� �˴�ͨ���ж����ƣ��޸��ļ�
		 */
		for (Enumeration<JarEntry> en = jarf.entries(); en.hasMoreElements();) {
			JarEntry entry = en.nextElement();
			InputStream entryIn = null;
			if (entry.getName().equals("installIuap/config/datasource.xml")) {
				entryIn = (updateXmlFile(tempJarPath, document));
			} else {
				entryIn = jarf.getInputStream(entry);
			}
			jaros.putNextEntry(new JarEntry(entry.getName()));
			byte[] encryData;
			byte[] classData = getByteData(entryIn);
			encryData = classData;
			jaros.write(encryData, 0, encryData.length);
			jaros.closeEntry();
		}
		jarf.close();
		jaros.close();

		/**
		 * 4��ɾ����ʱ�ļ��е�����
		 */
		File f = new File(tempJarPath);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * @MethodName : updateXmlFile
	 * @Description : �����޸ĺ��xml�ļ�
	 * @param currentJarPath
	 * @param calendarId
	 * @return
	 */
	private static InputStream updateXmlFile(String currentJarPath, Document document) {
		InputStream is = null;
		String road = "";
		try {
			/**
			 * 1��ȡ����ʱ�ļ���·���� a����ȡjar�ļ���·�� b��������ʱ�ļ���·��
			 * 
			 */
			// ��ʱ�ļ��ļ���λ��
			road = currentJarPath.substring(0, currentJarPath.lastIndexOf("/"));

			// �����ļ���
			File file = new File(road);
			if (!file.exists()) {// ����ļ���
				file.mkdirs();// �½�
			}
			String EntryName = "/datasource.xml";

			// roadΪ��xml��ȫ·��
			road += EntryName;

			/**
			 * 2�����ݴ�������Idֵ������ʱ�ļ�Ŀ¼�£������µ� xml�ļ�
			 */
			Writer fileWriter = new FileWriter(road);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document); // д���ļ���
			xmlWriter.close();

			/**
			 * 3�����ļ�·������������ʽ����
			 */
			is = new FileInputStream(road);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return is;
	}

	/**
	 * @MethodName : getByteData
	 * @Description : ���������ļ�
	 * @param is
	 *            �ļ���
	 * @return
	 * @throws IOException
	 */
	private static byte[] getByteData(InputStream is) throws IOException {
		int len;
		int size = 1024;
		byte[] buf;
		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}
}
