package resources.sprites;

import java.awt.Image;

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
public class SpriteFactory {

	public static Sprite createBasicShip() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/ship2.gif"),
				ImageStore.get("resources/entities/ship2.gif"),
				ImageStore.get("resources/entities/ship2.gif"),
				ImageStore.get("resources/entities/ship2.gif") }, 200);
	}
	
	public static Sprite createChampi() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/champi1.png"),
				ImageStore.get("resources/entities/champi2.png"),
				ImageStore.get("resources/entities/champi3.png") }, 200);
	}
}