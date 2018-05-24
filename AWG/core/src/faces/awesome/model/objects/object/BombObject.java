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

    public BombObject(Position pos) {
        super(pos, "Bomb");

        countDown();
    }

    private void countDown() {
        MapSegment.addToObjects(this);

        int x = pos.getX();
        int y = pos.getY();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Enemy> targets = MapSegment.getPlayerTargets(x-2, y+2, x+2, y-2);
                for (Enemy enemy: targets) {
                    enemy.decreaseHealth(10);
                }
                removeObject();
            }
        }, 1500);
    }

    private void removeObject() {
        MapSegment.removeFromObjects(this);
    }
}
