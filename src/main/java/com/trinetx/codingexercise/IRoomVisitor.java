package com.trinetx.codingexercise;

public interface IRoomVisitor {
	/**
	 * 
	 * @param room to start visit for
	 */
	void startVisit(IMazeRoom room);
	
	/**
	 * @param room that player got back to
	 */
	void backToRoom(IMazeRoom room);
	
	/**
	 * 
	 * @param room to end visit for
	 */
	void endVisit(IMazeRoom room);
	
	/**
	 * 
	 * @return true if done with a task after visiting rooms
	 */
	boolean isDone();
}
