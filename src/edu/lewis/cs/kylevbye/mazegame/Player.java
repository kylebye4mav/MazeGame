/**
 * 
 */
package edu.lewis.cs.kylevbye.mazegame;

/**
 * This class is a container for a player's
 * name and health.
 */
public class Player {

	///
	/// Fields
	///
	private String name;
	private int health;

	///
	/// Getters
	///
	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}



	///
	/// Setters
	///
	public void setName(String nameIn) {
		this.name = nameIn;
	}
	
	public void setHealth(int healthIn) {
		this.health = healthIn;
	}


	///
	///	Fields
	///
	/**
	 * Initializes player's name and health to ""
	 * and 100 respectively.
	 */
	public Player() {
		this(new String(), 100);
	}
	
	/**
	 * Initializes player's name and health to the
	 * provided <code>nameIn</code> and 100 respectively.
	 * 
	 * @param	nameIn	player's name
	 */
	public Player(String nameIn) {
		this(nameIn, 100);
	}
	
	/**
	 * Initializes player's name and health to the
	 * provided <code>nameIn</code> and the provided 
	 * <code>healthIn</code> respectively.
	 * 
	 * @param	nameIn	player's name
	 * @param	healthIn	player's health value
	 */
	public Player(String nameIn, int healthIn) {
		setName(nameIn); setHealth(healthIn);
	}

}
