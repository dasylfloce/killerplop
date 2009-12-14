package fr.emn.killerplop.graphics.sprites;

import java.util.Random;

import fr.emn.killerplop.game.entities.shapes.CircleShape;
import fr.emn.killerplop.game.entities.shapes.PointShape;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.imageCenter.ImageCenter;

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
public class SpriteFactory implements ImageCenter {

	public static Sprite createShip() {
		return new AnimatedSprite(new String[] { SHIP_1, SHIP_2 }, 40);
	}
	
	public static Sprite createShipShot() {
		return new SimpleSprite(SHIP_SHOT, new PointShape(10, 5));
	}

	public static Sprite createSonicOr() {
		return new AnimatedSprite(new String[] { SONIC_OR_1, SONIC_OR_2,
				SONIC_OR_3 }, 40, new CircleShape(25));
	}

	public static Sprite createSonicBleu() {
		return new AnimatedSprite(new String[] { SONIC_BLEU_1, SONIC_BLEU_2,
				SONIC_BLEU_3 }, 40, new CircleShape(25));
	}

	public static Sprite createBoss() {

		long[] time = { 400, 150, 150, 400, 150, 150 };
		Shape shape = new CircleShape(39);
		return new AnimatedSprite(new String[] { BOSS_1, BOSS_2, BOSS_3,
				BOSS_4, BOSS_3, BOSS_2 }, time, shape);
	}

	public static Sprite createrandom() {
		Random r = new Random();
		int random = r.nextInt(3);
		switch (random) {
		case 0:
			return createBoss();
		case 1:
			return createSonicBleu();
		case 2:
			return createSonicOr();
		default:
			return createSonicOr();
		}

	}
}