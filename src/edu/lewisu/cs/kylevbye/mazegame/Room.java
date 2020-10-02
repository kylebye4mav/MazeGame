package edu.lewisu.cs.kylevbye.mazegame;

import java.util.LinkedHashMap;
/**
 * 
 */
public class Room {

    ///
    /// Fields
    ///
	private String identifier;
    private String name;
    private String description;
    private LinkedHashMap<String, Room> neighbors;
    private Actionable action;

    ///
    /// Getters
    ///
    public String getIdentifier() { return identifier; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LinkedHashMap<String, Room> getNeighbors() { return neighbors; }
    public Actionable getAction() { return action; }

    ///
    /// Setters
    ///
    public void setIdentifier(String identifierIn) { this.identifier = identifierIn; }
    public void setName(String nameIn) { this.name = nameIn;}
    public void setDescription(String descriptionIn) { this.description = descriptionIn; }
    public void setNeighbors(LinkedHashMap<String, Room> neighborsIn) { this.neighbors = neighborsIn; }
    public void setAction(Actionable actionIn) { this.action = actionIn; }
    
    ///
    /// Functions
    ///
    public void performAction() {
    	action.performAction();
    }
    
    /**
     * Returns the Room that's in the provided
     * direction.
     * 
     * @param   directionIn a cardinal direction
     * @return  the room in provided direction (null possible).
     */
    public Room getNeighbor(String directionIn) {
        return neighbors.get(directionIn);
    }

    /**
     * Sets the neighbor of the room in the provided
     * direction.
     * 
     * @param   directionIn a cardinal direction
     * @param   roomIn  the room to set at provided position.
     */
    public void setNeighbor(String directionIn, Room roomIn) {
        neighbors.put(directionIn, roomIn);
    }

    /**
     * Returns the name of the room of the provided direction
     * if the room exists. Otherwise, "" is returned.
     * 
     * @param   directionIn a cardinal direction
     * @return  name of room or ""
     */
    public String getNeighborName(String directionIn) {
        Room rm = getNeighbor(directionIn);
        if (rm != null) return rm.getName();
        else return new String();
    }

    /**
     * Returns a String Representation of the neighbors
     * of this room instance.
     * 
     * @return  string representation for neighboring rooms (tab-delimited).
     */
    public String getNeighborsAsString() {
        return String.format( "{N:%s, E:%s, S:%s, W:%s}", getNeighborName("N"),
            getNeighborName("E"), getNeighborName("S"), getNeighborName("W")
            );
    }

    /**
     * Returns true if this instance of room has
     * the same name as the provided room.
     * 
     * @param   other   room to compare.
     * @return  true/false.
     */
    @Override
    public boolean equals(Object other) {
        Room otherRoom = (Room)other;
        return name.equalsIgnoreCase(otherRoom.getName());
    }

    /**
     * Returns true if this instance of room's name
     * is the same as the provided name.
     * 
     * @param   otherName   name to compare.
     * @return  true/false.
     */
    public boolean equals(String otherName) {
        return name.equalsIgnoreCase(otherName);
    }
    
    ///
    /// toString
    ///
    @Override
    public String toString() {
        return String.format(
            "[%s, %s, %s]", name, description, getNeighborsAsString()
            );
    }

    ///
    /// Constructor
    ///
    public Room() {
    	setIdentifier("");
        setName("");
        setDescription("");
        neighbors = new LinkedHashMap<String, Room>();
        action = new Actionable() {
        	public void performAction() {
        		System.out.println("Null Action");
        	}
        };
    }

    public Room(String identifierIn, String nameIn, String descriptionIn) {
        this();
        setIdentifier(identifierIn);
        setName(nameIn);
        setDescription(descriptionIn);
    }
    
}