import java.util.*;

class Map {

	private ArrayList<Room> map_history = new ArrayList<>();

	
	public Room getNextRoom() {
		Room room = RoomFactory.getRoom();
		map_history.add(room);
		return room;
	}

	public Room getPrevRoom() {
		return map_history.get(map_history.size()-1);
	}

}