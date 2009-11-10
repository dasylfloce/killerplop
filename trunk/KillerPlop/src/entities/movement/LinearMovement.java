package entities.movement;

import controller.GameController;
import entities.Entity;

public class LinearMovement implements Movement {

	/**
	 * 
	 * @param amplitude
	 *            Amplitude in pxl
	 * @param period
	 *            Period in sec
	 * @param vx
	 */
	public LinearMovement() {
		super();
	}

	@Override
	public void setMovement(Entity entity, GameController gameController) {
		entity.setHorizontalMovement(gameController.getHorizontalMovement());
		entity.setVerticalMovement(gameController.getVerticalMovement());
	}

}
