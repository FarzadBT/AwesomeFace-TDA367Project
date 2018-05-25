package faces.awesome.model;

import faces.awesome.GDXWrapper;
import faces.awesome.model.characters.Character;
import faces.awesome.model.characters.Enemy;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson, Farzad Besharati
 *
 * This class represents a finite "chunk" of the world map. Holds a list of Characters and a list
 * of enemiesInWorld. Whenever an enemiesInWorld attacks, target checking is handled by this class.
 * It also hols the map position, checks the segments border and checks if a tile is occupied.
 */

public class MapSegment {

    //Varibles
    private Position mapPosition;
    private GDXWrapper gdxWrapper;

    // Two lists for enemies and characters
    private List<Character> characterInWorld = new ArrayList<>();
    private List<Enemy> enemiesInWorld = new ArrayList<>();
    private List<GameObject> objectsInWorld = new ArrayList<>();


    public MapSegment(GDXWrapper gdxWrapper){

        this.gdxWrapper = gdxWrapper;

        this.mapPosition = new Position(0, 0);

    }


    // Sets the list of enemies in the world
    public void setEnemiesInWorld (List<Enemy> enemiesInWorld) {

        this.enemiesInWorld = enemiesInWorld;

        characterInWorld.clear();
        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(gdxWrapper.player);

    }


    // Gets the list of enemies
    public List<Enemy> getEnemiesInSegment() {

        int minX = mapPosition.getX() * 32;
        int maxX = (mapPosition.getX() + 1) * 32;
        int minY = mapPosition.getY() * 16;
        int maxY = (mapPosition.getY() + 1) * 16;

        List<Enemy> enemiesInSegment = new ArrayList<>();

        for (Enemy e : enemiesInWorld) {

            int enemyX = e.getPos().getX();
            int enemyY = e.getPos().getY();

            if (enemyX > minX && enemyX < maxX && enemyY > minY && enemyY < maxY) {
                enemiesInSegment.add(e);
            }
        }
        return enemiesInSegment;
    }

    /**
     * Get all the GameObjecst in the current MapSegment
     * @return a list of GameObjects
     */
    public List<GameObject> getObjectsInSegment() {
        int minX = mapPosition.getX() * 32;
        int maxX = (mapPosition.getX() + 1) * 32;
        int minY = mapPosition.getY() * 16;
        int maxY = (mapPosition.getY() + 1) * 16;

        List<GameObject> objectsInSegment = new ArrayList<>();

        for(GameObject o : objectsInWorld){

            int enemyX = o.getPos().getX();
            int enemyY = o.getPos().getY();

            if (enemyX > minX && enemyX < maxX && enemyY > minY && enemyY < maxY) {
                objectsInSegment.add(o);
            }
        }
        return objectsInSegment;
    }

    public void addToObjects(GameObject object) {
        objectsInWorld.add(object);
    }

    public void removeFromObjects(GameObject object) {
        objectsInWorld.remove(object);
    }

    /**
     * By using getEnemiesInSegment() we check which of the enemies in the current segment that are inside of a hitbox
     * that goes from top-left to bottom-right
     * @param pos the origin position of the attack
     * @param width width of the attack
     * @param range range of the attack, is not used if the facing is NONE
     * @param facing which direction the attack is made towards
     * @return A list of the enemies that are inside the hitbox
     */
    public List<Enemy> getPlayerTargets(/*int x1, int y1, int x2, int y2*/Position pos, int width, int range, Facing facing) {
        int x1, x2, y1, y2;

        if (facing == Facing.SOUTH) {
            x1 = pos.getX() - width;
            x2 = pos.getX() + width;
            y1 = pos.getY() - 1;
            y2 = pos.getY() - range;
        }
        else if(facing == Facing.NORTH) {
            x1 = pos.getX() - width;
            x2 = pos.getX() + width;
            y1 = pos.getY() + 1;
            y2 = pos.getY() + range;
        }
        else if(facing == Facing.EAST) {
            x1 = pos.getX() + 1;
            x2 = pos.getX() + range;
            y1 = pos.getY() + width;
            y2 = pos.getY() - width;
        }
        else if(facing == Facing.WEST) {
            x1 = pos.getX() - 1;
            x2 = pos.getX() - range;
            y1 = pos.getY() + 1;
            y2 = pos.getY() - width;
        }
        else { //facing == Facing.NONE or INVALIDFACING
            x1 = pos.getX() - width;
            x2 = pos.getX() + width;
            y1 = pos.getY() + width;
            y2 = pos.getY() - width;
        }

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

            if(p.equals(gdxWrapper.player.getPos())){

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


    // Gets the list with characters
    public List<Character> getCharacterInWorld() {
        return characterInWorld;
    }


    // Gets the list of enemies
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
        return gdxWrapper.isSolid(x, y);
    }


    // Delegate the set of the new map and sets the map position
    public Position setNewMap(Position position) {

        Position pos = gdxWrapper.setNewMap(position);

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
