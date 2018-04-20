package faces.awesome.model;

public abstract class Character {
	// Use a vector to encapsulate player coordinates?
	// character position, perhaps this should be represented in a different way.
	protected Position pos;

	//Character facing, north, east, west, south. Could potentially be used in the attack method
	// to always swing in the direction that the character is facing.
	protected Facing facing;

	//Based damage dealt with no weapon equipped
	protected int BaseDamage;

	// character velocity
	protected float vel;

	public Character(Position pos){
		this.pos = pos;
	}

	protected void attack(Character other){};

	//Need to define how the position is stored before we implement this.
	protected abstract void move();


	//Get the position on the player
	public Position getPos () {
		return pos;
	}

	//Set the position on the player
	public void setPos (Position pos) {
		this.pos = pos;
	}

}




