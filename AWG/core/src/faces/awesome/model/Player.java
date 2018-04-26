package faces.awesome.model;

import faces.awesome.AwesomeGame;

import java.lang.*;

public class Player extends Character {

	private Facing facing;

	public Player(Position pos){
		super(pos);
	}



	@Override
	protected void attack(Character other) {

	}


	public void move(float dx, float dy) {

		//Moves the player
		setPos(pos.movePos(dx, dy));

	}
/*
	public boolean isWithinCamView() {
		float x = getPos().getX();
		float y = getPos().getY();

		if (x >  && ) {

		}
	}
*/


}