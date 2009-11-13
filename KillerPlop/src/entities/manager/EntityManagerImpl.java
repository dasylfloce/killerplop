package entities.manager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import Constants.Constants;

import controller.GameController;
import entities.Entity;
import entities.aliens.AlienEntity;
import entities.ship.ShipEntity;
import entities.shots.ShotEntity;
import entities.shots.ShotPool;

/**
 * Implements EntityManager.
 * 
 * Has several lists to store activated entities and desactivated aliens entities,
 * ships and shots.
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
	protected ArrayList<AlienEntity> activatedEntities;

	protected LinkedList<ShipEntity> shipEntities;
	protected LinkedList<ShotEntity> shotEntities;
	

	/**
	 * Constructor
	 */
	public EntityManagerImpl() {
		sleepingEntities = new LinkedList<AlienEntity>();
		shipEntities = new LinkedList<ShipEntity>();
		activatedEntities = new ArrayList<AlienEntity>();
		shotEntities = new LinkedList<ShotEntity>();
	}

	@Override
	public void activateEntities(double x) {
		if (!sleepingEntities.isEmpty()) {
			boolean next = true;
			do {
				if (next = sleepingEntities.getFirst().getActivationPoint() < x)
					activatedEntities.add(sleepingEntities.removeFirst());
			} while (next && !sleepingEntities.isEmpty());
		}
	}

	@Override
	public void desactivateEntity(Entity entity) {
		activatedEntities.remove(entity);
		if (entity.isAlienEntity()) {
			AlienEntity ae = (AlienEntity) entity;
			if (ae.canBeReactivated()) {
				ae.nextActivation();
				sleepingEntities.add(ae);
			}
		}
	}

	@Override
	public void moveEntities(GameController gameController) {
		for (AlienEntity entity : activatedEntities)
			entity.update(gameController);
		for (ShipEntity ship : shipEntities)
			ship.update(gameController);
		for (ShotEntity shot : shotEntities)
			shot.update(gameController);
	}

	@Override
	public void render(Graphics2D g, int offsetX, int offsetY) {
		for (AlienEntity entity : activatedEntities)
			entity.draw(g, offsetX, offsetY);
		for (ShipEntity ship : shipEntities)
			ship.draw(g, offsetX, offsetY);
		for (ShotEntity shot : shotEntities)
			shot.draw(g, offsetX, offsetY);
	}

	@Override
	public void resolveCollisions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEntity(Entity entity) {

		if (entity.isAlienEntity()) {
			// Adding this activated entity in the sleeping list, sorted by
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
				ListIterator<AlienEntity> i = sleepingEntities
						.listIterator();
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
			shotEntities.add((ShotEntity) entity);
	}

	@Override
	public void resolveShot(long delta) {
		for (ShipEntity ship : shipEntities)
			if(ship.isShooting()){
				shotEntities.addFirst(ShotPool.getInstance().getShot());
				ship.setShooting(false);
				shotEntities.getFirst().setX(ship.getX()+30);
				shotEntities.getFirst().setY(ship.getY()+5);
			}	
			
	}

}
