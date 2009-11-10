package entities;

import java.awt.Graphics;

import controller.GameController;

/**
 * Represents an entity of the game. Super class of all entities (like ship,
 * alien, shot, etc...)
 * 
 * Position is absolute in the map, speed is relative to the view.
 * 
 * @author Aurélien RAMBAUX
 * 
 */
public interface Entity {

	/**
	 * Tests if the entity is a ship entity
	 * 
	 * @return true if this is a ship entity.
	 */
	public abstract boolean isShipEntity();

	/**
	 * Tests if the entity is a shot entity
	 * 
	 * @return true if this is a shot entity.
	 */
	public abstract boolean isShotEntity();

	/**
	 * Tests if the entity is an activated entity
	 * 
	 * @return true if this is an activated entity.
	 */
	public abstract boolean isActivatedEntity();

	/**
	 * Get the x location of this entity
	 * 
	 * @return The x location of this entity
	 */
	public double getX();

	/**
	 * Get the y location of this entity
	 * 
	 * @return The y location of this entity
	 */
	public double getY();

	/**
	 * Get the x location of this entity
	 * 
	 * @return The x location of this entity
	 */
	public void setX(double x);

	/**
	 * Get the y location of this entity
	 * 
	 * @return The y location of this entity
	 */
	public void setY(double y);

	/**
	 * Set the horizontal speed of this entity
	 * 
	 * @param dx
	 *            The horizontal speed of this entity (pixels/sec)
	 */
	public void setHorizontalMovement(double dx);

	/**
	 * Set the vertical speed of this entity
	 * 
	 * @param dx
	 *            The vertical speed of this entity (pixels/sec)
	 */
	public void setVerticalMovement(double dy);

	/**
	 * Get the horizontal speed of this entity
	 * 
	 * @return The horizontal speed of this entity (pixels/sec)
	 */
	public double getHorizontalMovement();

	/**
	 * Get the vertical speed of this entity
	 * 
	 * @return The vertical speed of this entity (pixels/sec)
	 */
	public double getVerticalMovement();

	/**
	 * Calculate the speed of the entity, used to move it.
	 * @param gameController controller
	 */
	public void calculateSpeed(GameController gameController);

	/**
	 * Request that this entity move itself based on a the game controller
	 * 
	 * @param gameController
	 *            game controller
	 */
	public void update(GameController gameController);

	/**
	 * Check if this entity collides with another.
	 * 
	 * @param other
	 *            The other entity to check collision against
	 * @return True if the entities collide with each other
	 */
	public boolean collidesWith(Entity entity);

	/**
	 * Draw this entity on the graphics context provided. See EntityDisplayer
	 * for "offsets"
	 * 
	 * @param g
	 *            The graphics context on which to draw
	 * @param offsetX
	 *            Horizontal offset to draw the entity
	 * @param offsetX
	 *            Vertical offset to draw the entity
	 */
	public void draw(Graphics g, int offsetX, int offsetY);

}
