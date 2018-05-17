package faces.awesome.model;

public abstract class Character extends GameObject{
	//Based damage dealt with no weapon equipped
	protected int baseDamage;

	// character current health and max health
	protected int health;
	protected int maxHealth;


	public Character(Position pos, int baseDamage, int maxHealth, String name){
		super(pos, name);
		this.baseDamage = baseDamage;
		this.maxHealth = maxHealth;
		health = maxHealth;
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void increaseHealth(int n) {
		if (health + n < maxHealth)
			health += n;
		else
			health = maxHealth;
	}

	public void decreaseHealth(int n) {
		if (health -n > 0) {
			health -= n;
		} else {
			health = 0;
			death();
		}
	}

	public abstract void death ();


	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

}




