package faces.awesome.model;

import faces.awesome.AwesomeGame;

import java.util.Random;

public class Enemy extends Character {

    private Random randomGenerator = new Random();

    private MapSegment segment;
    private AwesomeGame game;


    public Enemy(Position pos, AwesomeGame game){
        super(pos, 2, 15);
        health = maxHealth;
        this.game = game;
    }


    public void move() {

        //Rör sig 1 gång på 30 gånger, TODO ska egentligen inte vara här, ta bort senare
        int randInt = randomGenerator.nextInt(30);

        if ( randInt > 1 ) {
            return;
        }


        Position randPosition = randomPosition();

        Position newPosition = getPos().movePos(randPosition.getX(), randPosition.getY());

        boolean solid = game.segment.isSolid(newPosition.getX(), newPosition.getY());

        boolean occupied = game.segment.isOccupied(newPosition);

        boolean withInBorder = checkWithInBorder(newPosition);

        if (!solid && !occupied && withInBorder) {

            setPos(newPosition);

        }

    }

    public void attack(Character ch) {

        boolean shouldAttack = game.segment.getTargets(this);

        if ( !shouldAttack ) {

            return;

        }

        ch.decreaseHealth(baseDamage);
    }


    private boolean checkWithInBorder(Position position ) {

        int xMin = (this.getPos().getX() / 32) * 32;
        int yMin = (this.getPos().getY() / 16) * 16;

        int xMax = ((this.getPos().getX() / 32) + 1) * 32;
        int yMax = ((this.getPos().getY() / 16) + 1) * 16;

        boolean withInX = position.getX() < xMax && position.getX() > xMin;
        boolean withInY = position.getY() < yMax && position.getY() > yMin;

        return withInX && withInY;

    }


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


    @Override
    public void death() {

        game.enemiesInWorld.remove(this);
        game.segment.getCharacterInWorld().remove(this);

    }

}
