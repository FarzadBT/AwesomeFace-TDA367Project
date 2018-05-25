package faces.awesome.model;

import faces.awesome.services.WorldMap;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * This class represents a finite "chunk" of the world map. Holds a list of Characters and a list
 * of enemiesInWorld. Whenever an enemiesInWorld attacks, target checking is handled by this class.
 * It also hols the map position, checks the segments border and checks if a tile is occupied.
 */

public class MapSegment {

    //Varibles
    public WorldMap world;
    private static Position mapPosition;
    public PlayerCharacter player;

    // Two lists for enemies and characters
    private List<Character> characterInWorld = new ArrayList<>();
    private static List<Enemy> enemiesInWorld = new ArrayList<>();


    public MapSegment(WorldMap world, PlayerCharacter player){

        this.player = player;
        this.world = world;

        this.mapPosition = new Position(0, 0);

    }


    // Sets the list of enemies in the world
    public void setEnemiesInWorld (List<Enemy> enemiesInWorld) {

        this.enemiesInWorld = enemiesInWorld;

        characterInWorld.clear();
        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(player);

    }


    // Gets the list of enemies
    public static List<Enemy> getEnemiesInSegment(){

        int minX = mapPosition.getX() * 32;
        int maxX = (mapPosition.getX() + 1) * 32;
        int minY = mapPosition.getY() * 16;
        int maxY = (mapPosition.getY() + 1) * 16;

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

            int x = enemy.getPos().getX();
            int y = enemy.getPos().getY();

            if(x >= x1 && x <= x2 && y <= y1 && y >= y2)

                targets.add(enemy);

        }

        return targets;
    }


    //Gets possible target positions for the enemy. Called when the enemy attacks.
    public boolean hasTargets(Enemy attacker){
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


    // Checks if the position in the list of targets matches the player and if so the enemy will attack.
    private boolean targetIsPlayer(List<Position> targets){

        for(Position p : targets){

            if(p.equals(player.getPos())){

                return true;
            }
        }
        return false;
    }


    // Checks if the position is occupied of other characters or not
    public boolean isOccupied ( Position position ) {

        for ( Character c : characterInWorld ) {

            if (c.getPos().equals(position)) {

                return true;

            }

        }

        return false;
    }


    // Gets the list with characters    TODO används inte, spara?
    public List<Character> getCharacterInWorld() {
        return characterInWorld;
    }


    // Gets the list of enemies     TODO används inte, spara?
    public List<Enemy> getEnemiesInWorld() {
        return enemiesInWorld;
    }


    //Checks the border for the segment
    public void checkSegmentBorder (Position oldPos, Position newPos) {
        int xMin = this.mapPosition.getX() * 32;
        int yMin = this.mapPosition.getY() * 16;

        int xMax = (this.mapPosition.getX() + 1) * 32;
        int yMax = (this.mapPosition.getY() + 1) * 16;


        if (oldPos.getX() >= xMin && newPos.getX() < xMin) {
            this.mapPosition = this.mapPosition.movePos(-1, 0);
        } else if (oldPos.getY() >= yMin && newPos.getY() < yMin) {
            this.mapPosition = this.mapPosition.movePos(0, -1);
        } else if (oldPos.getX() < xMax && newPos.getX() >= xMax) {
            this.mapPosition = this.mapPosition.movePos(1, 0);
        } else if (oldPos.getY() < yMax && newPos.getY() >= yMax) {
            this.mapPosition = this.mapPosition.movePos(0, 1);
        }
    }


    //Gets the map position
    public Position getMapPosition() {
        return mapPosition;
    }


    //Sets the map position
    private void setMapPosition (int x, int y) {
        mapPosition = new Position(x, y);
    }


    //Sets the players position on the map
    public void setPlayerPosOnMap(Position playerPos) {
        this.mapPosition = new Position(playerPos.getX()/32, playerPos.getY()/16);
    }


    // Delegate the check if a tile is solid or not to the Tiles class
    public boolean isSolid(int x, int y) {
        return world.isSolid(x, y);
    }


    // Delegate the set of the new map and sets the map position
    public Position setNewMap(Position position) {

        Position pos = world.setNewMap(position);

        if ( pos != null ) {
            setMapPosition(0,0);
        }

        return pos;

    }


    // Removes a specific enemy from the list
    public void removeEnemyFromLists(Enemy enemy) {

        enemiesInWorld.remove(enemy);
        characterInWorld.remove(enemy);

    }

}
