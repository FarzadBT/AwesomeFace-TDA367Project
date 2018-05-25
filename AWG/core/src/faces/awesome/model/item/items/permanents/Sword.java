package faces.awesome.model.item.items.permanents;

import faces.awesome.model.characters.Enemy;
import faces.awesome.model.Facing;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;
import faces.awesome.model.item.BaseItem;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Farzad Besharati
 *
 * TODO skriva vad klassen gör
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
    public void use(Position pos, Facing facing, MapSegment segment) {
        List<Enemy> enemies = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        if (facing == Facing.SOUTH)
            enemies = segment.getPlayerTargets(x-1, y-1, x+1, y-1);
        else if(facing == Facing.NORTH)
            enemies = segment.getPlayerTargets(x-1, y+1, x+1, y+1);
        else if(facing == Facing.EAST)
            enemies = segment.getPlayerTargets(x+1, y+1, x+1, y-1);
        else if(facing == Facing.WEST)
            enemies = segment.getPlayerTargets(x-1, y+1, x-1, y-1);

        for (Enemy enemy : enemies) {
            enemy.decreaseHealth(5);
        }
    }

}
