package faces.awesome.model;

import java.util.ArrayList;

public class Enemy extends Character {

    public Enemy(Position pos){
        super(pos, 5, 15, 15);
    }


    public void attack() {

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


}
