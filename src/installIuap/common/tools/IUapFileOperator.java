package installIuap.common.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

/**
 * IUAPÎÄ¼þ²Ù×÷
 * 
 * @author zhaoti
 * 
 */
public class IUapFileOperator {
	public void writeFile(String filepath, String content) {
		if ((content == null) || (content.length() == 0)) {
			return;
		}

		File file = new File(filepath);
		file.getParentFile().mkdirs();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeFile(String filepath, String content, boolean coderule) {
		if ((content == null) || (content.length() == 0)) {
			return;
		}

		File file = new File(filepath);
		file.getParentFile().mkdirs();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			writer.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeXmlDoc(String filepath, Document doc) throws Exception {
		File file = new File(filepath);
		file.getParentFile().mkdirs();
		XMLWriter writer = new XMLWriter(new FileWriter(filepath));
		writer.write(doc);
		writer.close();
	}

	public void writeFileAdd(String filepath, String content) {
		File file = new File(filepath);
		file.getParentFile().mkdirs();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String readTemplateFile(String filepath) {
		StringBuffer tmp = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "GBK"));
			String tmpLine = null;

			tmpLine = br.readLine();
			while (tmpLine != null) {
				tmp.append(tmpLine).append("\n");

				tmpLine = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return tmp.toString();
	}

	public void saveObjInFile(String path) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File f = new File(path);
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				oos.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				oos.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getNodeLst(String path) {
		List lst = new ArrayList();
		File file = new File(path);

		if (file.canRead()) {
			for (File f : file.listFiles()) {
				lst.add(f.getName());
			}
		}

		return lst;
	}

	public List<String> getFileList(String directory) {
		List<String> fileList = new ArrayList<String>();
		File directoryFile = new File(directory);
		if (directoryFile.isDirectory()) {
			File[] files = directoryFile.listFiles();
			if (files.length > 0) {
				for (File file : files) {
					fileList.add(file.getName());
				}
			}
		}
		return fileList;
	}
}