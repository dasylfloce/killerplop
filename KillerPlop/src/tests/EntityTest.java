package tests;

import resources.sprites.ImageStore;
import resources.sprites.SimpleSprite;
import resources.sprites.SpriteFactory;
import Constants.Constants;
import entities.aliens.AlienEntity;
import entities.manager.EntityManager;
import entities.manager.EntityManagerImpl;
import entities.movement.basics.DynamicMovement;
import entities.movement.basics.SinusMovement;
import entities.movement.basics.StaticMovement;
import entities.ship.ShipEntity;

public class EntityTest extends Test implements Constants{

	private static EntityManager manager;
	public static ShipEntity ship = new ShipEntity(SpriteFactory.createBasicShip(), 10, WINDOW_HEIGHT / 2, SHIPSPEED);

	public static EntityManager createEntityManager() {
		manager = new EntityManagerImpl();
		testSinusStatic();
		manager.addEntity(ship);
		return manager;
	}

	protected static void testStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new AlienEntity(new SimpleSprite(ImageStore.get(
					"resources/entities/alien.gif")), 800 + 100 * i
					+ r.nextInt(WINDOW_HEIGHT - 40), r
					.nextInt(Test.WINDOW_HEIGHT), new StaticMovement()));
		}
	}

	protected static void testSinus() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(250);
			manager.addEntity(new AlienEntity(new SimpleSprite(ImageStore.get(
					"resources/entities/ship.gif")), 800 + 100 * i
					+ r.nextInt(WINDOW_WIDTH), r
					.nextInt(Test.WINDOW_HEIGHT - amplitude)
					+ amplitude / 2, new DynamicMovement(new SinusMovement(
					amplitude, r.nextInt(3) + 1, -r.nextInt(200)))));
		}
	}

	protected static void testSinusStatic() {
		for (int i = 0; i < 80; i++) {
			int amplitude = r.nextInt(150);
			manager.addEntity(new AlienEntity(SpriteFactory.createChampi(), WINDOW_WIDTH + 100 * i
					+ r.nextInt(WINDOW_WIDTH), r
					.nextInt(Test.WINDOW_HEIGHT - 2*amplitude)
					+ amplitude / 2, new SinusMovement(amplitude, r
					.nextInt(3) + 1, r.nextInt(200))));
		}
	}

}
