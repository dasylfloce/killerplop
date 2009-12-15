package fr.emn.killerplop.story.test;

import java.util.Random;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.entitymanager.EntityManager;
import fr.emn.killerplop.game.controller.entitymanager.EntityManagerImpl;
import fr.emn.killerplop.game.entities.aliens.AlienEntity;
import fr.emn.killerplop.game.entities.movement.basics.DynamicMovement;
import fr.emn.killerplop.game.entities.movement.basics.SinusMovement;
import fr.emn.killerplop.game.entities.movement.basics.StaticMovement;
import fr.emn.killerplop.game.entities.ship.ShipEntity;
import fr.emn.killerplop.graphics.sprites.SimpleSprite;
import fr.emn.killerplop.graphics.sprites.SpriteFactory;

public class EntityTest implements Constants {

	private static Random r = new Random();
	private static EntityManager manager;

	public static ShipEntity ship = new ShipEntity(SpriteFactory
			.createShip(), 10, WINDOW_HEIGHT / 2, SHIPSPEED);

	public static EntityManager createEntityManager() {
		manager = new EntityManagerImpl();
		testSinusStaticSeveralEnemies();
		manager.addEntity(ship);
		return manager;
	}

	protected static void testStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new AlienEntity(new SimpleSprite(
					"entities/alien.gif"), 200 + 30 * i
					+ r.nextInt(WINDOW_HEIGHT - 40), r
					.nextInt(WINDOW_HEIGHT), new StaticMovement()));
		}
	}

	protected static void testSinus() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(250);
			manager.addEntity(new AlienEntity(new SimpleSprite(
					"entities/ship.gif"), 800 + 100 * i
					+ r.nextInt(WINDOW_WIDTH), r.nextInt(WINDOW_HEIGHT
					- amplitude)
					+ amplitude / 2, new DynamicMovement(new SinusMovement(
					amplitude, r.nextInt(3) + 1, -r.nextInt(200)))));
		}
	}

	protected static void testSinusStatic() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(150);
			manager.addEntity(new AlienEntity(SpriteFactory.createBoss(),
					WINDOW_WIDTH + 100 * i + r.nextInt(WINDOW_WIDTH), r
							.nextInt(WINDOW_HEIGHT - 2 * amplitude)
							+ amplitude / 2, new SinusMovement(amplitude, r
							.nextInt(3) + 1, r.nextInt(200))));
		}
	}

	protected static void testSinusStaticSeveralEnemies() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(150);
			manager.addEntity(new AlienEntity(SpriteFactory.createrandom(),
					WINDOW_WIDTH + 30 * i + r.nextInt(WINDOW_WIDTH), r
							.nextInt(WINDOW_HEIGHT - 2 * amplitude)
							+ amplitude / 2, new SinusMovement(amplitude, r
							.nextInt(3) + 1, -r.nextInt(300)+100)));
		}
	}

}
