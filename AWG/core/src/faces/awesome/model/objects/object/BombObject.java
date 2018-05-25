package faces.awesome.model.objects.object;

import faces.awesome.model.Enemy;
import faces.awesome.model.GameObject;
import faces.awesome.model.MapSegment;
import faces.awesome.model.Position;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mr Cornholio on 08/05/2018.
 */
public class BombObject extends GameObject {

    private MapSegment segment;

    public BombObject(Position pos, MapSegment segment) {
        super(pos, "Bomb");

        this.segment = segment;
        countDown();
    }

    private void countDown() {
        segment.addToObjects(this);

        int x = pos.getX();
        int y = pos.getY();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Enemy> targets = segment.getPlayerTargets(pos, 2, 2, facing);
                for (Enemy enemy: targets) {
                    enemy.decreaseHealth(15);
                }
                removeObject();
            }
        }, 1500);
    }

    private void removeObject() {
        segment.removeFromObjects(this);
    }
}
