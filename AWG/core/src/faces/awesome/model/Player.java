package faces.awesome.model;

import java.lang.*;

public class Player extends Character {

	private Facing facing;

	public Player(Position pos){
		super(pos);
	}


	public void move(float dx, float dy) {

		setPos(pos.movePos(dx, dy));


	}



}