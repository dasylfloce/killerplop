package fr.emn.killerplop.game.sprites;

import java.util.Random;

import fr.emn.killerplop.game.entities.shapes.CircleShape;
import fr.emn.killerplop.game.entities.shapes.Shape;

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

	public static Sprite createExplosion() {
		return new AnimatedSprite(new String[] {
				"resources/explosion/explosion1.png",
				"resources/explosion/explosion2.png",
				"resources/explosion/explosion3.png",
				"resources/explosion/explosion4.png",
				"resources/explosion/explosion5.png" }, 150,
				new CircleShape(25));
	}

	public static Sprite createBasicShip() {
		return new SimpleSprite("resources/entities/ship2.gif");
	}

	public static Sprite createAnimatedShip() {
		return new AnimatedSprite(new String[] {
				"resources/entities/ship2.gif",
				"resources/entities/ship3.gif" }, 40);
	}

	public static Sprite createChampi() {
		return new AnimatedSprite(new String[] {
				"resources/entities/ship.gif",
				"resources/entities/ship.gif",
				"resources/entities/ship.gif" }, 200);
	}

	public static Sprite createSonicOr() {
		return new AnimatedSprite(new String[] {
				"resources/entities/sonic_or1.png",
				"resources/entities/sonic_or2.png",
				"resources/entities/sonic_or3.png" }, 40,
				new CircleShape(25));
	}

	public static Sprite createSonicBleu() {
		return new AnimatedSprite(new String[] {
				"resources/entities/sonic_bleu1.png",
				"resources/entities/sonic_bleu2.png",
				"resources/entities/sonic_bleu3.png" }, 40,
				new CircleShape(25));
	}

	public static Sprite createBoss() {

		long[] time = { 400, 150, 150, 400, 150, 150 };
		Shape shape = new CircleShape(39);
		return new AnimatedSprite(new String[] {
				"resources/entities/boss0.png",
				"resources/entities/boss1.png",
				"resources/entities/boss2.png",
				"resources/entities/boss3.png",
				"resources/entities/boss2.png",
				"resources/entities/boss1.png" }, time, shape);
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
			return createChampi();
		}

	}
}