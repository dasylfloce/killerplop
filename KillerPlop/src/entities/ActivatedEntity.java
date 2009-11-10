package entities;

import controller.GameController;
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
public class ActivatedEntity extends EntityImpl implements
		Comparable<ActivatedEntity> {

	protected int[] activatingPoints;
	protected int currentActivation;
	protected Movement movement;

	public ActivatedEntity(Sprite sprite, int y, Movement movement,
			int activationPoint) {
		this(sprite, y, movement, new int[] { activationPoint });
	}

	public ActivatedEntity(Sprite sprite, int y, Movement movement,
			int[] activatingPoints) {
		super(sprite, activatingPoints[0], y);
		this.activatingPoints = activatingPoints;
		this.movement = movement;
		currentActivation = 0;
	}

	public int getCurrentActivation() {
		return activatingPoints[currentActivation];
	}

	public boolean canBeReactivated() {
		return currentActivation < activatingPoints.length - 1;
	}

	public void nextActivation() {
		currentActivation++;
	}

	@Override
	public boolean isActivatedEntity() {
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
	public int compareTo(ActivatedEntity arg0) {
		if (getCurrentActivation() < arg0.getCurrentActivation())
			return -1;
		else if (getCurrentActivation() == arg0.getCurrentActivation())
			return 0;
		else
			return 1;
	}

}
