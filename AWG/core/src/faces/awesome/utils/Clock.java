package faces.awesome.utils;

/*
* Simple clock class.
* Usually used together with the Timer class to prevent an "event" from occuring too often.
*/

public class Clock {

    private long millis;

    public Clock() {
        update();
    }

    public void update() {
        millis = System.currentTimeMillis();
    }

    public long getMillis() {
        // pretty sure this update here isn't necessary.
        update();
        return millis;
    }

    public String formatTime(long millis) {
        // TODO:
        // Fix proper string ...

        int hundreds = (int) (millis / 10) % 100;
        int seconds = (int) (millis / 1000) % 60;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        return hours + ":" + minutes + ":" + seconds + ":" + hundreds;
    }
}
