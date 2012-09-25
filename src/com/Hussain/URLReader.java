package com.Hussain;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * A utility class providing features for reading from a URL.
 * 
 * This class is meant to be used as follows: <code>
 * String sData = URLReader.readURL ("http://www.mypage.net/...");
 * if (sData != null) {
 *   // do something with the data read
 * }
 * </code>
 * 
 * @author Christian Urban
 * @author Steffen Zschaler
 * 
 */
public class URLReader {
	/**
	 * Function for reading data from a URL. This will return <code>null</code>
	 * in case of any error and the actual data otherwise. The data returned may
	 * consist of a number of lines, which will be separated by '\n'. If there
	 * is any data at all, the string returned will be terminated by a '\n'
	 * character.
	 * 
	 * @param sUrl
	 *            the URL to read from. Must be an absolute URL including a
	 *            protocol identifier.
	 * @return The data obtained by reading from the URL. Assumes textual data.
	 *         If any error occurs, the function returns <code>null</code>.
	 */
	public static String readURL(String sUrl) {
		StringBuilder buf = new StringBuilder();
		Scanner in = null;

		try {
			URL url = new URL(sUrl);
			in = new Scanner(url.openStream());

			while (in.hasNextLine()) {
				buf.append(in.nextLine() + "\n");
			}

			return buf.toString();
		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return null;
	}
}