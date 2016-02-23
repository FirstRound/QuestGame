import java.util.*;

class Room {

	private ArrayList<Room> doors;
	private ArrayList<Enemy> enemies;
	private Container container;
	private int room_id;
	private String name;

	public Room(ArrayList<Room> doors, ArrayList<Enemy> enemies, Container container, int room_id, String name) {
		this.doors = doors;
		this.enemies = enemies;
		this.room_id = room_id;
		this.name = name;
	}

	public ArrayList<Enemy> getEnemies() {
		checkForDead();
		return enemies;
	}

	public int getEnemiesCount() {
		checkForDead();
		return enemies.size();
	}

	public int getDoorsCount() {
		return doors.size();
	}

	public String getRoomName() {
		return name;
	}

	// BEGIN PRIVATE
	
	private void checkForDead() {
		for(int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() <= 0) {
				enemies.remove(enemies.get(i));
				break;
			}
		}
	}

	// END PRIVATE
	
}