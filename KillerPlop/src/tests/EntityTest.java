package tests;

import java.util.LinkedList;

import entities.ActivatedEntity;
import entities.ShipEntity;
import entities.manager.EntityManager;
import entities.manager.EntityManagerImpl;
import entities.movement.LinearMovement;
import entities.movement.SinusMovement;
import entities.movement.SinusMovementStatic;
import entities.movement.StaticMovement;
import entities.sprites.SpriteStore;

@SuppressWarnings("serial")
public class EntityTest extends MapTest {

	protected EntityManager createEntityManager() {
		return testShip();
	}

	protected EntityManager testStatic() {
		LinkedList<ActivatedEntity> sleepingEntities = new LinkedList<ActivatedEntity>();
		for (int i = 0; i < 80; i++) {
			sleepingEntities.add(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/alien.gif"), r
					.nextInt(windowSize.height), new StaticMovement(), 800
					+ 100 * i + r.nextInt(500)));
		}

		EntityManager entityManager = new EntityManagerImpl(null,
				sleepingEntities);
		return entityManager;
	}

	protected EntityManager testSinus() {
		LinkedList<ActivatedEntity> sleepingEntities = new LinkedList<ActivatedEntity>();
		for (int i = 0; i < 80; i++) {
			sleepingEntities.add(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/ship.gif"), r
					.nextInt(windowSize.height), new SinusMovement(r
					.nextInt(250), r.nextInt(3) + 1, -40), 800 + 100 * i
					+ r.nextInt(500)));
		}

		EntityManager entityManager = new EntityManagerImpl(null,
				sleepingEntities);
		return entityManager;
	}
	
	protected EntityManager testShip() {
		LinkedList<ActivatedEntity> sleepingEntities = new LinkedList<ActivatedEntity>();
		for (int i = 0; i < 80; i++) {
			sleepingEntities.add(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/ship.gif"), r
					.nextInt(windowSize.height), new SinusMovement(r
					.nextInt(250), r.nextInt(3) + 1, -40), 800 + 100 * i
					+ r.nextInt(500)));
		}
		LinkedList<ShipEntity> shipEntities = new LinkedList<ShipEntity>();
		shipEntities.add(new ShipEntity(SpriteStore.get().getSprite("sprites/alien.gif"), 10, windowSize.height/2, new LinearMovement()));

		EntityManager entityManager = new EntityManagerImpl(shipEntities,
				sleepingEntities);
		return entityManager;
	}
	
	protected EntityManager testSinusStatic() {
		LinkedList<ActivatedEntity> sleepingEntities = new LinkedList<ActivatedEntity>();
		for (int i = 0; i < 80; i++) {
			sleepingEntities.add(new ActivatedEntity(SpriteStore.get()
					.getSprite("sprites/ship.gif"), r
					.nextInt(windowSize.height), new SinusMovementStatic(r
					.nextInt(250), r.nextInt(3) + 1, + 80), 800 + 100 * i
					+ r.nextInt(500)));
		}

		EntityManager entityManager = new EntityManagerImpl(null,
				sleepingEntities);
		return entityManager;
	}

	/**
	 * The entry point into the game. We'll simply create an instance of class
	 * which will start the display and game loop.
	 * 
	 * @param argv
	 *            The arguments that are passed into our game
	 */
	public static void main(String argv[]) {
		EntityTest mapTest = new EntityTest();

		// Start the main game loop, note: this method will not
		// return until the game has finished running. Hence we are
		// using the actual main thread to run the game.
		mapTest.startRendering();
	}
}
