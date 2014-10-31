package com.trinetx.codingexercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScenarioParser {
	public static Scenario parseScenarioFile(String fileName) throws InvalidFormatException, FileNotFoundException {
		String startRoomId = null;
		Set<String> objects = new HashSet<String>();
		
		File file = new File(fileName);
		
		Scanner sc = new Scanner(file);
		
		try {
			if (!sc.hasNextLine()) {
				throw new InvalidFormatException("File " + fileName + "is empty!");
			}
			
			startRoomId = sc.next();
			
			while (sc.hasNextLine()) {
				objects.add(sc.next());
			}
		} finally {
			sc.close();
		}
		
		return new Scenario(startRoomId, objects);
	}
	
	public static class InvalidFormatException extends Exception {
		static final long serialVersionUID = -4452740347552927781L;

		public InvalidFormatException(String message) {
			super(message);
		}
	}
}
