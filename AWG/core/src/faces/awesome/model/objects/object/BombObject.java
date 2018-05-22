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
        super(pos, "bombObject");

        countDown();
    }

    private void countDown() {
        int x = pos.getX();
        int y = pos.getY();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Enemy> targets = MapSegment.getPlayerTargets(x-1, y+1, x+1, y-1);
                for (Enemy enemy: targets) {
                    enemy.decreaseHealth(10);
                }
            }
        }, 1500);
    }
}
