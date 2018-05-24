package faces.awesome.controllers;

import faces.awesome.controllers.ScreenSwitcher.ScreenType;

/**
 * @author Linus Wallman
 */

public interface ScreenSwitchListener {

    void onScreenChange(ScreenType screen);

}
