package edu.lewisu.cs.kylevbye.mazegame;

import java.util.ArrayList;

import edu.lewisu.cs.kylevbye.util.IntPair;

/**
 * This class is a container for a name
 * and a list of rooms.
 * 
 * @author	Kyle V Bye
 * @see java.util.ArrayList
 */
public class Building {
	
	///
	///	Fields
	///
	private String name;
	private ArrayList<Room> roomList;
	
	///
	///	Getters
	///
	public String getName() { return this.name; }
	public ArrayList<Room> getRoomList() { return this.roomList; }
	
	///
	///	Setters
	///
	public void setName(String nameIn) { this.name = nameIn; }
	public void setRoomList(ArrayList<Room> roomListIn) { this.roomList = roomListIn; }
	
	///
	///	Functions
	///
	/**
	 * Returns a random room from roomList. Returns
	 * null is roomList is null or empty.
	 * 
	 * @return	a random room/null
	 */
	public Room getRandomRoom() {
		if (roomList == null || roomList.isEmpty()) return null;
		return roomList.get((int)(Math.random()*(roomList.size()-1)));
	}
	
	/**
	 * Initializes all the room's neighbors based on the Strings given.
	 * Size is determined by buildingSize. Method will stop execution if 
	 * any of the parameters are null or if mapStringList is empty.
	 * 
	 * @param	mapStringList	a hopefully not empty list of 
	 * 			strings determining map layout.
	 * @param	buldingSize	container of two integers determining 
	 * 			the size of the building
	 */
	public void linkRooms(ArrayList<String> mapStringList, IntPair buldingSize) {
		
		if (roomList == null || roomList.size() == 0 || buldingSize == null) {
			return;
		}
		
		//	Provided from room size
		int rows, cols;
		rows = buldingSize.getI1(); 
		cols = buldingSize.getI2();
		
		String[][] identifierMap = new String[rows][cols];
		
		for (int i = 0; i<mapStringList.size(); ++i) {
			
			identifierMap[i] = mapStringList.get(i).split("\t");
			
		}
		
		for (int i = 0; i<rows; ++i) {
			
			for (int j = 0; j<cols; ++j) {
				
				Room selectedRoom = getRoom(identifierMap[i][j]);
				Room neighborRoom;
				
				//	North
				if ((i + 1 < rows)) {
					
					neighborRoom = getRoom(identifierMap[i+1][j]);
					if (neighborRoom != null) neighborRoom.setNeighbor(
							"N", selectedRoom
							);
					
					
				}
				
				//	East
				if (j - 1 >= 0) {
					
					neighborRoom = getRoom(identifierMap[i][j-1]);
					if (neighborRoom != null) neighborRoom.setNeighbor(
							"E", selectedRoom
							);
					
				}
				
				//	South
				if (i - 1 >= 0) {
					
					neighborRoom = getRoom(identifierMap[i-1][j]);
					if (neighborRoom != null) neighborRoom.setNeighbor(
							"S", selectedRoom
							);
					
				}
				//	West
				if (j + 1 < cols) {
					
					neighborRoom = getRoom(identifierMap[i][j+1]);
					if (neighborRoom != null) neighborRoom.setNeighbor(
							"W", selectedRoom
							);
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Returns the room at the direction and room provided.
	 * 
	 * @param	roomIn	starting room
	 * @param	directionIn	direction from the starting room
	 * @return	room in that direction/null
	 */
	public Room getRoom(Room roomIn, String directionIn) {
		return roomIn.getNeighbor(directionIn);
	}
	
	/**
	 * Returns the room that has the provided identifier.
	 * 
	 * @param identifierIn
	 * @return
	 */
	public Room getRoom(String identifierIn) {
		for (Room room : roomList) if (room.getIdentifier().equals(identifierIn)) return room;
		return null;
	}
	
	/**
	 * Adds a room to the roomList
	 * @param	rm	room to add.
	 */
	public void addRoom(Room rm) {
		roomList.add(rm);
	}
	
	///
	///	toString
	///
	/**
	 * Returns a String in the form of
	 * [name, {room1, room2, ...}]
	 */
	@Override
	public String toString() {
		String roomListStr = "{";
		for (int i = 0; i<roomList.size(); ++i) {
			Room r = roomList.get(i);
			roomListStr += r.toString();
			if (i != roomList.size()-1) roomListStr += ", ";
		}
		roomListStr += "}";
		return String.format("[%s, {%s}]", this.name, roomListStr);
	}
	
	///
	///	Constructors
	///
	/**
	 * Default constructor calls other constructor
	 * Building(String) with an empty String.
	 * 
	 * @see Building#Building(String)
	 */
	public Building() {
		this(new String());
	}
	
	/**
	 * Initializes this building with a name and
	 * an empty roomList. Recommended to set the
	 * room list yourself.
	 * 
	 * @param	nameIn	name of building
	 */
	public Building(String nameIn) {
		setName(nameIn);
		roomList = new ArrayList<Room>();
	}
	
}