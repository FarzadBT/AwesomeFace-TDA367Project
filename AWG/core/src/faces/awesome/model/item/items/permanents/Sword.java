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

    /**
     * Swing the sword side to side on the three blocks in front of you
     * @param pos current player position
     * @param facing current player facing
     */
    @Override
    public void use(Position pos, Facing facing, MapSegment segment) {
        List<Enemy> enemies = new ArrayList<>();
        enemies = segment.getPlayerTargets(pos, 1, 1, facing);

        for (Enemy enemy : enemies) {
            enemy.decreaseHealth(10);
        }
    }
}
