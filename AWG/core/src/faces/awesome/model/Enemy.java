package faces.awesome.model;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    PlayerCharacter player;     //Ta bort player
    private final WorldMap world;       //Varför är den så? Räcker med WorldMap world;?
    Random randomGenerator = new Random();

    //Segment segment;          Ska ha?


    public Enemy(Position pos, PlayerCharacter player, WorldMap world){
        super(pos, 2, 15);
        health = maxHealth;
        this.player = player;           //Helst ta bort
        this.world = world;             //Behövs det?
    }


    public void move() {

        //Rör sig 1 gång på 30 gånger
        int randInt = randomGenerator.nextInt(30);

        if ( randInt > 1 ) {
            return;
        }


        //Först hämta en random position, som ger en dx och dy
        Position randPosition = randomPosition();


        //Lägg in den positionen som en newPosition
        Position newPosition = getPos().movePos(randPosition.getX(), randPosition.getY());


        //Kolla om den nya positionen är solid eller inte
        boolean solid = world.isSolid(newPosition.getX(), newPosition.getY());


        //TODO Kolla om den nya positionen är occupied eller inte
        //TODO Behöver en metod som kollar igenom listan med fiender, kollar deras positioner och om någon
        //TODO stämmer med positionen man skickar in så är den occupied och därmed returnerar true, annars false
        //TODO Typ: public boolean isOccupied ( Position position ) {}
        boolean occupied = false;


        //Kolla om positionen är inom ramen av var fienden får gå runt?
        boolean withInBorder = checkWithInBorder(newPosition);


        //Om det inte finns något som protesterar så flytta på sig
        if (!solid && !occupied && withInBorder) {

            setPos(newPosition);

        }

    }



    public void attack(){


        //TODO Behöver en metod som utifrån position och facing kollar om det står en player inom attackrangen
        //TODO och ifall det står en player där så returnera true, annars false
        //TODO Typ: public boolean checkAttackRange (Position position, Facing facing) {}

        /*boolean shouldAttack = Segment.checkAttackRange(getPos());


        if ( !shouldAttack ) {

            return;

        }

        segment.attackPlayer(baseDamage);


        Inne i Segmentklassen:
        public void attackPlayer (int damage) {
            player.decreaseHealth(damage);
        }



        */

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


    /*
    public ArrayList<Position> getTargets(){

        ArrayList<Position> targets = new ArrayList<>(3);
        Position origin = this.getPos();

        if(getFacing().equals(Facing.NORTH)){
            for(int i = -1; i < 1; i++){
                targets.add(new Position(origin.getX()+i, origin.getY()+1));
            }
        } else if(getFacing().equals(Facing.EAST)){
            for(int i = -1; i < 1; i++){
                targets.add(new Position(origin.getX()+1, origin.getY()+1));
            }
        } else if(getFacing().equals(Facing.SOUTH)){
            for(int i = -1; i < 1; i++){
                targets.add(new Position(origin.getX()+1, origin.getY()-1));
            }
        } else if(getFacing().equals((Facing.WEST))){
            for(int i = -1; i < 1; i++){
                targets.add(new Position(origin.getX()-1, origin.getY()+1));
            }
        }

        return targets;
    }
    */


}
