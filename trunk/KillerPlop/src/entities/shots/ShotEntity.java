package entities.shots;

import Constants.Constants;
import controller.GameController;
import entities.EntityImpl;
import entities.movement.Movement;
import entities.sprites.Sprite;

public class ShotEntity extends EntityImpl implements Constants{
	
	private double speed;

	public ShotEntity(Sprite sprite, int x, int y, double speed) {
		super(sprite, x, y);
		this.speed = speed;
	}
	
	@Override
	public void calculateSpeed(GameController gameController) {
		//todo
		dx = SHOTSPEED;
		this.setHorizontalMovement(gameController.getHorizontalMovement()+dx);
		this.setVerticalMovement(gameController.getVerticalMovement()+dy);
	}

	@Override
	public boolean isAlienEntity() {
		return false;
	}

	@Override
	public boolean isShipEntity() {
		return false;
	}

	@Override
	public boolean isShotEntity() {
		return true;
	}
	
	
}
