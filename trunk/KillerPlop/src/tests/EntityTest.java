package tests;

import entities.aliens.AlienEntity;
import entities.manager.EntityManager;
import entities.manager.EntityManagerImpl;
import entities.movement.ShipMovement;
import entities.movement.basics.DynamicMovement;
import entities.movement.basics.SinusMovement;
import entities.movement.basics.StaticMovement;
import entities.ship.ShipEntity;
import entities.sprites.SpriteStore;

public class EntityTest extends Test {

	private static EntityManager manager;

	public static EntityManager createEntityManager() {
		manager = new EntityManagerImpl();
		testSinusStatic();
		testShip();
		return manager;
	}

	protected static void testStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new AlienEntity(SpriteStore.get().getSprite(
					"resources/sprites/alien.gif"), 800 + 100 * i
					+ r.nextInt(windowSize.height - 40), r
					.nextInt(Test.windowSize.height), new StaticMovement()));
		}
	}

	protected static void testSinus() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(250);
			manager.addEntity(new AlienEntity(SpriteStore.get().getSprite(
					"resources/sprites/ship.gif"), 800 + 100 * i
					+ r.nextInt(windowSize.width), r
					.nextInt(Test.windowSize.height - amplitude)
					+ amplitude / 2, new DynamicMovement(new SinusMovement(
					amplitude, r.nextInt(3) + 1, -r.nextInt(200)))));
		}
	}

	protected static void testShip() {
		manager.addEntity(new ShipEntity(SpriteStore.get().getSprite(
				"resources/sprites/alien.gif"), 10, windowSize.height / 2,
				new ShipMovement()));
	}

	protected static void testSinusStatic() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(250);
			manager.addEntity(new AlienEntity(SpriteStore.get().getSprite(
					"resources/sprites/ship.gif"), 800 + 100 * i
					+ r.nextInt(windowSize.width), r
					.nextInt(Test.windowSize.height - amplitude)
					+ amplitude / 2, new SinusMovement(amplitude, r
					.nextInt(3) + 1, r.nextInt(200))));
		}
	}

}
