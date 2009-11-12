package entities;

import java.awt.Graphics;

import controller.GameController;
import entities.sprites.Sprite;

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

	/**
	 * Construct a entity based on a sprite image and a location.
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
	public void update(GameController gameController) {
		// update speeds
		calculateSpeed(gameController);
		
		// update the location of the entity based on move speeds
		x += (gameController.getDelta()* dx) / 1000;
		y += (gameController.getDelta() * dy) / 1000;
	}
	
	/**
	 * Calculate the speed of the entity, used to move it.
	 * @param gameController controller
	 */
	public abstract void calculateSpeed(GameController gameController);

	@Override
	public boolean collidesWith(Entity other) {
		return false;
	}

	@Override
	public void draw(Graphics g, int offsetX, int offsetY) {
		sprite.draw(g, (int) x - offsetX, (int) y - offsetY);
	}

}