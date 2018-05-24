package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * This class represents a boss enemy. Simply a stronger form of enemy. If this dies, the player "wins" the game.
 */

import com.squareup.otto.Bus;
import faces.awesome.events.BossEnemyDiedEvent;

public class BossEnemy extends Enemy {

    public BossEnemy(Position pos, Bus bus, String name) {
        super(pos, bus, name);
        baseDamage = 5;
        health = maxHealth*2;
        name = "bossEnemy";
    }


    //Overrides enemys move because this enemy are bigger and therefor you have the check two tiles instead of one
    @Override
    public void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

        //Rör sig 1 gång på 30 gånger, TODO ska egentligen inte vara här, ta bort senare
        int randInt = randomGenerator.nextInt(50);

        if ( randInt > 1 ) {
            return;
        }


        if (!solid && !occupied && withInBorder) {

            setPos(newPosition);

        }

    }


    // If the boss dies, the player wins
    @Override
    public void death(){

        bus.post(new BossEnemyDiedEvent());

    }

}
