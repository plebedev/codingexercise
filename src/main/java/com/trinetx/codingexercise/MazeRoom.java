package com.trinetx.codingexercise;

import java.util.Set;

public class MazeRoom implements IMazeRoom {
	
	private String id;
	private String name;
	private Set<String> objects;
	private MazeRoom toRooms[] = new MazeRoom[4];
	private RoomState state = RoomState.none;
	
	public MazeRoom(String id, String name, Set<String> objects) {
		this.id = id;
		this.name = name;
		this.objects = objects;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasItem(String item) {
		return objects != null && objects.contains(item);
	}

	@Override
	public boolean collectItem(String item) {
		if (!hasItem(item)) {
			return false;
		}
		
		objects.remove(item);
		
		return true;
	}
	
	@Override
	public void setState(RoomState state) {
		this.state = state;	
	}
	
	public void setToRoom(int i, MazeRoom room) {
		if (i < 0 || i > 3) {
			throw new IllegalArgumentException("Room direction is invalid. It should be between 0 and 3 inclusive");
		}
		toRooms[i] = room;
	}
	
	public MazeRoom getToRoom(int i) {
		if (i < 0 || i > 3) {
			throw new IllegalArgumentException("Room direction is invalid. It should be between 0 and 3 inclusive");
		}
		return toRooms[i];
	}
	
	public RoomState getState() {
		return state;
	}
}
