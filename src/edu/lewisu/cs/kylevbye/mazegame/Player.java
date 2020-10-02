/**
 * 
 */
package edu.lewisu.cs.kylevbye.mazegame;

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
	///	Functions
	///
	/**
	 * Reduce the player health by the provided
	 * damage points. Using negative values results
	 * in nothing occuring. If health drops below 0,
	 * it will be set back to 0.
	 * 
	 * @param	damageIn	amount of health to decrease
	 */
	public void takeDamage(int damageIn) {
		
		if (damageIn > 0) {
			
			health -= damageIn;
		
			//	Health is not allowed to surpass into
			//	the negatives.
			if (health <= 0) setHealth(0);
			
		}
		
	}
	
	/**
	 * Increase the player health by the provided
	 * health points. Using negative values results
	 * in nothing going on. If health goes above 100,
	 * it will be set to 100.
	 * 
	 * @param	healthPointsIn	amount of health to increase
	 */
	public void heal(int healthPointsIn) {
		
		if (healthPointsIn > 0) {
			
			//	Health is not allowed to surpass.
			health += healthPointsIn;
			if (health > 100) setHealth(100);
			
		}
		
	}
	
	///
	///	toString
	///
	@Override
	public String toString() {
		return String.format("[%s, health:%d]", this.name, this.health);
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
