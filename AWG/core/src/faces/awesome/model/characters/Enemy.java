package faces.awesome.model.characters;

import com.squareup.otto.Bus;
import faces.awesome.model.DropTable;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.utils.AwesomeTimer;

/*
 * Author: Therese Sturesson
 * Updated by: Philip Nilsson
 *
 * One type of enemy in the game
 */

public class Enemy extends Character {

    protected Bus bus;
    private AwesomeTimer timerMove, timerAttack;
    private DropTable dropTable;

    public Enemy(Position pos, Bus bus, String name){
        super(pos, 2, 20, name);
        this.bus = bus;
        health = maxHealth;
        timerMove = new AwesomeTimer();
        timerAttack = new AwesomeTimer();
        dropTable = new DropTable();
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

    // The check for if this type of enemy dies happen in EnemyCtrl
    @Override
    public void death() {

    }

    /**
     * @param segment
     */
    public void dropPickup(MapSegment segment) {
        dropTable.roll(pos, segment);
    }

}
