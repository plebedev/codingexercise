package com.trinetx.codingexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MazeMapTest {

	@Test
	public void testWalkSimpleCaseNothingToCollect() {
		Map<String, MazeRoom> map = buildSimpleMap(null);
		RoomVisitor visitor = new RoomVisitor(null);
		MazeMap mazeMap = new MazeMap(map);
		
		mazeMap.walkMaze("1", visitor);
		
		assertEquals(1, visitor.getActionLog(false).size());
	}
	
	@Test
	public void testWalkSimpleCaseItemNotInMaze() {
		Map<String, MazeRoom> map = buildSimpleMap(null);
		Set<String> items = new HashSet<String>();
		items.add("item");
		RoomVisitor visitor = new RoomVisitor(items);
		MazeMap mazeMap = new MazeMap(map);
		
		mazeMap.walkMaze("1", visitor);
		
		assertFalse(visitor.isDone());
		assertEquals(9, visitor.getActionLog(false).size());
	}
	
	@Test
	public void testWalkSimpleCaseItemInStartRoom() {
		Set<String> items = new HashSet<String>();
		items.add("item");
		Map<String, Set<String>> objects = new HashMap<String, Set<String>>();
		Set<String> items1 = new HashSet<String>();
		items1.add("item");
		objects.put("1", items1);
		Map<String, MazeRoom> map = buildSimpleMap(objects);
		
		RoomVisitor visitor = new RoomVisitor(items);
		MazeMap mazeMap = new MazeMap(map);
		
		mazeMap.walkMaze("1", visitor);
		
		assertTrue(visitor.isDone());
		assertEquals(3, visitor.getActionLog(true).size());
	}
	
	@Test
	public void testWalkSimpleCaseItemInForthRoom() {
		Set<String> items = new HashSet<String>();
		items.add("item");
		Map<String, Set<String>> objects = new HashMap<String, Set<String>>();
		Set<String> items1 = new HashSet<String>();
		items1.add("item");
		objects.put("4", items1);
		Map<String, MazeRoom> map = buildSimpleMap(objects);
		
		RoomVisitor visitor = new RoomVisitor(items);
		MazeMap mazeSolver = new MazeMap(map);
		
		mazeSolver.walkMaze("1", visitor);
		
		assertTrue(visitor.isDone());
		assertEquals(6, visitor.getActionLog(false).size());
	}
	
	@Test
	public void testWalkSimpleCaseMultipleItems() {
		Set<String> items = new HashSet<String>();
		items.add("item");
		items.add("item2");
		RoomVisitor visitor = new RoomVisitor(items);
		
		Map<String, Set<String>> objects = new HashMap<String, Set<String>>();
		Set<String> items1 = new HashSet<String>();
		items1.add("item");
		Set<String> items2 = new HashSet<String>();
		items2.add("item2");
		objects.put("6", items1);
		objects.put("4", items2);
		
		Map<String, MazeRoom> map = buildMapWithCircle(objects);
		
		
		MazeMap mazeMap = new MazeMap(map);
		
		mazeMap.walkMaze("1", visitor);
		
		assertTrue(visitor.isDone());
		assertEquals(8, visitor.getActionLog(false).size());
	}
	
	@Test
	public void testWalkCircle() {
		Map<String, MazeRoom> map = buildMapWithCircle(null);
		Set<String> items = new HashSet<String>();
		items.add("item");
		RoomVisitor visitor = new RoomVisitor(items);
		MazeMap mazeMap = new MazeMap(map);
		
		mazeMap.walkMaze("1", visitor);
		
		assertFalse(visitor.isDone());
		assertEquals(11, visitor.getActionLog(false).size());
	}

	private Map<String, MazeRoom> buildMapWithCircle(Map<String, Set<String>> objects) {
		Map<String, MazeRoom> ret = buildSimpleMap(objects);
		MazeRoom room6 = new MazeRoom("6", "Sunroom", getObjects(objects, "6"));
		MazeRoom room2 = ret.get("2");
		MazeRoom room3 = ret.get("3");
		
		room2.setToRoom(MazeMap.East, "6");
		room3.setToRoom(MazeMap.North, "6");
		
		room6.setToRoom(MazeMap.West, "2");
		room6.setToRoom(MazeMap.South, "3");
		
		ret.put(room6.getId(), room6);
		
		return ret;
	}

	private Map<String, MazeRoom> buildSimpleMap(Map<String, Set<String>> objects) {
		MazeRoom room1 = new MazeRoom("1", "Dining", getObjects(objects, "1"));
		MazeRoom room2 = new MazeRoom("2", "Hallway", getObjects(objects, "2"));
		MazeRoom room3 = new MazeRoom("3", "Kitchen", getObjects(objects, "3"));
		MazeRoom room4 = new MazeRoom("4", "Family", getObjects(objects, "4"));
		MazeRoom room5 = new MazeRoom("5", "Living", getObjects(objects, "5"));
		
		room1.setToRoom(MazeMap.North, "2");
		room1.setToRoom(MazeMap.East, "3");
		room1.setToRoom(MazeMap.South, "4");
		room1.setToRoom(MazeMap.West, "5");
		
		room2.setToRoom(MazeMap.South, "1");
		room3.setToRoom(MazeMap.West,  "1");
		room4.setToRoom(MazeMap.North, "1");
		room5.setToRoom(MazeMap.East,  "1");
		
		Map<String, MazeRoom> ret = new HashMap<String, MazeRoom>();
		
		ret.put(room1.getId(), room1);
		ret.put(room2.getId(), room2);
		ret.put(room3.getId(), room3);
		ret.put(room4.getId(), room4);
		ret.put(room5.getId(), room5);
		
		return ret;
	}
	

	private Set<String> getObjects(Map<String, Set<String>> objects,
			String roomId) {
		if (objects != null) {
			return objects.get(roomId);
		}
		return null;
	}

}
