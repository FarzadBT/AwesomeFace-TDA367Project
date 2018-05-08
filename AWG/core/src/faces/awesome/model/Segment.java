package faces.awesome.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//This class represents a section of the world map. We thought it a good idea to not concern ourselves with the entire map all the time.
//This class holds all relevant information for a set of 32x16 tiles.
public class Segment {


    private List<Position> positions = new ArrayList<>();
    private PlayerCharacter player;
    private List<Enemy> enemies = new ArrayList<>();

    public Segment(PlayerCharacter player, List<Enemy> enemies){
        this.player = player;
        this.enemies = enemies;
    }

    public List<Enemy> getEnemiesInSegment(){
        return enemies;
    }



    public void checkTargets(){
        for(Enemy e : enemies){
            e.getTargets();
        }
    }






}
