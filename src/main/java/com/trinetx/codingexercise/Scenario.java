package com.trinetx.codingexercise;

import java.util.Set;

public class Scenario {
	private Set<String> objects;
	private String startRoomId;
	
	public Scenario(String startRoomId, Set<String> objects) {
		this.startRoomId = startRoomId;
		this.objects = objects;
	}

	public String getStartRoomId() {
		return startRoomId;
	}

	public Set<String> getObjects() {
		return objects;
	}
}
