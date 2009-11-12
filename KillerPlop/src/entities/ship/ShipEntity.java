package entities.ship;

import Constants.Constants;
import controller.GameController;
import entities.EntityImpl;
import entities.movement.Movement;
import entities.sprites.Sprite;

public class ShipEntity extends EntityImpl implements Constants {
	
	public static int Vmove = 0, Hmove = 0;
	public static boolean isShooting;
	
	public ShipEntity(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	@Override
	public void calculateSpeed(GameController gameController) {
		this.setHorizontalMovement(gameController.getHorizontalMovement()+Hmove*SHIPSPEED);
		this.setVerticalMovement(gameController.getVerticalMovement()+Vmove*SHIPSPEED);
	}

	@Override
	public boolean isAlienEntity() {
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
