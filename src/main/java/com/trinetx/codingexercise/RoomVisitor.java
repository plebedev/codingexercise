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
		StringBuilder items = new StringBuilder();
		boolean anythingCollected = false;
		if (itemsToCollect != null) {
			Set<String> collectedItems = new HashSet<String>();
			Iterator<String> itemsIter = itemsToCollect.iterator();
			while (itemsIter.hasNext()) {
				String item = itemsIter.next();
				if (room.hasItem(item)) {
					if (room.collectItem(item)) {
						if (anythingCollected) {
							items.append(',');
						} else {
							anythingCollected = true;
						}
						collectedItems.add(item);
						items.append(item);
					}
				}
			}
			itemsToCollect.removeAll(collectedItems);
		}
		
		if (!anythingCollected) {
			items.append("None");
		}
		
		actionLog.add(createLog(room, items.toString()));
	}

	@Override
	public void endVisit(IMazeRoom room) {
		room.setState(RoomState.visited);
		
	}
	
	@Override
	public void backToRoom(IMazeRoom room) {
		actionLog.add(createLog(room, "None"));
	}
	
	private String createLog(IMazeRoom room, String collectedItems) {
		return String.format("%-20s %-30s %-15s", room.getId(), room.getName(), collectedItems);
	}

	@Override
	public boolean isDone() {
		return itemsToCollect == null || itemsToCollect.isEmpty();
	}
	
	public List<String> getActionLog(boolean addHeader) {
		if (addHeader) {
			StringBuilder divider = new StringBuilder(65);
			for (int i = 0; i < 65; i++) {
				divider.append('-');
			}
			actionLog.add(0, divider.toString());
			actionLog.add(0, String.format("%-20s %-25s %-15s", "Room Id", "Room Name", "Object Collected"));
		}
		return actionLog;
	}
	
	private Set<String> itemsToCollect;
	private List<String> actionLog;
	
}
