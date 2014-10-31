package com.trinetx.codingexercise;

public interface IMazeRoom {
	
	/**
	 * 
	 * @return id of the room
	 */
	String getId();
	
	/**
	 * 
	 * @return name of the room being visited
	 */
	String getName();
	
	/**
	 * 
	 * @param item name of the item 
	 * @return true if the room has the item, false otherwise
	 */
	boolean hasItem(String item);
	
	/**
	 * 
	 * @param item name of the item to be collected
	 * @return true, if item is collected, false otherwise (i.e. if there is no such item in the room)
	 */
	boolean collectItem(String item);

	/**
	 * Set the room state (visiting, visited)
	 */
	void setState(RoomState state);
}
