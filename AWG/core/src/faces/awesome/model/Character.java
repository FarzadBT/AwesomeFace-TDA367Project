package faces.awesome.model;

public abstract class Character {
	// Use a vector to encapsulate player coordinates?
	// character position, perhaps this should be represented in a different way.
	protected Position pos;

	//Character facing, north, east, west, south. Could potentially be used in the attack method
	// to always swing in the direction that the character is facing.
	protected Facing facing;

	//Based damage dealt with no weapon equipped
	protected int baseDamage;

	// character velocity
	protected float vel;

	// character current health and max health
	protected int health, maxHealth;



	public Character(Position pos){
		this.pos = pos;
	}

	protected abstract void attack(Character other);

	//Need to define how the position is stored before we implement this.
	//protected abstract void move();

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	//Get the position on the player
	public Position getPos () {
		return pos;
	}

	//Set the position on the player
	public void setPos (Position pos) {
		this.pos = pos;
	}

}




