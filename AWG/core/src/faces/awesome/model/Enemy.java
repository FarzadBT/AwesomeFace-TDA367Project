package faces.awesome.model;

import com.squareup.otto.Bus;
import faces.awesome.events.EnemyDiedEvent;

import java.util.Random;

/*
 * Author: Therese Sturesson
 * Updated by: Philip Nilsson
 *
 * TODO skriva vad klassen gör
 */

public class Enemy extends Character {

    protected Random randomGenerator = new Random();
    protected Bus bus;

    //protected MapSegment segment;


    public Enemy(Position pos, Bus bus, String name){
        super(pos, 2, 15, name);
        this.bus = bus;
        health = maxHealth;
    }


    //Moves the enemy
    public void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

        //Rör sig 1 gång på 30 gånger, TODO ska egentligen inte vara här, ta bort senare
        int randInt = randomGenerator.nextInt(30);

        if ( randInt > 1 ) {
            return;
        }

        if (!solid && !occupied && withInBorder) {

            setPos(newPosition);

        }

    }


    //The enemy attacks if this method is called
    public void attack(Character ch) {

        ch.decreaseHealth(baseDamage);

    }


    //If the enemy dies it will be remove from the list
    @Override
    public void death() {

        bus.post(new EnemyDiedEvent(this));

        //segment.removeEnemy(this);
        //segment.getCharacterInWorld().remove(this);

    }


}
