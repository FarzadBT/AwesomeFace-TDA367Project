package faces.awesome.model;

import faces.awesome.AwesomeGame;

import java.lang.*;

public class Player extends Character {

	private Facing facing;

	public Player(Position pos){
		super(pos);
	}



	//TODO lägga till facing
	public void move(float dx, float dy) {

		//Kolla först om den tile man ska till är solid eller inte (här kommer det även kollas
		//om man tile är t.ex en dörr eller liknande som man kan gå in genom)

		//TODO ska anropa metoden isSolid med dx och dy (ska inte vara =false)
		boolean isSolid = false;


		//Kollar sedan om den tile man ska till är occupied av en annan character (typ enemy)

		//TODO ska anropa metoden isOccupied med dx och dy (ska inte vara =false)
		boolean isOccupied = false;


		//Om båda är false (tilen är inte solid och inte occupied) så rör man sig dit

		if ( !isSolid && !isOccupied ) {

			//Moves the player
			setPos(pos.movePos(dx, dy));

		}

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