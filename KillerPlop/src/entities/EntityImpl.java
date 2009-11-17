package entities;

import java.awt.Graphics2D;

import resources.sprites.Sprite;
import controller.GameController;
import entities.shapes.Shape;
import entities.shapes.ShapeRectangle;
import exceptions.OutOfMapException;

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
	/** The sprite that represents this entity */
	protected Sprite sprite;
	/** The current speed of this entity horizontally (pixels/sec) */
	protected double dx;
	/** The current speed of this entity vertically (pixels/sec) */
	protected double dy;

	/** Shape of the entity */
	protected Shape shape;
	/** This flag is turned on if the entity has to be removed from the game. */
	protected boolean destroyed;

	/**
	 * Construct a entity based on a sprite image, a location and a shape used
	 * for collision.
	 * 
	 * @param sprite
	 *            The sprite of the entity
	 * @param x
	 *            The initial x location of this entity
	 * @param y
	 *            The initial y location of this entity
	 * @param shape
	 *            The shape used for collision
	 */
	public EntityImpl(Sprite sprite, int x, int y, Shape shape) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.shape = shape;
		destroyed = false;
	}

	/**
	 * Construct a entity based on a sprite image and a location. The shape is
	 * given by default as a rectangle, which size is the sprite size.
	 * 
	 * @param sprite
	 *            The sprite of the entity
	 * @param x
	 *            The initial x location of this entity
	 * @param y
	 *            The initial y location of this entity
	 * @param shape
	 *            The shape used for collision
	 */
	public EntityImpl(Sprite sprite, int x, int y) {
		this(sprite, x, y, null);
		shape = new ShapeRectangle(getWidth(), getHeight());
	}

	@Override
	public Shape getShape() {
		return shape;
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
			isOutOfMap();
		}

		sprite.update(gameController.getDelta());

		shape.setPosition(x, y);
	}

	protected void updatePosition(GameController gameController)
			throws OutOfMapException {
		double tempX = x + (gameController.getDelta() * dx) / 1000;
		double tempY = y + (gameController.getDelta() * dy) / 1000;

		// if going right;
		if (dx > 0) {
			if (!gameController.getMap().isBlockedAt(tempX + getWidth(), y)
					&& !gameController.getMap().isBlockedAt(tempX + getWidth(),
							y + getHeight()))
				x = tempX;
		}

		// if going left;
		if (dx < 0)
			if (!gameController.getMap().isBlockedAt(tempX, tempY)
					&& !gameController.getMap().isBlockedAt(tempX,
							tempY + getHeight()))
				x = tempX;

		// if going down;
		if (dy > 0)
			if (!gameController.getMap().isBlockedAt(x, tempY + getHeight())
					&& !gameController.getMap().isBlockedAt(x + getWidth(),
							tempY + getHeight()))
				y = tempY;

		// if going up;
		if (dy < 0)
			if (!gameController.getMap().isBlockedAt(x, tempY)
					&& !gameController.getMap().isBlockedAt(x + getWidth(),
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
	public abstract void isOutOfMap();

	@Override
	public boolean collidesWith(Entity other) {
		if (shape.intersectsWith(other.getShape()))
			System.out.println(shape.toString() + "\n"
					+ other.getShape().toString());
		//System.exit(0);
		return shape.intersectsWith(other.getShape());
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