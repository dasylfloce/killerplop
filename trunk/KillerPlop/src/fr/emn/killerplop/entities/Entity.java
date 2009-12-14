package fr.emn.killerplop.entities;

import java.awt.Graphics2D;

import fr.emn.killerplop.controller.GameController;
import fr.emn.killerplop.entities.shapes.Shape;

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
	 * Tests if the entity is an alien entity
	 * 
	 * @return true if this is an alien entity.
	 */
	public abstract boolean isAlienEntity();

	/**
	 * Gives the x location of this entity
	 * 
	 * @return The x location of this entity
	 */
	public double getX();

	/**
	 * Gives the y location of this entity
	 * 
	 * @return The y location of this entity
	 */
	public double getY();

	/**
	 * Modifies the x location of this entity
	 * 
	 * @return The x location of this entity
	 */
	public void setX(double x);

	/**
	 * Modifies the y location of this entity
	 * 
	 * @return The y location of this entity
	 */
	public void setY(double y);

	/**
	 * Gives the width of the entity, given by its sprite representation.
	 * 
	 * @return the width of the entity.
	 */
	public int getWidth();

	/**
	 * Gives the width of the entity, given by its sprite representation.
	 * 
	 * @return the width of the entity.
	 */
	public int getHeight();

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
	 * Request that this entity move itself based on a the game controller
	 * 
	 * @param gameController
	 *            game controller
	 */
	public void move(GameController gameController);

	/**
	 * @return the shape of the entity.
	 */
	public Shape getShape();

	/**
	 * @return true if this entity has been destroyed, by hitting something or
	 *         anything else.
	 */
	public boolean isDestroyed();

	/**
	 * Reactivates this entity, which is used to determined if the entity is
	 * still in game or not. Has no effect if the entity is not destroyed.
	 * 
	 * @param desactivated
	 */
	public void reactivate();

	/**
	 * Check if this entity collides with another.
	 * 
	 * @param other
	 *            The other entity to check collision against
	 * @return True if the entities collide with each other
	 */
	public boolean collidesWith(Entity entity);

	/**
	 * Things to do if the entity is hit by anything.
	 */
	public void hit();

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
	public void draw(Graphics2D g, int offsetX, int offsetY);

}
