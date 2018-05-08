package faces.awesome.model;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.ArrayList;

public class Enemy extends Character {

    PlayerCharacter player;

    public Enemy(Position pos, PlayerCharacter player){
        super(pos, 5, 15, 15);
        this.player = player;
    }

    protected void move() {



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
