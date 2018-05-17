package faces.awesome.model.objects.object;

import faces.awesome.model.GameObject;
import faces.awesome.model.Position;

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
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1500);
    }
}
