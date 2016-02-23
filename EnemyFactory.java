import java.util.*;

class EnemyFactory {

	private static int enemy_count;
	private static Random rnd = new Random();

	public static Enemy getEnemy() {

		String name = "Vova";
		int health = Math.abs(rnd.nextInt() % 50 + 10);
		Weapon weapon = new Weapon("Клеймор", Math.abs(rnd.nextInt() % 20 + 1), Math.abs(rnd.nextInt() % 10 + 1));
		Armor armor = new Armor("Шлем", Math.abs(rnd.nextInt() % 20 + 1), Math.abs(rnd.nextInt() % 10 + 1));
		int damage = Math.abs(rnd.nextInt() % 20 + 1);
		int expirience = Math.abs(rnd.nextInt() % 10 + 1);
		Container container = ContainerFactory.getContainer(expirience);

		return new Enemy(name, health, weapon, armor, container, damage, expirience);
	}
	
}