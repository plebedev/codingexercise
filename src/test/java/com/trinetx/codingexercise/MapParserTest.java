package com.trinetx.codingexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class MapParserTest {

	@Test
	public void testParseMap() throws ParserConfigurationException, SAXException, IOException {
		MapParser parser = new MapParser();
		Map<String, MazeRoom> map = parser.parse("test/testmap.xml");
		assertEquals(4, map.size());
		
		MazeRoom room1 = map.get("1");
		assertEquals("Hallway", room1.getName());
		assertFalse(room1.hasItem("Potted Plant"));
		assertEquals("2", room1.getToRoom(MazeMap.North));
		assertNull(room1.getToRoom(MazeMap.East));
		assertNull(room1.getToRoom(MazeMap.West));
		assertNull(room1.getToRoom(MazeMap.South));
		
		MazeRoom room2 = map.get("2");
		assertEquals("Dining Room", room2.getName());
		assertFalse(room2.hasItem("Potted Plant"));
		assertEquals("1", room2.getToRoom(MazeMap.South));
		assertEquals("3", room2.getToRoom(MazeMap.West));
		assertEquals("4", room2.getToRoom(MazeMap.East));
		assertNull(room2.getToRoom(MazeMap.North));
		
		MazeRoom room3 = map.get("3");
		assertEquals("Kitchen", room3.getName());
		assertTrue(room3.hasItem("Knife"));
		assertTrue(room3.hasItem("Shovel"));
		assertFalse(room3.hasItem("Potted Plant"));
		assertEquals("2", room3.getToRoom(MazeMap.East));
		assertNull(room3.getToRoom(MazeMap.North));
		assertNull(room3.getToRoom(MazeMap.West));
		assertNull(room3.getToRoom(MazeMap.South));
		
		MazeRoom room4 = map.get("4");
		assertEquals("Sun Room", room4.getName());
		assertTrue(room4.hasItem("Potted Plant"));
		assertEquals("2", room4.getToRoom(MazeMap.West));
		assertNull(room4.getToRoom(MazeMap.East));
		assertNull(room4.getToRoom(MazeMap.North));
		assertNull(room4.getToRoom(MazeMap.South));
		
	}
}
