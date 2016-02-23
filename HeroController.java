import java.lang.*;
import java.util.*;
import java.io.*;

enum SelfActionType { START, FIGHT, RUN, CHANGE, CHANGE_ARMOR, CHANGE_WEAPON, CONTAINER, CONTAINER_WEAPON,  
CONTAINER_ARMOR, CONTAINER_HEAL, CONTAINER_DAMAGE, CONTAINER_STORED, HERO_INFO } 
class HeroController {

	private Hero hero;
	private Scanner sc = new Scanner(System.in);
	private SelfActionType state = SelfActionType.START;
	private ArrayList<Enemy> enemies;

	public HeroController() {
		hero = new Hero();
	}
	
	public boolean isAlive() {
		return hero.getHealth() > 0;
	}

	public Action makeMove() {
		return selectAction();
	}

	public void applyMove(Action action) {
		System.out.println("Вам нанесли " + action.getValue() + " очков урона.");
		hero.changeHealth(-action.getValue());
	}

	public void setEnemiesList(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	// BEGIN PRIVATE
	
	private Action selectAction() {
		int choise = 0;
		switch(state) {
			case START:
				printGlobalActionList();
				choise = getChoose();
				switch(choise){
					case 1:
						state = SelfActionType.FIGHT;
						break;
					case 2:
						state = SelfActionType.CHANGE;
						break;
					case 3:
						state = SelfActionType.HERO_INFO;
						break;
					case 4:
						state = SelfActionType.RUN;
						break;
				}
				return new Action(ActionType.SELF);
			case HERO_INFO:
				printHeroInfo();
				state = SelfActionType.START;
				return new Action(ActionType.SELF);
			case CHANGE:
				printSelfActionList();
				choise = getChoose();
				switch(choise) {
					case 1:
						state = SelfActionType.CHANGE_ARMOR;
						break;
					case 2:
						state = SelfActionType.CHANGE_WEAPON;
						break;
					case 3:
						state = SelfActionType.CONTAINER;
						break;
					case 4:
						state = SelfActionType.START;
						break;
				}
				return new Action(ActionType.SELF);
			case CHANGE_ARMOR:
				getArmorList();
				choise = getChoose();
				hero.changeArmor(choise-1);
				state = SelfActionType.CHANGE;
				return new Action(ActionType.SELF);
			case CHANGE_WEAPON:
				getWeaponList();
				choise = getChoose();
				hero.changeWeapon(choise-1);
				state = SelfActionType.CHANGE;
				return new Action(ActionType.SELF);
			case CONTAINER:
				printBagUnitsList();
				choise = getChoose();
				switch(choise) {
					case 1:
						state = SelfActionType.CONTAINER_WEAPON;
						break;
					case 2:
						state = SelfActionType.CONTAINER_ARMOR;
						break;
					case 3:
						state = SelfActionType.CONTAINER_HEAL;
						break;
					case 4:
						state = SelfActionType.CONTAINER_DAMAGE;
						break;
					case 5:
						state = SelfActionType.CONTAINER_STORED;
						break;
					case 6:
						state = SelfActionType.CHANGE;
						break;
				}
				return new Action(ActionType.SELF);
			case CONTAINER_WEAPON:
				getWeaponList();
				state = SelfActionType.CONTAINER;
				return new Action(ActionType.SELF);
			case CONTAINER_ARMOR:
				getArmorList();
				state = SelfActionType.CONTAINER;
				return new Action(ActionType.SELF);
			case CONTAINER_HEAL:
				getHealItemsList();
				state = SelfActionType.CONTAINER;
				return new Action(ActionType.SELF);
			case CONTAINER_DAMAGE:
				getDamageItemsList();
				state = SelfActionType.CONTAINER;
				return new Action(ActionType.SELF);
			case CONTAINER_STORED:
				getStoredItemsList();
				state = SelfActionType.CONTAINER;
				return new Action(ActionType.SELF);
			case RUN:
				state = SelfActionType.START;
				return new Action(ActionType.GO);
			case FIGHT:
				if (enemies.size() > 0) {
					state = SelfActionType.FIGHT;
					return pushEnemy();
				}
				state = SelfActionType.START;
				return new Action(ActionType.SELF);
		}
		return null;
	}

	private Action pushEnemy() {
		printEnemiesList();
		int choise = getChoose();
		return new Action(ActionType.PUSH, enemies.get(choise-1).getId(), hero.getDamage());
	}

	private int getChoose() {
		int res = sc.nextInt();
		return res;
	}

	private void printEnemiesList() {
		for (Enemy e : this.enemies) {
			System.out.println("Имя: " + e.getName());
			System.out.println("Здоровье: " + e.getHealth() + "/" + e.getMaxHealth());
			System.out.println("Урон: " + e.getDamage());
			System.out.println();
		}
	}

	private void printGlobalActionList() {
		System.out.println("Действия: ");
		System.out.println("1) Вступить в бой");
		System.out.println("2) Изменить состояние");
		System.out.println("3) Данные об игроке");
		System.out.println("4) Убежать");
	}

	private void printSelfActionList() {
		System.out.println("Действия: ");
		System.out.println("1) Сменить броню");
		System.out.println("2) Поменять оружие");
		System.out.println("3) Посмотреть в рюкзак");
		System.out.println("4) Выйти назад");
	}

	private void printBagUnitsList() {
		System.out.println("Отделы: ");
		System.out.println("1) Оружие");
		System.out.println("2) Броня");
		System.out.println("3) Зелья и травы");
		System.out.println("4) Ништяки");
		System.out.println("5) Предметы");
		System.out.println("6) Выйти назад");
	}

	private void getArmorList() {
		int i = 0;
		ArrayList<Armor> armors = hero.getArmorItems();
		if (armors == null) {
			System.out.println("В вашем мешке нет брони.");
			return;
		}
		for (Armor a : armors) {
			System.out.println((++i) + ") " + a.getName());
		}
	}

	private void getWeaponList() {
		int i = 0;
		ArrayList<Weapon> weapon = hero.getWeaponItems();
		if (weapon == null) {
			System.out.println("В вашем мешке нет оружия.");
			return;
		}
		for (Weapon a : weapon) {
			System.out.println((++i) + ") " + a.getName());
		}
	}

	private void getHealItemsList() {
		int i = 0;
		ArrayList<InfluentableItem> heals = hero.getHealItems();
		if (heals == null) {
			System.out.println("В вашем мешке нет трав.");
			return;
		}
		for (InfluentableItem a : heals) {
			System.out.println((++i) + ") " + a.getName());
		}
	}

	private void getDamageItemsList() {
		int i = 0;
		ArrayList<InfluentableItem> damages = hero.getDamageItems();
		if (damages == null) {
			System.out.println("В вашем мешке нет ништяков.");
			return;
		}
		for (InfluentableItem a : damages) {
			System.out.println((++i) + ") " + a.getName());
		}
	}

	private void getStoredItemsList() {
		int i = 0;
		ArrayList<StoredItem> stores = hero.getStoredItems();
		if (stores == null) {
			System.out.println("В вашем мешке нет предметов.");
			return;
		}
		for (StoredItem a : stores) {
			System.out.println((++i) + ") " + a.getName());
		}
	}

	private void printHeroInfo() {
		System.out.println("Имя: " + hero.getName());
		System.out.println("Здоровье: " + hero.getHealth() + "/" + hero.getMaxHealth());
		System.out.println("Урон: " + hero.getDamage());
		System.out.println("Оружие: " + hero.getWeaponName());
		System.out.println("Броня: " + hero.getArmorName());
		System.out.println();
	}

	// END PRIVATE

}