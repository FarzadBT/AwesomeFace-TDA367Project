package faces.awesome.model.characters;

import faces.awesome.model.GameObject;
import faces.awesome.model.Position;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson, Farzad Besharati
 *
 * An abstract class for all characters
 */

public abstract class Character extends GameObject {

	//Based damage dealt with no weapon equipped
	protected int baseDamage;

	//Character current health and max health
	protected int health;
	protected int maxHealth;


	public Character(Position pos, int baseDamage, int maxHealth, String name){
		super(pos, name);
		this.baseDamage = baseDamage;
		this.maxHealth = maxHealth;
		health = maxHealth;
	}

	//Gets the health of the character
	public int getHealth() {
		return health;
	}


	//Increases the health on the character
	public void increaseHealth(int n) {
		if (health + n < maxHealth) {
			health += n;
		} else {
			health = maxHealth;
		}
	}

	//Decreases the health on the character
	public void decreaseHealth(int n) {
		if (health -n > 0) {
			health -= n;
		} else {
			health = 0;
			death();
		}
	}

	//Gets the max health of the character
	public int getMaxHealth() {
		return maxHealth;
	}


	//Abstract method for when a characters die
	public abstract void death ();

}



