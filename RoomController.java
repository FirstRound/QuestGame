import java.lang.*;
import java.util.*;

class RoomController {

	private Room room;
	private Map map;
	private Random rnd;

	public RoomController() {
		rnd = new Random();
		map = new Map();
		room = map.getNextRoom();
	}

	public void changeRoom() {
		room = map.getNextRoom();
	}

	public Action pushHero() {

		ArrayList<Enemy> enemies = room.getEnemies();
		if (enemies.size() > 0) {
			Enemy enemy = enemies.get(Math.abs(rnd.nextInt()) % (enemies.size()));
			return new Action(ActionType.PUSH, enemy.getId(), enemy.getDamage());
		}
		return new Action(ActionType.PUSH, 0, 0);

	}

	public void applyPush(Action action) {
		findEnemieById(action.getId()).changeHealth(-action.getValue());
	}

	public ArrayList<Enemy> getEnemiesList() {
		return room.getEnemies();
	}

	public void printRoomInfo() {
		System.out.println("Название комнаты: " + room.getRoomName());
		System.out.println("Врагов: " + room.getEnemiesCount());
		ArrayList<Enemy> enemies = room.getEnemies();
		for (Enemy e : enemies) {
			System.out.println("Имя: " + e.getName());
			System.out.println("Здоровье: " + e.getHealth() + "/" + e.getMaxHealth());
			System.out.println("Урон: " + e.getDamage());
			System.out.println("Оружие: " + e.getWeaponName());
			System.out.println("Броня: " + e.getArmorName());
			System.out.println();
		}
	}

	// BEGIN PRIVATE
	
	private Enemy findEnemieById(int id) {
		ArrayList<Enemy> enemies = room.getEnemies();
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getId() == id) {
				return enemies.get(i);
			}
		}
		return null;
	}

	// END PRIVATE

	
	
}