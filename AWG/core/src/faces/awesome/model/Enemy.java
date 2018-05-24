package faces.awesome.model;

import com.squareup.otto.Bus;
import faces.awesome.utils.AwesomeTimer;

import java.util.Random;

/*
 * Author: Therese Sturesson
 * Updated by: Philip Nilsson
 *
 * One type of enemy in the game
 */

public class Enemy extends Character {

    protected Random randomGenerator = new Random();
    protected Bus bus;
    private AwesomeTimer timerMove, timerAttack;

    public Enemy(Position pos, Bus bus, String name){
        super(pos, 2, 15, name);
        this.bus = bus;
        health = maxHealth;
        timerMove = new AwesomeTimer();
        timerAttack = new AwesomeTimer();
    }


    //Moves the enemy
    public void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

        if ( timerMove.secondsElapsed() >= 1 ) {

            if (!solid && !occupied && withInBorder) {

                setPos(newPosition);

            }

            timerMove.restart();

        }

    }


    //The enemy attacks if this method is called
    public void attack(Character ch) {

        if ( timerAttack.secondsElapsed() >= 0.1 ) {

            ch.decreaseHealth(baseDamage);

        }

        timerAttack.restart();

    }


    @Override
    public void death() {
    }


}
