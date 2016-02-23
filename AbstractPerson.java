abstract class AbstractPerson {

	protected String name;
	protected int id;
	protected int health;
	protected int max_health;
	protected Weapon weapon;
	protected Armor armor;
	protected Container bag;
	protected int damage;
	protected int experience;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return max_health;
	}

	public int getDamage() {
		return calcDamage();
	}


	public String getWeaponName() {
		return weapon.getName();
	}

	public String getArmorName() {
		return armor.getName();
	}

	public int getExperience() {
		return experience;
	}

	public void changeHealth(int val) {
		health += val;
	}

	abstract protected int calcDamage();

}