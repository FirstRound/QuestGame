class Enemy extends AbstractPerson {
	public Enemy(String name, int max_health, Weapon weapon, Armor armor, Container container, int damage,
												int experience) {
		this.name = name;
		this.health = this.max_health = max_health;
		this.weapon = weapon;
		this.armor = armor;
		this.bag = container;
		this.damage = damage;
		this.experience = experience;
	}

	// BEGIN PRIVATE
	
	protected int calcDamage() { //TODO
		return damage;
	}

	// END PRIVATE

}