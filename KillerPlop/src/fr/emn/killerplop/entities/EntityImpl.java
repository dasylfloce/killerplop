package fr.emn.killerplop.entities;

import java.awt.Graphics2D;

import fr.emn.killerplop.controller.GameController;
import fr.emn.killerplop.entities.shapes.Shape;
import fr.emn.killerplop.exceptions.OutOfMapException;
import fr.emn.killerplop.sprites.Sprite;

/**
 * An entity represents any element that appears in the game. The entity is
 * responsible for resolving collisions and movement based on a set of
 * properties defined either by subclass or externally.
 * 
 * Note that doubles are used for positions. This may seem strange given that
 * pixels locations are integers. However, using double means that an entity can
 * move a partial pixel. It doesn't of course mean that they will be display
 * half way through a pixel but allows us not lose accuracy as we move.
 * 
 * @author Kevin Glass, Aurélien RAMBAUX
 */
public abstract class EntityImpl implements Entity {

	/** The current x location of this entity */
	protected double x;
	/** The current y location of this entity */
	protected double y;
	/** The current speed of this entity horizontally (pixels/sec) */
	protected double dx;
	/** The current speed of this entity vertically (pixels/sec) */
	protected double dy;

	/** The sprite that represents this entity */
	protected Sprite sprite;
	/** This flag is turned on if the entity has to be removed from the game. */
	protected boolean destroyed;

	/**
	 * Construct a entity based on a sprite image and a location
	 * 
	 * @param sprite
	 *            The sprite of the entity
	 * @param x
	 *            The initial x location of this entity
	 * @param y
	 *            The initial y location of this entity
	 */
	public EntityImpl(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		destroyed = false;
	}

	@Override
	public Shape getShape() {
		return sprite.getShape();
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void reactivate() {
		this.destroyed = false;
	}

	@Override
	public double getX() {
		return (int) x;
	}

	@Override
	public double getY() {
		return (int) y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setHorizontalMovement(double dx) {
		this.dx = dx;
	}

	@Override
	public void setVerticalMovement(double dy) {
		this.dy = dy;
	}

	@Override
	public double getHorizontalMovement() {
		return dx;
	}

	@Override
	public double getVerticalMovement() {
		return dy;
	}

	@Override
	public void move(GameController gameController) {
		// update speeds
		calculateSpeed(gameController);

		// update the location of the entity based on move speeds
		try {
			updatePosition(gameController);
		} catch (OutOfMapException e) {
			isOutOfMap(e);
		}

		sprite.update(gameController.getDelta());

		getShape().setPosition(x, y);
	}

	protected void updatePosition(GameController gc)
			throws OutOfMapException {
		double tempX = x + (gc.getDelta() * dx) / 1000;
		double tempY = y + (gc.getDelta() * dy) / 1000;

		// if going right;
		if (dx > 0) {
			if (!gc.getMap().isBlockedAt(tempX + getWidth(), y)
					&& !gc.getMap().isBlockedAt(tempX + getWidth(),
							y + getHeight()))
				x = tempX;
		}

		// if going left;
		if (dx < 0)
			if (!gc.getMap().isBlockedAt(tempX, tempY)
					&& !gc.getMap().isBlockedAt(tempX,
							tempY + getHeight()))
				x = tempX;

		// if going down;
		if (dy > 0)
			if (!gc.getMap().isBlockedAt(x, tempY + getHeight())
					&& !gc.getMap().isBlockedAt(x + getWidth(),
							tempY + getHeight()))
				y = tempY;

		// if going up;
		if (dy < 0)
			if (!gc.getMap().isBlockedAt(x, tempY)
					&& !gc.getMap().isBlockedAt(x + getWidth(),
							tempY))
				y = tempY;
	}

	/**
	 * Calculate the speed of the entity, used to move it.
	 * 
	 * @param gameController
	 *            controller
	 */
	public abstract void calculateSpeed(GameController gameController);

	/**
	 * Called when the entity goes out of the map.
	 */
	public abstract void isOutOfMap(OutOfMapException e);

	@Override
	public boolean collidesWith(Entity other) {
//		if (getShape().intersectsWith(other.getShape()))
//			System.out.println(getShape().toString() + "\n"
//					+ other.getShape().toString());
		//System.exit(0);
		return getShape().intersectsWith(other.getShape());
	}

	@Override
	public int getHeight() {
		return sprite.getHeight();
	}

	@Override
	public int getWidth() {
		return sprite.getWidth();
	}

	@Override
	public void draw(Graphics2D g, int offsetX, int offsetY) {
		sprite.draw(g, (int) x - offsetX, (int) y - offsetY);
	}

}