package entities.movement.basics;

import controller.GameController;
import entities.Entity;
import entities.movement.Movement;

public class DynamicMovement implements Movement {

	protected ControlledMovement mvt;
	
	public DynamicMovement(ControlledMovement mvt) {
		this.mvt = mvt;
	}


	@Override
	public void setMovement(Entity entity, GameController gameController) {
		mvt.calculateSpeed(entity, gameController);

		entity.setHorizontalMovement(mvt.getVx()+gameController.getHorizontalMovement());
		entity.setVerticalMovement(mvt.getVy()+gameController.getVerticalMovement());
	}

}
