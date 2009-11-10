package entities;

import controller.GameController;
import entities.movement.Movement;
import entities.sprites.Sprite;

public class ShipEntity extends EntityImpl {
	
	protected Movement movement;

	public ShipEntity(Sprite sprite, int x, int y, Movement movement) {
		super(sprite, x, y);
		this.movement = movement;
	}

	@Override
	public void calculateSpeed(GameController gameController) {
		movement.setMovement(this, gameController);
	}

	@Override
	public boolean isActivatedEntity() {
		return false;
	}

	@Override
	public boolean isShipEntity() {
		return true;
	}

	@Override
	public boolean isShotEntity() {
		return false;
	}

}
