package fr.emn.killerplop.game.entities.shots;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.entities.EntityImpl;
import fr.emn.killerplop.game.exceptions.OutOfMapException;
import fr.emn.killerplop.game.sprites.Sprite;

public class ShotEntity extends EntityImpl implements Constants {

	public ShotEntity(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	@Override
	public void calculateSpeed(GameController gameController) {
		// todo
		dx = SHOTSPEED;
		this.setHorizontalMovement(gameController.getHorizontalMovement() + dx);
		this.setVerticalMovement(gameController.getVerticalMovement() + dy);
	}

	/**
	 * Override method from EntityImpl. It tests if the new position of the
	 * center of the shot is not blocked. If true, the shot marked as
	 * desactivated, otherwise, its position is updated.
	 * 
	 * @throws OutOfMapException
	 * @throws OutOfMapException
	 *             if the shot goes out of the map
	 */
	protected void updatePosition(GameController gc) throws OutOfMapException {
		double tempX = x + (gc.getDelta() * dx) / 1000;
		double tempY = y + (gc.getDelta() * dy) / 1000;

		double middleX = tempX + getWidth() / 2;
		double middleY = y + getHeight() / 2;

		if (middleX < gc.getX() || middleX > gc.getX() + gc.getViewWidth()
				|| middleY < gc.getY()
				|| middleY > gc.getY() + gc.getViewHeight()) {
			// Shot is out of view -> desactivate it
			destroyed = true;

		} else if (gc.getMap().isBlockedAt(middleX, middleY)) {
			// Shot shoots a blocked part of the map -> desactivate it
			destroyed = true;

		} else {
			// Move it
			x = tempX;
			y = tempY;
		}
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

	@Override
	public void isOutOfMap(OutOfMapException e) {
		e.printStackTrace();
		System.err.println("Shot out of the map !? (x:" + x + "; y:" + y);
		System.exit(0);
	}

	@Override
	public void hit() {
		destroyed = true;
	}

}
