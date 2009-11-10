package entities.movement;

import controller.GameController;
import entities.Entity;

public class ShipMovement implements Movement {

	/**
	 * 
	 * @param amplitude
	 *            Amplitude in pxl
	 * @param period
	 *            Period in sec
	 * @param vx
	 */
	public ShipMovement() {
		super();
	}

	@Override
	public void setMovement(Entity entity, GameController gameController) {
		entity.setHorizontalMovement(gameController.getHorizontalMovement());
		entity.setVerticalMovement(gameController.getVerticalMovement());
	}

}
