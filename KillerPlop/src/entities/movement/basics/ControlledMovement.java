package entities.movement.basics;

import controller.GameController;
import entities.Entity;
import entities.movement.Movement;

public abstract class ControlledMovement implements Movement {

	private double vx;
	private double vy;

	protected ControlledMovement() {
	}

	protected double getVx() {
		return vx;
	}

	protected void setVx(double dx) {
		this.vx = dx;
	}

	protected double getVy() {
		return vy;
	}

	protected void setVy(double dy) {
		this.vy = dy;
	}

	public void setMovement(Entity entity, GameController gameController) {
		calculateSpeed(entity, gameController);

		entity.setHorizontalMovement(vx);
		entity.setVerticalMovement(vy);
	}
	
	/**
	 * Method called each time setMovement() is called. This method sets values
	 * for vx and vy.
	 * 
	 * @param entity
	 *            Entity to configure.
	 * @param gameController
	 *            Game Controller.
	 */
	protected abstract void calculateSpeed(Entity entity,
			GameController gameController);

}
