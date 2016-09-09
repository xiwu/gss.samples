package camelinaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

	public static String getFileStr(File file) {
		String content = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			content = getString(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return content;
	}

	private static String getString(InputStream is) throws IOException {
		int ch;
		StringBuilder sb = new StringBuilder();
		while ((ch = is.read()) != -1)
			sb.append((char) ch);
		return sb.toString();
	}
}
