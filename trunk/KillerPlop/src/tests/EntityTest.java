package tests;

import entities.ActivatedEntity;
import entities.ShipEntity;
import entities.manager.EntityManager;
import entities.manager.EntityManagerImpl;
import entities.movement.ShipMovement;
import entities.movement.SinusMovement;
import entities.movement.SinusMovementStatic;
import entities.movement.StaticMovement;
import entities.sprites.SpriteStore;

public class EntityTest extends Test{
	
	private static EntityManager manager;

	public static EntityManager createEntityManager() {
		manager = new EntityManagerImpl();
		testSinus();
		testShip();
		return manager;
	}

	protected static void testStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/alien.gif"), r
					.nextInt(Test.windowSize.height), new StaticMovement(), 800
					+ 100 * i + r.nextInt(windowSize.height-40)));
		}
	}

	protected static void testSinus() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/ship.gif"), r
					.nextInt(Test.windowSize.height), new SinusMovement(r
					.nextInt(250), r.nextInt(3) + 1, -40), 800 + 100 * i
					+ r.nextInt(windowSize.height-40)));
		}
	}
	
	protected static void testShip() {
		manager.addEntity(new ShipEntity(SpriteStore.get().getSprite("sprites/alien.gif"), 10, windowSize.height/2, new ShipMovement()));
	}
	
	protected static void testSinusStatic() {
		for (int i = 0; i < 80; i++) {
			manager.addEntity(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/ship.gif"), r
					.nextInt(Test.windowSize.height), new SinusMovementStatic(r
					.nextInt(250), r.nextInt(3) + 1, 80), 800 + 100 * i
					+ r.nextInt(windowSize.height-40)));
		}
	}

}
