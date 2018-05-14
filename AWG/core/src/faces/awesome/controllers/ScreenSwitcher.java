package faces.awesome.controllers;

public class ScreenSwitcher {

    private static ScreenSwitchListener currentListener;
    private static ScreenType currentScreen;

    public enum ScreenType {
        GameScreen, MainScreen, SaveManagingScreen, GameOverScreen, SettingsScreen
    }

    public static void changeScreen(ScreenType screen) {
        currentScreen = screen;
        notifyListener();
    }

    private static void notifyListener() {
        if (currentListener != null) {
            currentListener.onScreenChange(currentScreen);
        }
    }




}
