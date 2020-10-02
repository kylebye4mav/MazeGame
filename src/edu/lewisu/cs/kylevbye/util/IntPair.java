package edu.lewisu.cs.kylevbye.util;

/**
 * This class is a container for two integers.
 * 
 * @author	Kyle V Bye
 */
public class IntPair {
	
	///
	///	Fields
	///
	private int i1;
	private int i2;
	
	///
	///	Getters
	///
	public int getI1() { return i1; }
	public int getI2() { return i2; }
	
	///
	///	Setters
	///
	public void setI1(int i1In) { this.i1 = i1In; }
	public void setI2(int i2In) { this.i2 = i2In; }
	
	///
	///	toString
	///
	/**
	 * Returns a string in the form of {i1, i2}
	 * @return	string containing two integers.
	 */
	@Override
	public String toString() { return String.format("{%d, %d}", this.i1, this.i2); }
	
	///
	///	Constructors
	///
	/**
	 * Initializes i1 and i2 to 0.
	 */
	public IntPair() {
		this(0,0);
	}
	/**
	 * Initializes i1 and i2 to the provided
	 * parameters appropriately.
	 * 
	 * @param	i1In	first integer
	 * @param	i2In	second integer
	 */
	public IntPair(int i1In, int i2In) {
		setI1(i1In); setI2(i2In);
	}
	
}
