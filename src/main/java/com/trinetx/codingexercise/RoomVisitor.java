package com.trinetx.codingexercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RoomVisitor implements IRoomVisitor {

	public RoomVisitor(Set<String> itemsToCollect) {
		this.itemsToCollect = itemsToCollect;
		this.actionLog = new ArrayList<String>();
	}
	
	@Override
	public void startVisit(IMazeRoom room) {
		room.setState(RoomState.visiting);
		StringBuilder log = createLog(room);
		log.append('\t');
		boolean anythingCollected = false;
		if (itemsToCollect != null) {
			Set<String> collectedItems = new HashSet<String>();
			Iterator<String> itemsIter = itemsToCollect.iterator();
			while (itemsIter.hasNext()) {
				String item = itemsIter.next();
				if (room.hasItem(item)) {
					if (room.collectItem(item)) {
						anythingCollected = true;
						collectedItems.add(item);
						log.append(item);
					}
				}
			}
			itemsToCollect.removeAll(collectedItems);
		}
		
		if (!anythingCollected) {
			log.append("None");
		}
		
		actionLog.add(log.toString());
	}

	@Override
	public void endVisit(IMazeRoom room) {
		room.setState(RoomState.visited);
		
	}
	
	@Override
	public void backToRoom(IMazeRoom room) {
		actionLog.add(createLog(room).toString());
	}
	
	private StringBuilder createLog(IMazeRoom room) {
		StringBuilder log = new StringBuilder();
		log.append(room.getId()).append("\t").append(room.getName());
		
		return log;
	}

	@Override
	public boolean isDone() {
		return itemsToCollect == null || itemsToCollect.isEmpty();
	}
	
	public List<String> getActionLog() {
		return actionLog;
	}
	
	private Set<String> itemsToCollect;
	private List<String> actionLog;
	
}
