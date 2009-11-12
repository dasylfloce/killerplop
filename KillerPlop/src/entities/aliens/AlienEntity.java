package entities.aliens;

import controller.GameController;
import entities.EntityImpl;
import entities.movement.Movement;
import entities.sprites.Sprite;

/**
 * This is an Entity that moves according to a Movement class, and which has an
 * activation point.
 * 
 * An activation point is a horizontal position in which the entity is taken in
 * calculation by the controller.
 * 
 * @author Aurélien RAMBAUX
 * 
 */
public class AlienEntity extends EntityImpl implements
		Comparable<AlienEntity> {

	protected int[] activatingPoints;
	protected int currentActivation;
	protected Movement movement;

	public AlienEntity(Sprite sprite, int activationPoint, int y,
			Movement movement) {
		this(sprite, new int[] { activationPoint }, y, movement);
	}

	public AlienEntity(Sprite sprite, int[] activatingPoints, int y,
			Movement movement) {
		super(sprite, activatingPoints[0], y);
		this.activatingPoints = activatingPoints;
		this.movement = movement;
		currentActivation = 0;
	}

	/**
	 * @return the x position of its next activation point.
	 */
	public int getActivationPoint() {
		return activatingPoints[currentActivation];
	}

	/**
	 * @return true if the entity can be reactivated.
	 */
	public boolean canBeReactivated() {
		return currentActivation < activatingPoints.length - 1;
	}

	/**
	 * Update the status of this ActivatedEntity for its next activation.
	 */
	public void nextActivation() {
		currentActivation++;
	}

	@Override
	public boolean isAlienEntity() {
		return true;
	}

	@Override
	public boolean isShipEntity() {
		return false;
	}

	@Override
	public boolean isShotEntity() {
		return false;
	}

	@Override
	public void calculateSpeed(GameController gameController) {
		movement.setMovement(this, gameController);
	}

	@Override
	public int compareTo(AlienEntity arg0) {
		if (getActivationPoint() < arg0.getActivationPoint())
			return -1;
		else if (getActivationPoint() == arg0.getActivationPoint())
			return 0;
		else
			return 1;
	}

}
