package fr.emn.killerplop.tests;

import fr.emn.killerplop.game.controller.entitymanager.EntityManager;
import fr.emn.killerplop.game.controller.entitymanager.EntityManagerImpl;
import fr.emn.killerplop.game.entities.aliens.AlienEntity;
import fr.emn.killerplop.game.entities.movement.basics.DynamicMovement;
import fr.emn.killerplop.game.entities.movement.basics.SinusMovement;
import fr.emn.killerplop.game.entities.movement.basics.StaticMovement;
import fr.emn.killerplop.game.entities.ship.ShipEntity;
import fr.emn.killerplop.game.resources.ImageStore;
import fr.emn.killerplop.game.sprites.SimpleSprite;
import fr.emn.killerplop.game.sprites.SpriteFactory;

public class EntityTest extends Test {

	private static EntityManager manager;
	public static ShipEntity ship = new ShipEntity(SpriteFactory.createAnimatedShip(), 10, WINDOW_HEIGHT / 2,
			SHIPSPEED);

	public static EntityManager createEntityManager() {
		manager = new EntityManagerImpl();
		testSinusStaticSeveralEnemies();
		manager.addEntity(ship);
		return manager;
	}

	protected static void testStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new AlienEntity(new SimpleSprite(ImageStore
					.get("resources/entities/alien.gif")), 200 + 30 * i
					+ r.nextInt(WINDOW_HEIGHT - 40), r
					.nextInt(Test.WINDOW_HEIGHT), new StaticMovement()));
		}
	}

	protected static void testSinus() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(250);
			manager.addEntity(new AlienEntity(new SimpleSprite(ImageStore
					.get("resources/entities/ship.gif")), 800 + 100 * i
					+ r.nextInt(WINDOW_WIDTH), r.nextInt(Test.WINDOW_HEIGHT
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
							.nextInt(Test.WINDOW_HEIGHT - 2 * amplitude)
							+ amplitude / 2, new SinusMovement(amplitude, r
							.nextInt(3) + 1, r.nextInt(200))));
		}
	}
	
	protected static void testSinusStaticSeveralEnemies() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(150);
			manager.addEntity(new AlienEntity(SpriteFactory.createrandom(),
					WINDOW_WIDTH + 100 * i + r.nextInt(WINDOW_WIDTH), r
							.nextInt(Test.WINDOW_HEIGHT - 2 * amplitude)
							+ amplitude / 2, new SinusMovement(amplitude, r
							.nextInt(3) + 1, r.nextInt(200))));
		}
	}

}
