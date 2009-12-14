package fr.emn.killerplop.game.controller.entitymanager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.explosionmanager.ExplosionManager;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.entities.Entity;
import fr.emn.killerplop.game.entities.aliens.AlienEntity;
import fr.emn.killerplop.game.entities.ship.ShipEntity;
import fr.emn.killerplop.game.entities.shots.ShipShotEntity;
import fr.emn.killerplop.game.entities.shots.ShipShotPool;
import fr.emn.killerplop.graphics.context.GraphicContext;

/**
 * Implements EntityManager.
 * 
 * Has several lists to store activated entities and desactivated aliens
 * entities, ships and shots.
 * 
 * @author Administrateur
 * 
 */
public class EntityManagerImpl implements EntityManager, Constants {

	/**
	 * Entities waiting to be added in the activated list.
	 */
	protected LinkedList<AlienEntity> sleepingEntities;
	/**
	 * Entities currently active.
	 */
	protected ArrayList<AlienEntity> alienEntities;

	protected LinkedList<ShipEntity> shipEntities;
	protected LinkedList<ShipShotEntity> shipShotEntities;
	
	/**
	 * Explosion manager (visual effects)
	 */
	protected ExplosionManager explosionManager;

	/**
	 * Constructor
	 */
	public EntityManagerImpl() {
		explosionManager = new ExplosionManager();
		sleepingEntities = new LinkedList<AlienEntity>();
		shipEntities = new LinkedList<ShipEntity>();
		alienEntities = new ArrayList<AlienEntity>();
		shipShotEntities = new LinkedList<ShipShotEntity>();
	}

	@Override
	public void activateEntities(double x) {
		if (!sleepingEntities.isEmpty()) {
			boolean next = true;
			do {
				if (next = sleepingEntities.getFirst().getActivationPoint() < x)
					alienEntities.add(sleepingEntities.removeFirst());
			} while (next && !sleepingEntities.isEmpty());
		}
	}

	@Override
	public void moveEntities(GameController gameController) {
		for (AlienEntity entity : alienEntities)
			entity.move(gameController);
		for (ShipEntity ship : shipEntities)
			ship.move(gameController);
		for (ShipShotEntity shot : shipShotEntities)
			shot.move(gameController);
	}

	@Override
	public void render(GraphicContext graphicContext, int offsetX, int offsetY) {
		for (AlienEntity entity : alienEntities)
			entity.draw(graphicContext, offsetX, offsetY);
		for (ShipEntity ship : shipEntities)
			ship.draw(graphicContext, offsetX, offsetY);
		for (ShipShotEntity shot : shipShotEntities)
			shot.draw(graphicContext, offsetX, offsetY);
		explosionManager.render(graphicContext, offsetX, offsetY);
	}

	@Override
	public void resolveCollisions(double x, double y, int viewHeight,
			int viewWidth) {

		// Collisions between aliens and ships
		for (ShipEntity ship : shipEntities) {
			if (!ship.isDestroyed())
				for (AlienEntity ae : alienEntities)
					if (ae.collidesWith(ship))
						ship.hit();
		}

		// Collisions between aliens and ship shots
		for (AlienEntity ae : alienEntities)
			if (!ae.isDestroyed())
				for (ShipShotEntity shot : shipShotEntities)
					if (!shot.isDestroyed())
						if (ae.collidesWith(shot)) {
							ae.hit();
							shot.hit();
						}
	}

	public void cleanUpEntities() {

		// Cleaning ships destroyed
		ListIterator<ShipEntity> shipIt = shipEntities.listIterator();
		ShipEntity ship;
		while (shipIt.hasNext()) {
			ship = shipIt.next();
			if (ship.isDestroyed()) {
				explosionManager.destroy(ship);
				shipIt.remove();
			}
		}
		// Cleaning aliens destroyed
		ListIterator<AlienEntity> alienIt = alienEntities.listIterator();
		AlienEntity ae;
		while (alienIt.hasNext()) {
			ae = alienIt.next();
			if (ae.isDestroyed()) {
				explosionManager.destroy(ae);
				if (ae.canBeReactivated()) {
					ae.nextActivation();
					addEntity(ae);
				}
				alienIt.remove();
			}
		}
		// Cleaning shots destroyed
		ListIterator<ShipShotEntity> shotIt = shipShotEntities.listIterator();
		ShipShotEntity shot;
		while (shotIt.hasNext()) {
			shot = shotIt.next();
			if (shot.isDestroyed()) {
				ShipShotPool.getInstance().returnShot(shot);
				shotIt.remove();
			}
		}

	}

	@Override
	public void addEntity(Entity entity) {

		if (entity.isAlienEntity()) {
			// Adding this alien entity in the sleeping list, sorted by
			// activation point.
			AlienEntity ae = (AlienEntity) entity;

			// There is three special cases, when the list is empty or when the
			// entity to add is the first one or the last one.
			if (sleepingEntities.isEmpty()) {
				sleepingEntities.add(ae);
			} else if (sleepingEntities.getFirst().compareTo(ae) > 0) {
				sleepingEntities.addFirst(ae);
			} else if (sleepingEntities.getLast().compareTo(ae) < 0) {
				sleepingEntities.addLast(ae);
			} else {
				ListIterator<AlienEntity> i = sleepingEntities.listIterator();
				while (i.hasNext() && i.next().compareTo(ae) < 0)
					;
				// Must go backward
				i.previous();
				i.add(ae);
			}
		}

		if (entity.isShipEntity())
			shipEntities.add((ShipEntity) entity);
		if (entity.isShotEntity())
			shipShotEntities.add((ShipShotEntity) entity);
	}

	@Override
	public void resolveShot(long delta) {
		for (ShipEntity ship : shipEntities)
			if (!ship.isDestroyed())
				if (ship.isShooting()) {
					shipShotEntities.addFirst(ShipShotPool.getInstance().getShot());
					ship.setShooting(false);
					shipShotEntities.getFirst().setX(ship.getX() + 30);
					shipShotEntities.getFirst().setY(ship.getY() + 5);
				}

	}
	
	public void manageExplosions(long delta) {
		explosionManager.update(delta);
	}

}
