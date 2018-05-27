package faces.awesome.controllers;

/**
 * @author Linus Wallman
 * Updated by: Therese Sturesson
 *
 * Cointains all the screen the switcher can change to
 */

public class ScreenSwitcher {

    //Varibles
    private static ScreenSwitchListener currentListener;
    private static ScreenType currentScreen;

    //Enum with the screens
    public enum ScreenType {
        GameScreen, MainScreen, GameOverScreen, GameWonScreen, CreditScreen
    }


    public static void changeScreen(ScreenType screen) {
        currentScreen = screen;
        notifyListener();
    }

    //Methods for the listener
    public static void setListener(ScreenSwitchListener listener) {
        currentListener = listener;
    }

    private static void notifyListener() {
        if (currentListener != null) {
            currentListener.onScreenChange(currentScreen);
        }
    }

}
