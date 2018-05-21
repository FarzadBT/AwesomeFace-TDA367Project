package faces.awesome.model.item.items.permanents;

import faces.awesome.AwesomeGame;
import faces.awesome.model.Enemy;
import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr Cornholio on 14/05/2018.
 */
public class Sword extends BaseItem {

    public Sword() {
        name = "Sword";
    }

    /**
     * Swing the sword side to side on the three blocks in front of you
     * @param pos current player position
     * @param facing current player facing
     */
    @Override
    public void use(Position pos, Facing facing) {
        List<Enemy> enemies = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        if (facing == Facing.SOUTH)
            enemies = MapSegment.getPlayerTargets(x-1, y-1, x+1, y-1);
        else if(facing == Facing.NORTH)
            enemies = MapSegment.getPlayerTargets(x-1, y+1, x+1, y+1);
        else if(facing == Facing.EAST)
            enemies = MapSegment.getPlayerTargets(x+1, y+1, x+1, y-1);
        else if(facing == Facing.WEST)
            enemies = MapSegment.getPlayerTargets(x-1, y+1, x-1, y-1);

        for (Enemy enemy : enemies) {
            enemy.decreaseHealth(5);
        }
    }
}
