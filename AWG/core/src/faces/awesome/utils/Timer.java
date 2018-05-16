package faces.awesome.utils;
import faces.awesome.AwesomeGame;

public class Timer {

    private long startTicks;
    private boolean isStopped;

    public Timer() {
        restart();
    }

    public void restart() {
        startTicks = AwesomeGame.AWG_TIME.getMillis();
        isStopped = false;
    }

    public long ticksElapsed() {
        return AwesomeGame.AWG_TIME.getMillis() - startTicks;
    }

    public boolean isRunning() {
        return !isStopped;
    }

    public float secondsElapsed() {
        return ticksElapsed() / 1000.0f;
    }
}
