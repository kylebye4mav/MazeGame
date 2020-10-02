package edu.lewisu.cs.kylevbye.mazegame;

import java.util.ArrayList;

import edu.lewisu.cs.kylevbye.util.IntPair;
import edu.lewisu.cs.kylevbye.util.TextFileReader;

public class RoomReader {
	
	///
	///	Fields
	///
	private static ArrayList<Room> roomList = new ArrayList<Room>();
	private static ArrayList<String> mapStringList = new ArrayList<String>();
	private static IntPair buildingSize;
	
	///
	///	Getters
	///
	public static ArrayList<Room> getRoomList() { return roomList; }
	public static ArrayList<String> getMapStringList() { return mapStringList; }
	public static IntPair getBuildingSize() { return buildingSize; }
	
	///
	///	Functions
	///
	public static boolean readFile(String fileNameIn) {
		
		TextFileReader reader = new TextFileReader(fileNameIn);
		
		if (reader.readFile()) {
			
			//	Initialize fields and unprocessed data
			String[] readData = reader.getData();
			roomList = new ArrayList<Room>();
			mapStringList = new ArrayList<String>();
			buildingSize = null;
			
			//	Process all the data
			for (String s : readData) {
				
				if (!s.equals("")) {
					
					if (s.charAt(0) == '*') processBuildingSize(s);
					else if (s.charAt(0) == '$') roomList.add(processRoom(s));
					else if (!(s.charAt(0) == '#')) mapStringList.add(s);
				
				}
				
			}
			
			
		}
		else {
			//	Failed to read file
			System.err.println("Error reading the following file: " + fileNameIn);
			return false;
		}
		
		//	Final Checks
		if (!finalCheck()) {
			
			//	Process Failed. Reset all fields.
			roomList = null;
			mapStringList = null;
			buildingSize = null;
			
		}	
		
		return true;
		
	}

	public static Room processRoom(String roomStringIn) {
		
		Room returnRoom;
		
		String identifier, name, description;
		String[] roomStringInParts = roomStringIn.split("\t");
		
		identifier = roomStringInParts[1];
		name = roomStringInParts[2];
		description = roomStringInParts[3];
		returnRoom = new Room(identifier, name, description);
		
		return returnRoom;
		
	}
	
	public static String linkRooms() {
		return null;
	}
	
	private static void processBuildingSize(String buildingSizeStringIn) {
		
		String[] buildingSizeStringInParts = buildingSizeStringIn.split("\t");
		
		int firstInt = Integer.parseInt(buildingSizeStringInParts[1]);
		int secondInt = Integer.parseInt(buildingSizeStringInParts[2]);
		
		buildingSize = new IntPair(firstInt, secondInt);
	}
	
	private static boolean finalCheck() {
		
		if (roomList.size() == 0) {
			System.err.println("No Rooms were parsed.");
			return false;
		}
		
		if (mapStringList.size() == 0) {
			System.err.println("No Room Map Strings were parsed");
			return false;
		}
		
		if (buildingSize == null) {
			System.err.println("No buildingSize was parsed.");
			return false;
		}
		
		//	Passed
		return true;
		
	}

	private RoomReader() {}

}
