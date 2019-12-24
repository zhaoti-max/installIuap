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
 * 更新jar包的xml文件
 * 
 * @author zhaoti
 * 
 */
public class IUapWriteXml {

	/**
	 * @MethodName : writeXml
	 * @Description : 修改包含jar包中的xml配置文件 1、将源文件移动到tempPath目录下 2、在源文件里面创建 文件名为
	 *              源文件名的文件 3、将文件从临时文件路径读取到源文件路径下 4、删除临时文件
	 * @param calendarId
	 * @throws IOException
	 */
	public static void writeXml(Document document) throws IOException {
		/**
		 * 1、将源文件移动到tempPath目录下
		 */

		// 获取当前Jar文件名，并对其解码，防止出现中文乱码
		String jarfile = URLDecoder.decode(IUapWriteXml.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "InstallIuap.jar", "UTF-8");

		// 文件原地址
		File oldFile = new File(jarfile);

		// 临时文件文件夹位置
		String tempPath = jarfile.substring(0, jarfile.lastIndexOf("/") + 1) + "temp";

		// new一个新文件夹
		File fnewpath = new File(tempPath);

		// 判断文件夹是否存在
		if (!fnewpath.exists())
			fnewpath.mkdirs();

		// 新文件的全路径
		String tempJarPath = tempPath + "/" + oldFile.getName();

		// 将文件移到新文件里
		File fnew = new File(tempJarPath);
		oldFile.renameTo(fnew);
		
		/**
		 * 2、在源文件里面创建 文件名为 源文件名的文件
		 */
		JarOutputStream jaros = new JarOutputStream(new FileOutputStream(jarfile));
		JarFile jarf = new JarFile(tempJarPath);

		/**
		 * 3、将文件从临时文件路径读取到源文件路径下 此处通过判断名称，修改文件
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
		 * 4、删除临时文件中的数据
		 */
		File f = new File(tempJarPath);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * @MethodName : updateXmlFile
	 * @Description : 创建修改后的xml文件
	 * @param currentJarPath
	 * @param calendarId
	 * @return
	 */
	private static InputStream updateXmlFile(String currentJarPath, Document document) {
		InputStream is = null;
		String road = "";
		try {
			/**
			 * 1、取得临时文件的路径： a、获取jar文件的路径 b、创建临时文件的路径
			 * 
			 */
			// 临时文件文件夹位置
			road = currentJarPath.substring(0, currentJarPath.lastIndexOf("/"));

			// 建立文件夹
			File file = new File(road);
			if (!file.exists()) {// 存该文件夹
				file.mkdirs();// 新建
			}
			String EntryName = "/datasource.xml";

			// road为新xml的全路径
			road += EntryName;

			/**
			 * 2、根据传过来的Id值，在临时文件目录下，创建新的 xml文件
			 */
			Writer fileWriter = new FileWriter(road);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document); // 写入文件中
			xmlWriter.close();

			/**
			 * 3、将文件路径，以流的形式返回
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
	 * @Description : 读二进制文件
	 * @param is
	 *            文件流
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
