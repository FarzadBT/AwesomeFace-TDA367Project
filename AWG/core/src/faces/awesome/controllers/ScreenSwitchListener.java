package faces.awesome.controllers;

import faces.awesome.controllers.ScreenSwitcher.ScreenType;

public interface ScreenSwitchListener {

    void onScreenChange(ScreenType screen);
}
