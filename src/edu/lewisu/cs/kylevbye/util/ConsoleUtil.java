package edu.lewisu.cs.kylevbye.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * This class uses Java's provided BufferedReader to take input
 * from the input stream (most likely being from the terminal).
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
		
		try { line = br.readLine();}
		catch (IOException ioe) { line = null; }
		
		return line;
	}
	
	/**
	 * Prints the provided string object to the output stream.
	 * Returns a String if no errors occur in reading the input
	 * from the input stream. Otherwise, null is returned.
	 * 
	 * @param	line	line to print to output stream.
	 * @return	string object or null.
	 */
	public static String readLine(String line) {
		
		System.out.print(line);
		
		return readLine();
		
	}

	//	Follows Singleton pattern.
	private ConsoleUtil() {}

}
