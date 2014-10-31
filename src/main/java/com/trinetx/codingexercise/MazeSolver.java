package com.trinetx.codingexercise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.trinetx.codingexercise.ScenarioParser.InvalidFormatException;


public class MazeSolver 
{
	public static void main( String[] args )
    {
    	if (args.length != 2) {
    		System.out.println("Wrong number of arguments.");
    		System.out.println("Usage: MazeSolver <map.xml> <scenario.txt>");
    		return;
    	}
    	
    	String mapFile = args[0];
    	MazeMap map = null;
    	try {
			map = MapParser.parseMapFile(mapFile);
		} catch (ParserConfigurationException e) {
			System.out.println("Parser mis-configuration, error="+ e.getMessage());
			return;
		} catch (SAXException e) {
			System.out.println("Error parsing map file=" + mapFile + ", error="+ e.getMessage());
		} catch (IOException e) {
			System.out.println("Error reading map file=" + mapFile + ", error="+ e.getMessage());
			return;
		}
    	
    	Scenario scenario = null;
    	String scenarioFile = args[1];
    	try {
			scenario = ScenarioParser.parseScenarioFile(scenarioFile);
		} catch (FileNotFoundException e) {
			System.out.println("Scenario file not found; file=" + scenarioFile + ", error="+ e.getMessage());
			return;
		} catch (InvalidFormatException e) {
			System.out.println("Scenario file is empty; file=" + scenarioFile + ", error="+ e.getMessage());
			return;
		}
    	
    	RoomVisitor visitor = new RoomVisitor(scenario.getObjects());
    	map.walkMaze(scenario.getStartRoomId(), visitor);
    	List<String> actionLog = visitor.getActionLog(true);
    	
    	for (String action : actionLog) {
    		System.out.println(action);
    	}
    }
}
