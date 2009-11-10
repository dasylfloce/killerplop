package entities;

import controller.GameController;
import entities.sprites.Sprite;

public class ShipEntity extends EntityImpl {

	public ShipEntity(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calculateSpeed(GameController gameController) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isActivatedEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShipEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShotEntity() {
		// TODO Auto-generated method stub
		return false;
	}

}
