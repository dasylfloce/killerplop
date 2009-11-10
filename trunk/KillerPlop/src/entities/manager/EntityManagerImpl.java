package entities.manager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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
	/**
	 * Entities waiting for their next activation (not the first activation).
	 */
	protected ArrayList<ActivatedEntity> waitingEntities;

	protected LinkedList<ShipEntity> shipEntities;
	protected LinkedList<ShotEntity> shotEntities;

	/**
	 * Constructor
	 * 
	 * @param shipEntities
	 *            Ships that evolve in the level.
	 * @param waitingEntities
	 *            Entities that evolve in the level.
	 */
	public EntityManagerImpl(LinkedList<ShipEntity> shipEntities,
			LinkedList<ActivatedEntity> sleepingEntities) {

		if (sleepingEntities == null)
			this.sleepingEntities = new LinkedList<ActivatedEntity>();
		else
			this.sleepingEntities = sleepingEntities;

		if (sleepingEntities == null)
			this.shipEntities = new LinkedList<ShipEntity>();
		else
			this.shipEntities = shipEntities;
		activatedEntities = new ArrayList<ActivatedEntity>();
		waitingEntities = new ArrayList<ActivatedEntity>();
		shotEntities = new LinkedList<ShotEntity>();
		
		Collections.sort(this.sleepingEntities);
	}

	@Override
	public void activateEntities(double x) {
		if (!sleepingEntities.isEmpty()) {
			boolean next = true;
			do {
				if (next = sleepingEntities.getFirst().getCurrentActivation() < x)
					activatedEntities.add(sleepingEntities.removeFirst());
			} while (next && !sleepingEntities.isEmpty());
		}

		for (int i = 0; i < waitingEntities.size(); i++)
			if (waitingEntities.get(i).getCurrentActivation() < x)
				activatedEntities.add(waitingEntities.remove(i--));
	}

	@Override
	public void desactivateEntity(Entity entity) {
		activatedEntities.remove(entity);
		if (entity.isActivatedEntity()) {
			if (((ActivatedEntity) entity).canBeReactivated())
				waitingEntities.add((ActivatedEntity) entity);
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

}
