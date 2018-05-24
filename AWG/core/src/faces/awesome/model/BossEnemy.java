package faces.awesome.model;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * This class represents a boss enemy. Simply a stronger form of enemy. If this dies, the player "wins" the game.
 */

import com.squareup.otto.Bus;
import faces.awesome.events.BossEnemyDiedEvent;
import faces.awesome.utils.AwesomeTimer;

public class BossEnemy extends Enemy {

    private AwesomeTimer timerMove, timerAttack;

    public BossEnemy(Position pos, Bus bus, String name) {
        super(pos, bus, name);
        baseDamage = 1;
        health = 2;
        name = "bossEnemy";
        timerMove = new AwesomeTimer();
        timerAttack = new AwesomeTimer();
    }


    //Overrides enemys move because this enemy are bigger and therefor you have the check two tiles instead of one
    @Override
    public void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

        if ( timerMove.secondsElapsed() >= 0.5 ) {

            if (!solid && !occupied && withInBorder) {

                setPos(newPosition);

            }

            timerMove.restart();

        }

    }


    //The enemy attacks if this method is called
    @Override
    public void attack(Character ch) {

        if ( timerAttack.secondsElapsed() >= 0.1 ) {

            ch.decreaseHealth(baseDamage);

        }

        timerAttack.restart();

    }


    // If the boss dies, the player wins
    @Override
    public void death(){

        bus.post(new BossEnemyDiedEvent());

    }

}
