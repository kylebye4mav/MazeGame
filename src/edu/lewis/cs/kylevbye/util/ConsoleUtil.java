package edu.lewis.cs.kylevbye.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 
 * @author Kyle Bye
 * @see BufferedReader
 * @see InputStreamReader
 * @see IOException
 */
public class ConsoleUtil {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Returns a String if no errors occur in reading the input
	 * from the input stream. Otherwise, null is returned.
	 * 
	 * @return String object or null.
	 */
	public static String readLine() {
		String line;
		try {
			line = br.readLine();
		}
		catch (IOException ioe) {
			line = null;
		}
		return line;
	}

	//	Follows Singleton pattern.
	private ConsoleUtil() {}

}
