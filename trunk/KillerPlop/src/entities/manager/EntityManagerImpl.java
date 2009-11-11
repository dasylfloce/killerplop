package entities.manager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import controller.GameController;
import entities.ActivatedEntity;
import entities.Entity;
import entities.ShipEntity;
import entities.ShotEntity;

/**
 * Implements EntityManager.
 * 
 * Has several lists to store activated entities and desactivated entities,
 * ships and shots.
 * 
 * @author Administrateur
 * 
 */
public class EntityManagerImpl implements EntityManager {

	/**
	 * Entities waiting to be added in the activated list.
	 */
	protected LinkedList<ActivatedEntity> sleepingEntities;
	/**
	 * Entities currently active.
	 */
	protected ArrayList<ActivatedEntity> activatedEntities;

	protected LinkedList<ShipEntity> shipEntities;
	protected LinkedList<ShotEntity> shotEntities;

	/**
	 * Constructor
	 */
	public EntityManagerImpl() {
		sleepingEntities = new LinkedList<ActivatedEntity>();
		shipEntities = new LinkedList<ShipEntity>();
		activatedEntities = new ArrayList<ActivatedEntity>();
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
		if (entity.isActivatedEntity()) {
			ActivatedEntity ae = (ActivatedEntity) entity;
			if (ae.canBeReactivated()) {
				ae.nextActivation();
				sleepingEntities.add(ae);
			}
		}
	}

	@Override
	public void moveEntities(GameController gameController) {
		for (ActivatedEntity entity : activatedEntities)
			entity.update(gameController);
		for (ShipEntity ship : shipEntities)
			ship.update(gameController);
	}

	@Override
	public void render(Graphics2D g, int offsetX, int offsetY) {
		for (ActivatedEntity entity : activatedEntities)
			entity.draw(g, offsetX, offsetY);
		for (ShipEntity ship : shipEntities)
			ship.draw(g, offsetX, offsetY);
	}

	@Override
	public void resolveCollisions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEntity(Entity entity) {

		if (entity.isActivatedEntity()) {
			// Adding this activated entity in the sleeping list, sorted by
			// activation point.
			ActivatedEntity ae = (ActivatedEntity) entity;

			// There is three special cases, when the list is empty or when the
			// entity to add is the first one or the last one.
			if (sleepingEntities.isEmpty()) {
				sleepingEntities.add(ae);
			} else if (sleepingEntities.getFirst().compareTo(ae) > 0) {
				sleepingEntities.addFirst(ae);
			} else if (sleepingEntities.getLast().compareTo(ae) < 0) {
				sleepingEntities.addLast(ae);
			} else {
				ListIterator<ActivatedEntity> i = sleepingEntities
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

}
