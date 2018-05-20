package faces.awesome.model;

import faces.awesome.AwesomeGame;
import java.util.Random;

/*
 * Author: Therese Sturesson
 * Updated by: Philip Nilsson
 *
 * TODO skriva vad klassen gör
 */

public class Enemy extends Character {

    protected Random randomGenerator = new Random();

    protected MapSegment segment;
    protected AwesomeGame game;


    public Enemy(Position pos, MapSegment segment){
        super(pos, 2, 15, "enemy");
        this.segment = segment;
        health = maxHealth;
    }


    //Checks if the enemy can move or not
    public void tryMove () {

        Position randPosition = randomPosition();

        Position newPosition = getPos().movePos(randPosition.getX(), randPosition.getY());


        boolean solid = segment.isSolid(newPosition.getX(), newPosition.getY());

        boolean occupied = segment.isOccupied(newPosition);

        boolean withInBorder = checkWithInBorder(newPosition);


        move(solid, occupied, withInBorder, newPosition);

    }


    //Moves the enemy
    protected void move(boolean solid, boolean occupied, boolean withInBorder, Position newPosition) {

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

        boolean shouldAttack = segment.getTargets(this);

        if ( !shouldAttack ) {

            return;

        }

        ch.decreaseHealth(baseDamage);
    }


    //Checks if the enemy is within the segment border
    private boolean checkWithInBorder(Position position ) {

        int xMin = (this.getPos().getX() / 32) * 32;
        int yMin = (this.getPos().getY() / 16) * 16;

        int xMax = ((this.getPos().getX() / 32) + 1) * 32;
        int yMax = ((this.getPos().getY() / 16) + 1) * 16;

        boolean withInX = position.getX() < xMax && position.getX() > xMin;
        boolean withInY = position.getY() < yMax && position.getY() > yMin;

        return withInX && withInY;

    }


    //Gives the enemy a random position to move to
    private Position randomPosition() {

        Position randPos = new Position(0,0);

        int randomInt = randomGenerator.nextInt(4) + 1;


        if ( randomInt == 1 ) {

            randPos = new Position( 1, 0);
            setFacing(Facing.EAST);

        } else if ( randomInt == 2 ) {

            randPos = new Position( 0, 1);
            setFacing(Facing.NORTH);

        } else if (  randomInt == 3 ) {

            randPos = new Position( -1, 0);
            setFacing(Facing.WEST);

        } else if ( randomInt == 4 ) {

            randPos = new Position( 0, -1);
            setFacing(Facing.SOUTH);

        }

        return randPos;

    }


    //If the enemy dies it will be remove from the list
    @Override
    public void death() {

        segment.removeEnemy(this);
        segment.getCharacterInWorld().remove(this);

    }

}
