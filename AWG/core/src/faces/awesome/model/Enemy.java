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

    /*
    public ArrayList<Position> getTargetTiles(Character Char){

        ArrayList<Position> targets = new ArrayList<>();
        Position origin = Char.getPos();

        if(Char.getFacing().equals(Facing.NORTH)){
            targets.add(new Position(origin.getX()-1,origin.getY()+1));
            targets.add(new Position(origin.getX(), origin.getY()+1));
            targets.add(new Position(origin.getX()+1,origin.getY()+1));
        }
        else if(Char.getFacing().equals(Facing.EAST)){
            targets.add(new Position(origin.getX()+1, origin.getY()+1));
            targets.add()
        }
    }
    */


}
