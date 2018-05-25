package faces.awesome.controllers;

import faces.awesome.controllers.ScreenSwitcher.ScreenType;

/**
 * @author Linus Wallman
 *
 * An interface for the listener
 */

public interface ScreenSwitchListener {

    void onScreenChange(ScreenType screen);

}
