package faces.awesome.model.item.items.permanents;

import faces.awesome.model.Enemy;
import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr Cornholio on 14/05/2018.
 */
public class Sword extends BaseItem {
    public Sword() {
        name = "Sword";
    }

    @Override
    public void use(Position pos, Facing facing) {
        List<Enemy> enemies = new ArrayList<>();
        if (facing == Facing.SOUTH)
            enemies = MapSegment.getPlayerTargets(pos.getX()-1, pos.getY()-1, pos.getX()+1, pos.getY()-1);
        else if(facing == Facing.NORTH)
            enemies = MapSegment.getPlayerTargets(pos.getX()-1, pos.getY()+1, pos.getX()+1, pos.getY()+1);
        else if(facing == Facing.EAST)
            enemies = MapSegment.getPlayerTargets(pos.getX()+1, pos.getY()+1, pos.getX()+1, pos.getY()-1);
        else if(facing == Facing.WEST)
            enemies = MapSegment.getPlayerTargets(pos.getX()-1, pos.getY()+1, pos.getX()-1, pos.getY()-1);

        for (Enemy enemy : enemies) {
            enemy.decreaseHealth(5);
        }
    }
}
