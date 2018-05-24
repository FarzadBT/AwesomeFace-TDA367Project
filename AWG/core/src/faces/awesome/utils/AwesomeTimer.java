package faces.awesome.utils;
import faces.awesome.AwesomeGame;
import faces.awesome.GDXWrapper;

/**
 * @author Linus Wallman
 * Simple timer class.
 */

public class AwesomeTimer {

    private long startTicks;
    private boolean isStopped;
    private AwesomeClock clock;

    public AwesomeTimer() {
        clock = new AwesomeClock();
        restart();
    }

    public void restart() {
        startTicks = clock.getMillis();
        isStopped = false;
    }

    public long ticksElapsed() {
        return clock.getMillis() - startTicks;
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
