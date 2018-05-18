package faces.awesome.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Author: Philip Nilsson
 * Updated by: Therese Sturesson
 *
 * TODO skriva vad klassen gör
 */

/* This class represents a finite "chunk" of the world map. It holds a list of positions in the
 * current segment, a PlayerCharacter, and a list of enemiesInWorld. Whenever an enemiesInWorld
 * attacks, target checking is handled by this class. The list of positions is updated in
 * accordance with changes in WorldPositions.
 */


public class MapSegment {

    private WorldMap world;
    public PlayerCharacter player;

    public BossEnemy boss;

    private List<Character> characterInWorld;

    private List<Enemy> enemiesInWorld;


    private Position mapPosition;


    public MapSegment(WorldMap World, List<Enemy> enemiesInWorld, PlayerCharacter player, BossEnemy boss){

        this.player = player;
        this.enemiesInWorld = enemiesInWorld;
        this.world = World;
        this.boss = boss;

        this.mapPosition = new Position(0, 0);

        characterInWorld = new ArrayList<>();
        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(player);

    }


    public void setEnemiesInWorld (List<Enemy> enemiesInWorld) {

        this.enemiesInWorld = enemiesInWorld;

        characterInWorld.clear();

        characterInWorld.addAll(enemiesInWorld);
        characterInWorld.add(player);

    }

    /* Compares the position of each enemiesInWorld in the world with the positions in the current segment. If they match,
    * the enemiesInWorld is added to EnemiesInSegment */
    public List<Enemy> getEnemiesInSegment(){

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
    public List<Enemy> getPlayerTargets(int x1, int y1, int x2, int y2) {
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
        return Tiles.isSolid(world.getCurrentMap(), x, y);
    }

    public List<Character> getCharacterInWorld() {
        return characterInWorld;
    }

    public List<Enemy> getEnemiesInWorld() {
        return enemiesInWorld;
    }


    //Checks the border for the segment         TODO flytta till Segment?
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
    public void setMapPosition (int x, int y) {
        mapPosition.setX(x);
        mapPosition.setY(y);
    }

    //Sets the players position on the map
    public void setPlayerPosOnMap (Position playerPos) {
        this.mapPosition = new Position(playerPos.getX()/32, playerPos.getY()/16);
    }


    public Position setNewMap(int x, int y) {

        Position pos = world.setNewMap(x, y);

        if ( pos != null ) {
            setMapPosition(0,0);
        }

        return pos;

    }
}
