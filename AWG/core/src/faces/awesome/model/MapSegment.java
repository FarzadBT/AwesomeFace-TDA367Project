package faces.awesome.model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class MapSegment {
    private WorldMap World;
    List<Enemy> EnemiesInWorld;
    PlayerCharacter player;

    List<Enemy> EnemiesInSegment = new ArrayList<>();
    List<Position> PositionsInSegment = new ArrayList<>();

    MapSegment(WorldMap World, List<Enemy> EnemiesInWorld, PlayerCharacter player){
        this.player = player;
        this.EnemiesInWorld = EnemiesInWorld;
        this.World = World;
    }

    public void getPositionsInSegment(){
        for(int i = World.getMapPosition().getX()*32; i < World.getMapPosition().getX()*32; i++){
            for(int j = World.getMapPosition().getY()*16; i < World.getMapPosition().getX()*16; i++){
                PositionsInSegment.add(new Position(i, j));
            }
        }
    }

    public void getEnemiesInSegment(){
        for(Position p : PositionsInSegment){
            for(Enemy e : EnemiesInWorld){
                if(e.getPos().equals(p)){
                    EnemiesInSegment.add(e);
                }
            }
        }
    }

    






}
