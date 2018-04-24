package faces.awesome;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import faces.awesome.AwesomeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// setup window, these should probably be changed in the future.
		config.width = 1024;
		config.height = 512;
		config.title = "AwesomeGame";
		new LwjglApplication(new AwesomeGame(), config);
		
	}
}
