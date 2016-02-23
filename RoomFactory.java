import java.util.*;

class RoomFactory {

	private static int room_count = 0;
	private static Random rnd = new Random();

	public static Room getRoom() {
		ArrayList<Enemy> enemies = new ArrayList<>();
		int enemies_count = Math.abs(rnd.nextInt()) % 4 + 1;
		int avg = 0;
		for (int i = 0 ; i < enemies_count; i++) {
			Enemy enemy = EnemyFactory.getEnemy();
			avg += enemy.getExperience();
			enemies.add(enemy);
		}
		Container container = ContainerFactory.getContainer(avg/enemies_count);
		ArrayList<Room> doors = new ArrayList<Room>(Math.abs(rnd.nextInt()) % 4 + 1);

		return new Room(doors, enemies, container, room_count++, "Комната");
	}
}