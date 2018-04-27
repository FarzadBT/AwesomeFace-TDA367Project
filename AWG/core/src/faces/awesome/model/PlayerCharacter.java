package faces.awesome.model;

import faces.awesome.AwesomeGame;

import java.lang.*;

public class PlayerCharacter extends Character {

	private Facing facing;

	public PlayerCharacter(Position pos){
		super(pos);
	}


	public void move(float dx, float dy) {
		//Moves the playerCharacter
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