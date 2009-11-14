package resources.sprites;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A resource manager for sprites in the game. Its often quite important how and
 * where you get your game resources from. In most cases it makes sense to have
 * a central resource loader that goes away, gets your resources and caches them
 * for future use.
 * <p>
 * [singleton]
 * <p>
 * 
 * @author Aurélien RAMBAUX
 */
public class SpriteStore {

	public static Sprite createBasicShip() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/alien.gif"),
				ImageStore.get("resources/entities/alien2.gif"),
				ImageStore.get("resources/entities/alien.gif"),
				ImageStore.get("resources/entities/alien3.gif") }, 200);
	}
}