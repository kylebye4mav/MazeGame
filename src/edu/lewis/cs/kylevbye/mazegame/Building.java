package edu.lewis.cs.kylevbye.mazegame;

public class Building {
	
	public Room getRoom(Room rm, String directionIn) {
		return rm.getNeighbor(directionIn);
	}
	
	public void addRoom(Room rm ) {
		rooms.add(rm);
	}
    
	public void addRoom(String startFromIn, String directionIn, String newRoomNameIn, String descriptionIn) {
		Room startRoom = findRoomByName(startFromIn);
		if (startRoom != null) startRoom.setNeighbor(directionIn, descripti);
	}
}