package faces.awesome.model;

import faces.awesome.AwesomeGame;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * This class represents a boss enemy. Simply a stronger form of enemy. If this dies, the player "wins" the game.
 */

public class BossEnemy extends Enemy {

    public BossEnemy(Position pos, MapSegment segment) {
        super(pos, segment);
        baseDamage = 5;
        health = maxHealth*2;
        name = "bossEnemy";
    }


    //Overrides enemys move because this enemy are bigger and therefor you have the check two tiles instead of one
    @Override
    protected void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

        //Rör sig 1 gång på 30 gånger, TODO ska egentligen inte vara här, ta bort senare
        int randInt = randomGenerator.nextInt(50);

        if ( randInt > 1 ) {
            return;
        }

        solid = solid || segment.isSolid(newPosition.getX()+1, newPosition.getY());

        if (!solid && !occupied && withInBorder) {

            setPos(newPosition);

        }

    }


    // If the boss dies, the player wins
    @Override
    public void death(){

        if(this.getHealth() == 0){

        }

    }

}
