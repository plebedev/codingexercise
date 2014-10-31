package com.trinetx.codingexercise;

import java.util.Map;


public class MazeSolver 
{
	public static final int North = 0;
	public static final int East = 1;
	public static final int South = 2;
	public static final int West = 3;
	
    public static void main( String[] args )
    {
    	if (args.length != 2) {
    		System.out.println("Wrong number of arguments.");
    		System.out.println("Usage: MazeSolver <map.xml> <scenario.txt>");
    		return;
    	}
    	
        System.out.println( "Hello World!" );
    }
    
    public MazeSolver(Map<String, MazeRoom> rooms, IRoomVisitor visitor) {
        this.rooms = rooms;
        this.visitor = visitor;
    }
    
    public void walkMaze(String startRoomId) {
    	MazeRoom room = rooms.get(startRoomId);
    	visitor.startVisit(room);
    	
    	for (int i = 0; i < 4; i++) {
    		if (visitor.isDone()) {
				return;
			}
    		MazeRoom nextRoom = room.getToRoom(i);
    		if (nextRoom != null) {
    			if (walkMaze(nextRoom, convertDirection(i)) && !visitor.isDone()) {
    				visitor.backToRoom(room);
    			}
    		}
    	}
    	
    	visitor.endVisit(room);
    }
    
	private boolean walkMaze(MazeRoom room, int incomingDirection) {
		/*When we walk the map, we see what direction we came from
        and start visiting rooms clockwise, so we visit all rooms
        but the one we came from. I.e. if we came from east, then we
        start walking south, west, then north.
		*/
		
		if (room.getState() == RoomState.visited || visitor.isDone()) {
			return false;
		}
		
		visitor.startVisit(room);
		//get next room to visit going clockwise
		int startRoomDir = (incomingDirection + 1) % 4;
		
		for (int i = 0; i < 3; i++) {
			int roomDir = (startRoomDir + i) % 4;
			MazeRoom nextRoom = room.getToRoom(roomDir);
			if (nextRoom == null || nextRoom.getState() == RoomState.visiting)  {
				continue;
			}
			walkMaze(nextRoom, convertDirection(roomDir));
			visitor.backToRoom(room);
		}

		visitor.endVisit(room);	
		
		return true;
    }
	
	private int convertDirection(int dir) {
		//This method converts outgoing dir for one room to incoming dir
    	//for the next room, i.e. if in room one you go south, incomung dir
    	//in room 2 is north
		return (dir+2) % 4;
	}
    
    
    private IRoomVisitor visitor;
    private Map<String, MazeRoom> rooms;
}
