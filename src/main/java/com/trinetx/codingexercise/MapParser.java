package com.trinetx.codingexercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MapParser {
	
	public static MazeMap parseMapFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
		MapParser parser = new MapParser();
		return new MazeMap(parser.parse(fileName));
	}
	
	
	MapParser() {
	}

	Map<String, MazeRoom>  parse(String fileName) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse(fileName);

		return parseDOM(dom);
	}

	private Map<String, MazeRoom> parseDOM(Document dom) {
		Element rootElement = dom.getDocumentElement();
		NodeList rooms = rootElement.getElementsByTagName("room");
		
		if (rooms == null || rooms.getLength() == 0) {
			return null;
		}
		
		Map<String, MazeRoom> ret = new  HashMap<String, MazeRoom>();
		
		for (int i = 0; i < rooms.getLength(); i++) {
			MazeRoom room = parseRoom(rooms.item(i));
			ret.put(room.getId(), room);
		}
		 
		return ret;
	}


	private MazeRoom parseRoom(Node item) {
		Element roomElem = (Element) item;
		String roomId = roomElem.getAttribute("id");
		String roomName = roomElem.getAttribute("name");
		Set<String> objects = parseObjects(roomElem.getElementsByTagName("object"));
		
		MazeRoom ret = new MazeRoom(roomId, roomName, objects);
		
		String north = getAttributeValue(roomElem, "north");
		if (north != null) {
			ret.setToRoom(MazeMap.North, north);
		}
		
		String east = getAttributeValue(roomElem, "east");
		if (east != null) {
			ret.setToRoom(MazeMap.East, east);
		}
		
		String south = getAttributeValue(roomElem, "south");
		if (south != null) {
			ret.setToRoom(MazeMap.South, south);
		}
		
		String west = getAttributeValue(roomElem, "west");
		if (west != null) {
			ret.setToRoom(MazeMap.West, west);
		}
		
		return ret;
	}
	
	String getAttributeValue(Element elem, String name) {
		String attr = elem.getAttribute(name);
		if (attr == null || attr.isEmpty()) {
			return null;
		}
		return attr;
	}


	private Set<String> parseObjects(NodeList objectList) {
		if (objectList == null || objectList.getLength() == 0) {
			return null;
		}
		
		Set<String> ret = new HashSet<String>();
		for (int i = 0; i < objectList.getLength(); i++) {
			ret.add(((Element) objectList.item(i)).getAttribute("name"));
		}
		
		return ret;
	}
}
