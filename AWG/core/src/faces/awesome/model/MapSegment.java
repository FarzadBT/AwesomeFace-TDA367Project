package faces.awesome.model;

//import javafx.geometry.Pos;

import faces.awesome.AwesomeGame;

import java.util.ArrayList;
import java.util.List;

/* This class represents a finite "chunk" of the world map. It holds a list of positions in the current segment,
 * a PlayerCharacter, and a list of enemies. Whenever an enemy attacks, target checking is handled by this class.
 * The list of positions is updated in accordance with changes in WorldPositions. */


public class MapSegment {
    private WorldMap world;
    private List<Enemy> EnemiesInWorld;
    private PlayerCharacter player;
    private AwesomeGame game;

    public List<Character> characterInWorld = new ArrayList<>();

    private List<Enemy> EnemiesInSegment = new ArrayList<>();
    private List<Position> PositionsInSegment = new ArrayList<>();

    public MapSegment(WorldMap World, List<Character> characterInWorld, PlayerCharacter player){
        this.player = player;
        this.characterInWorld = characterInWorld;
        this.world = World;

    }


    //Gets all positions in the current segment. Think of a segment as a 32x16 matrix.
    public void getPositionsInSegment(){
        for(int i = world.getMapPosition().getX()*32; i < world.getMapPosition().getX()*32; i++){
            for(int j = world.getMapPosition().getY()*16; j < world.getMapPosition().getX()*16; j++){
                PositionsInSegment.add(new Position(i, j));
            }
        }
    }

    /* Compares the position of each enemy in the world with the positions in the current segment. If they match,
    * the enemy is added to EnemiesInSegment */
    public void getEnemiesInSegment(){
        for(Position p : PositionsInSegment){
            for(Enemy e : EnemiesInWorld){
                if(e.getPos().equals(p)){
                    EnemiesInSegment.add(e);
                }
            }
        }
    }


    //Gets possible target positions for each enemy. Called when enemies attack.
    public boolean getTargets(Position position, Facing facing){
        List<Position> targets = new ArrayList<>();
        //Position origin = attacker.getPos();

        switch (facing){
            case NORTH:
                for(int i = -1; i < 1; i++){
                    targets.add(new Position(position.getX()+i, position.getY()+1));
                }
            case EAST:
                for(int i = -1; i < 1; i++){
                    targets.add(new Position(position.getX()+1, position.getY()+i));
                }
            case SOUTH:
                for(int i = -1; i < 1; i++){
                    targets.add(new Position(position.getX()+i, position.getY()-1));
                }
            case WEST:
                for(int i = -1; i < 1; i++){
                    targets.add(new Position(position.getX()-1, position.getY()+i));
                }
        }

        boolean checkTargets = targetIsPlayer(targets);


        return checkTargets;
    }


    //Checks if target position is equal to that of the player. If so; the enemy is allowed to attack.
    public boolean targetIsPlayer(List<Position> targets){
        for(Position p : targets){
            if(p.equals(player.getPos())){
                return true;
            }
        }
        return false;
    }

    //TODO: Add method that calls enemy.attack(), or something akin to that.

    //Updates the segment with new information. Is called whenever thee PlayerCharacter enters a new set of 32x16 positions.
    public void updateSegment(){

        getEnemiesInSegment();

        getEnemiesInSegment();
    }


    public boolean isOccupied ( Position position ) {

        for ( Character c : characterInWorld ) {

            if (c.getPos().equals(position)) {

                return true;

            }

        }

        return false;
    }



    public boolean isSolid(int x, int y) {
        return world.isSolid(x, y);
    }
}
