package faces.awesome.model;

//import javafx.geometry.Pos;

import faces.awesome.AwesomeGame;

import java.util.ArrayList;
import java.util.List;

/* This class represents a finite "chunk" of the world map. It holds a list of positions in the current segment,
 * a PlayerCharacter, and a list of enemiesInWorld. Whenever an enemiesInWorld attacks, target checking is handled by this class.
 * The list of positions is updated in accordance with changes in WorldPositions. */


public class MapSegment {

    private WorldMap world;
    private PlayerCharacter player;
    private AwesomeGame game;

    private List<Character> characterInWorld;

    private List<Enemy> enemiesInWorld;


    public MapSegment(WorldMap World, List<Enemy> enemiesInWorld, PlayerCharacter player){

        this.player = player;
        this.enemiesInWorld = enemiesInWorld;
        this.world = World;

        this.characterInWorld = new ArrayList<>();
        this.characterInWorld.addAll(enemiesInWorld);
        this.characterInWorld.add(player);

    }


    /* Compares the position of each enemiesInWorld in the world with the positions in the current segment. If they match,
    * the enemiesInWorld is added to EnemiesInSegment */
    public List<Enemy> getEnemiesInSegment(){

        int minX = world.getMapPosition().getX() * 32;
        int maxX = (world.getMapPosition().getX() + 1) * 32;
        int minY = world.getMapPosition().getY() * 16;
        int maxY = (world.getMapPosition().getY() + 1) * 16;

        List<Enemy> enemiesInSegment = new ArrayList<>();

        for(Enemy e : enemiesInWorld){

            int enemyX = e.getPos().getX();
            int enemyY = e.getPos().getY();

            if (enemyX > minX && enemyX < maxX && enemyY > minY && enemyY < maxY) {
                enemiesInSegment.add(e);
            }
        }
        return enemiesInSegment;
    }


    //Gets possible target positions for each enemiesInWorld. Called when enemiesInWorld attack.
    public boolean getTargets(Enemy attacker){
        List<Position> targets = new ArrayList<>();
        Position origin = attacker.getPos();

        switch (attacker.getFacing()){
            case NORTH:
                for(int i = -1; i <= 1; i++){
                    targets.add(new Position(origin.getX()+i, origin.getY()+1));
                }
            case EAST:
                for(int i = -1; i <= 1; i++){
                    targets.add(new Position(origin.getX()+1, origin.getY()+i));
                }
            case SOUTH:
                for(int i = -1; i <= 1; i++){
                    targets.add(new Position(origin.getX()+i, origin.getY()-1));
                }
            case WEST:
                for(int i = -1; i <= 1; i++){
                    targets.add(new Position(origin.getX()-1, origin.getY()+i));
                }
        }

        return targetIsPlayer(targets);
    }


    //Checks if target position is equal to that of the player. If so; the enemiesInWorld is allowed to attack.
    public boolean targetIsPlayer(List<Position> targets){

        for(Position p : targets){

            if(p.equals(player.getPos())){

                return true;
            }
        }
        return false;
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
