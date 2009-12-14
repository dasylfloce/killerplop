package fr.emn.killerplop.sprites;

import java.awt.Graphics2D;

import fr.emn.killerplop.entities.shapes.Shape;

/**
 * A sprite to be displayed on the screen. Note that a sprite contains no state
 * information, i.e. its just the image and not the location. This allows us to
 * use a single sprite in lots of different places without having to store
 * multiple copies of the image.
 * 
 * @author Kevin Glass
 */
public interface Sprite {

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth();

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight();

	/**
	 * @return the shape of the sprite
	 */
	public Shape getShape();

	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param g
	 *            The graphics context on which to draw the sprite
	 * @param x
	 *            The x location at which to draw the sprite
	 * @param y
	 *            The y location at which to draw the sprite
	 */
	public void draw(Graphics2D g, int x, int y);

	/**
	 * Update the sprite.
	 * 
	 * @param delta
	 *            Time elapsed since last update.
	 */
	public void update(long delta);

	/**
	 * Set the shape of the sprite
	 * @param shape New shape
	 */
	public void setShape(Shape shape);

}