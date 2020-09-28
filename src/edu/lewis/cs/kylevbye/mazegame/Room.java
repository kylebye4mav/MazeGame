package edu.lewis.cs.kylevbye.mazegame;

import java.util.LinkedHashMap;
/**
 * 
 */
public class Room {

    ///
    /// Fields
    ///
    private String name;
    private String description;
    private LinkedHashMap<String, Room> neighbors;

    ///
    /// Getters
    ///
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LinkedHashMap<String, Room> getNeighbors() { return neighbors; }

    ///
    /// Setters
    ///
    public void setName(String nameIn) { name = nameIn;}
    public void setDescription(String descriptionIn) { description = descriptionIn; }
    public void setNeighbors(LinkedHashMap<String, Room> neighborsIn) { neighbors = neighborsIn; }

    ///
    /// Functions
    ///
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
     * Sets the nighbor of the room in the provided
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
        return String.format( "%s\t%s\t%s\t%s", getNeighborName("N"),
            getNeighborName("S"), getNeighborName("E"), getNeighborName("W")
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
            "%s\t%s\t%s", name, description, getNeighborsAsString()
            );
    }

    ///
    /// Constructor
    ///
    public Room() {
        setName("");
        setDescription("");
        neighbors = new LinkedHashMap<String, Room>();
    }

    public Room(String nameIn, String descriptionIn) {
        this();
        setName(nameIn);
        setDescription(descriptionIn);
    }
    
}