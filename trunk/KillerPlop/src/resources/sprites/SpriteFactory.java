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
	
	public static Sprite createAnimatedShip() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/ship2.gif"),
				ImageStore.get("resources/entities/ship3.gif")}, 40);
	}
	
	public static Sprite createChampi() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/ship.gif"),
				ImageStore.get("resources/entities/ship.gif"),
				ImageStore.get("resources/entities/ship.gif") }, 200);
	}
	
	public static Sprite createSonicOr() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/sonic_or1.png"),
				ImageStore.get("resources/entities/sonic_or2.png"),
				ImageStore.get("resources/entities/sonic_or3.png") }, 40);
	}
	
	public static Sprite createSonicBleu() {
		return new AnimatedSprite(new Image[] {
				ImageStore.get("resources/entities/sonic_bleu1.png"),
				ImageStore.get("resources/entities/sonic_bleu2.png"),
				ImageStore.get("resources/entities/sonic_bleu3.png") }, 40);
	}
}