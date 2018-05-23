package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson, Farzad Besharati
 *
 * TODO skriva vad klassen gör
 */

import faces.awesome.view.GameObjectView;

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

	//Sets the health on the character		TODO används inte, spara?
	public void setHealth(int health) {
		this.health = health;
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

	//Gets the max health of the character	TODO används inte, spara?
	public int getMaxHealth() {
		return maxHealth;
	}

	//Sets the max health of the character	TODO används inte, spara?
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	//Abstract method for when a characters die
	public abstract void death ();


}



