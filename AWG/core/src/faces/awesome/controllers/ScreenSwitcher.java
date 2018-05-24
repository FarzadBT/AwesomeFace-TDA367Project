package faces.awesome.controllers;

/**
 * @author Linus Wallman
 * Updated by: Therese Sturesson
 *
 * TODO skriva vad klassen gör
 */


public class ScreenSwitcher {

    private static ScreenSwitchListener currentListener;
    private static ScreenType currentScreen;

    public enum ScreenType {
        GameScreen, MainScreen, GameOverScreen, GameWonScreen, CreditScreen
    }

    public static void changeScreen(ScreenType screen) {
        currentScreen = screen;
        notifyListener();
    }

    public static void setListener(ScreenSwitchListener listener) {
        currentListener = listener;
    }

    private static void notifyListener() {
        if (currentListener != null) {
            currentListener.onScreenChange(currentScreen);
        }
    }




}
