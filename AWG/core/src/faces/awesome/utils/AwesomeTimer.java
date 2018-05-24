package faces.awesome.utils;
import faces.awesome.AwesomeGame;
import faces.awesome.GDXWrapper;

/**
 * @author Linus Wallman
 * Simple timerMove class.
 */

public class AwesomeTimer {

    private long startTicks;
    private boolean isStopped;

    public AwesomeTimer() {
        restart();
    }

    public void restart() {
        startTicks = GDXWrapper.AWG_TIME.getMillis();
        isStopped = false;
    }

    public long ticksElapsed() {
        return GDXWrapper.AWG_TIME.getMillis() - startTicks;
    }

    public boolean isRunning() {
        return !isStopped;
    }

    public float secondsElapsed() {
        return ticksElapsed() / 1000.0f;
    }

    public void stop() {
        isStopped = true;
    }

    public void start() {
        isStopped = false;
    }
}
