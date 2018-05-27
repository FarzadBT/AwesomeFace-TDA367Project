package faces.awesome.model;

import faces.awesome.model.factories.PickupFactory;
import faces.awesome.model.objects.pickup.BasePickup;
import faces.awesome.model.objects.pickup.SmallBomb;
import faces.awesome.model.objects.pickup.SmallHeart;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Farzad Besharati
 *
 * The droptable is a list of pickups that can appear in the world after the 'roll' method is called
 * usually after an enemy dies
 */

public class DropTable {
    private List<PickupFactory> dropTable;
    private BasePickup roll;

    public DropTable() {
        dropTable = new ArrayList<>();
        init();
    }

    /**
     * Initialize the drop-table with dropppable items
     */
    private void init() {
        dropTable.add(new PickupFactory(() -> new SmallBomb(new Position(1,1))));
        dropTable.add(new PickupFactory(() -> new SmallHeart(new Position(1,1))));
    }

    /**
     * When you roll you have a random chance to create a pickup, of the same type rolled from the list,
     * on the position designated.
     * @param pos
     */
    public void roll(Position pos, MapSegment segment) {
        List<BasePickup> pickupsInSegment = segment.getPickupsInSegment();

        // If the current position is already occupied by a pickup, don't do anything
        if (!pickupsInSegment.isEmpty()) {
            for (BasePickup p : segment.getPickupsInSegment()) {
                if (p.getPos().getX() == pos.getX() && p.getPos().getY() == pos.getY())
                    return;
            }
        }

        Random rand = new Random();
        if (rand.nextInt(3) == 0) {
            roll = dropTable.get(rand.nextInt(dropTable.size())).create();
            roll.setPos(pos);
            segment.addToPickup(roll);
        }
    }
}
