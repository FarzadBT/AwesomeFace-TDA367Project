package faces.awesome.model;

import java.lang.*;

public class Player extends Character {

	private Facing facing;

	public Player(Position pos, int baseDamage){
		super(pos);
	}



	@Override
	protected void attack(Character other) {

	}


	public void move(float dx, float dy) {

		//Moves the player
		setPos(pos.movePos(dx, dy));

	}



}