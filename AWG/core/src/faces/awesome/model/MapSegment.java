package faces.awesome.model;

//import javafx.geometry.Pos;

import faces.awesome.AwesomeGame;

import java.util.ArrayList;
import java.util.List;

/* This class represents a finite "chunk" of the world map. It holds a list of positions in the current segment,
 * a PlayerCharacter, and a list of enemiesInWorld. Whenever an enemiesInWorld attacks, target checking is handled by this class.
 * The list of positions is updated in accordance with changes in WorldPositions. */


public class MapSegment {

    private static WorldMap world;
    public PlayerCharacter player;

    public BossEnemy boss;

    private static List<Character> characterInWorld;

    private static List<Enemy> enemiesInWorld;


    public MapSegment(WorldMap World, List<Enemy> enemiesInWorld, PlayerCharacter player, BossEnemy boss){

        this.player = player;
        enemiesInWorld = enemiesInWorld;
        world = World;
        this.boss = boss;

        characterInWorld = new ArrayList<>();
        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(player);

    }


    public void setEnemiesInWorld (List<Enemy> enemiesInWorld) {

        enemiesInWorld = enemiesInWorld;

        characterInWorld.clear();

        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(player);

    }

    /* Compares the position of each enemiesInWorld in the world with the positions in the current segment. If they match,
    * the enemiesInWorld is added to EnemiesInSegment */
    public static List<Enemy> getEnemiesInSegment(){

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

    /**
     * By using getEnemiesInSegment() we check which of the enemies in the current segment that are inside of a hitbox
     * that goes from top-left to bottom-right
     * @param x1 x-coord of top left corner
     * @param y1 y-coord of top left corner
     * @param x2 x-coord of bottom right corner
     * @param y2 y-coord of bottom right corner
     * @return A list of the enemies that are inside the hitbox
     */
    public static List<Enemy> getPlayerTargets(int x1, int y1, int x2, int y2) {
        List <Enemy> enemies = getEnemiesInSegment();
        List <Enemy> targets = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if(enemy.getPos().getX() >= x1 && enemy.getPos().getX() <= x2 && enemy.getPos().getY() <= y1 && enemy.getPos().getY() >= y2)
                targets.add(enemy);
        }

        return targets;
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
        return Tiles.isSolid(world.getCurrent(), x, y);
    }

    public List<Character> getCharacterInWorld() {
        return characterInWorld;
    }

    public List<Enemy> getEnemiesInWorld() {
        return enemiesInWorld;
    }

}
