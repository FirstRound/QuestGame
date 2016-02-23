class Weapon  extends AbstractItem {

	private int damage;

	public Weapon(String name, int price, int damage) {
		super(name, price);
		this.damage = damage;
		action_name = "Взять";
	}

	public int getDamage() {
		return damage;
	}
	
}