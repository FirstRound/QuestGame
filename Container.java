import java.lang.*;
import java.util.*;

class Container extends AbstractItem {

	private int capacity;
	
	private ArrayList<Weapon> weapons;
	private ArrayList<Armor> armors;
	private ArrayList<InfluentableItem> heal_items;
	private ArrayList<InfluentableItem> damage_items;
	private ArrayList<StoredItem> stored_items;

	public Container(String name, int price, int capacity) {
		super(name, price);
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public ArrayList<Armor> getArmors() {
		return armors;
	}

	public ArrayList<InfluentableItem> getHealItems() {
		return heal_items;
	}

	public ArrayList<InfluentableItem> getDamageItems() {
		return damage_items;
	}

	public ArrayList<StoredItem> getStoredItems() {
		return stored_items;
	}

}