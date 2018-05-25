package faces.awesome.controllers;

import faces.awesome.model.*;
import faces.awesome.model.characters.Character;
import faces.awesome.model.characters.BossEnemy;
import faces.awesome.model.characters.Enemy;

import java.util.Random;

/*
 * Author: Therese Sturesson
 * Updated by:
 *
 * A controller for the enemy (so enemy does not need to have segment)
 */

public class EnemyCtrl {

    private final MapSegment segment;

    private Random randomGenerator = new Random();


    public EnemyCtrl(MapSegment segment) {
        this.segment = segment;
    }


    //Checks if the enemy can move or not
    public void tryMove (Enemy enemy) {

        Position randPosition = randomPosition(enemy);

        Position newPosition = enemy.getPos().movePos(randPosition.getX(), randPosition.getY());


        boolean solid = segment.isSolid(newPosition.getX(), newPosition.getY());

        if (enemy instanceof BossEnemy) {

            solid = solid || segment.isSolid(newPosition.getX()+1, newPosition.getY());

        }

        boolean occupied = segment.isOccupied(newPosition);

        boolean withInBorder = checkWithInBorder(newPosition, enemy);


        enemy.move(solid, occupied, withInBorder, newPosition);

    }


    //Checks if the enemy should attack or not
    public void shouldAttack(Enemy enemy, Character ch) {

        boolean shouldAttack = segment.hasTargets(enemy);

        if ( shouldAttack ) {

            enemy.attack(ch);

        }

    }


    //Gives the enemy a random position to move to
    private Position randomPosition(Enemy enemy) {

        Position randPos = new Position(0,0);

        int randomInt = randomGenerator.nextInt(4) + 1;


        if ( randomInt == 1 ) {

            randPos = new Position( 1, 0);
            enemy.setFacing(Facing.EAST);

        } else if ( randomInt == 2 ) {

            randPos = new Position( 0, 1);
            enemy.setFacing(Facing.NORTH);

        } else if (  randomInt == 3 ) {

            randPos = new Position( -1, 0);
            enemy.setFacing(Facing.WEST);

        } else if ( randomInt == 4 ) {

            randPos = new Position( 0, -1);
            enemy.setFacing(Facing.SOUTH);

        }

        return randPos;

    }


    //Checks if the enemy is within the segment border
    private boolean checkWithInBorder(Position position, Enemy enemy) {

        int xMin = (enemy.getPos().getX() / 32) * 32;
        int yMin = (enemy.getPos().getY() / 16) * 16;

        int xMax = ((enemy.getPos().getX() / 32) + 1) * 32;
        int yMax = ((enemy.getPos().getY() / 16) + 1) * 16;

        boolean withInX = position.getX() < xMax && position.getX() > xMin;
        boolean withInY = position.getY() < yMax && position.getY() > yMin;

        return withInX && withInY;

    }


    // Checks if the enemy have died
    public void checkDeath(Enemy enemy) {

        if ( enemy.getHealth() == 0 ) {

            segment.removeEnemyFromLists(enemy);
            enemy.death();
        }

    }
}
