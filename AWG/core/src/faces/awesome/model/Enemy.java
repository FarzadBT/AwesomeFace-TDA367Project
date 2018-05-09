package faces.awesome.model;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.ArrayList;
import java.util.Random;
import faces.awesome.model.WorldMap;

public class Enemy extends Character {

    PlayerCharacter player;
    private final WorldMap world;


    public Enemy(Position pos, PlayerCharacter player, WorldMap world){
        super(pos, 5, 15);
        this.player = player;
        this.world = world;
    }


    public void move() {


        //Först hämta en random position, som ger en dx och dy
        Position randPosition = randomPosition();


        //Lägg in den positionen som en newPosition
        Position newPosition = getPos().movePos(randPosition.getX(), randPosition.getY());


        //Kolla om den nya positionen är solid eller inte
        boolean solid = world.isSolid(newPosition.getX(), newPosition.getY());


        //Kolla om den nya positionen är occupide eller inte



        //Kolla om positionen är inom ramen av var fienden får gå runt?



        //Om det inte finns något som protesterar så flytta på sig
        if (!solid) {

            world.tryMovePosition(getPos(), newPosition);

        }


    }



    public Position randomPosition () {

        Position randPos = new Position(0,0);

        Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(4) + 1;


        if ( randomInt == 1 ) {

            randPos = new Position( 1, 0);

        } else if ( randomInt == 2 ) {

            randPos = new Position( 0, 1);

        } else if (  randomInt == 3 ) {

            randPos = new Position( -1, 0);

        } else if ( randomInt == 4 ) {

            randPos = new Position( 0, -1);

        }

        return randPos;

    }



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

    public void attack(){
        switch(facing){
            case NORTH:
                if(Math.abs(player.getPos().getX() - this.getPos().getX()) == 1 && player.getPos().getY() - this.getPos().getY() == 1){
                    player.decreaseHealth(baseDamage);
                }
            case EAST:
                if(player.getPos().getX() - this.getPos().getX() == 1 && Math.abs(player.getPos().getY() - this.getPos().getY()) == 1){
                    player.decreaseHealth(baseDamage);
                }
            case SOUTH:
                if(player.getPos().getY() - this.getPos().getY() == -1 && Math.abs(player.getPos().getX() - this.getPos().getX()) == 1){
                    player.decreaseHealth(baseDamage);
                }
            case WEST:
                if(player.getPos().getX() - this.getPos().getX() == -1 && Math.abs(player.getPos().getY() - this.getPos().getY()) == 1){
                    player.decreaseHealth(baseDamage);
                }
        }
    }


}
