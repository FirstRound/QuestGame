import java.util.*;

class Hero extends AbstractPerson {
	
	public Hero() {
		health = 100;
		max_health = 100;
		damage = 50;
		experience = 10;
		name = "Strelkov";
		weapon = new Weapon("Сабля", 100, 40);
		armor = new Armor("Латы", 120, 100);
		bag = ContainerFactory.getContainer(experience);
	}

	protected int calcDamage() {
		return damage;
	}

	public ArrayList<Armor> getArmorItems() {
		return bag.getArmors();
	}

	public void changeArmor(int i) {
		armor = (bag.getArmors()).get(i);
	}

	public ArrayList<Weapon> getWeaponItems() {
		return bag.getWeapons();
	}

	public ArrayList<InfluentableItem> getDamageItems() {
		return bag.getDamageItems();
	}

	public ArrayList<InfluentableItem> getHealItems() {
		return bag.getHealItems();
	}

	public ArrayList<StoredItem> getStoredItems() {
		return bag.getStoredItems();
	}

	public void changeWeapon(int i) {
		weapon = (bag.getWeapons()).get(i);
	}

}