package fr.emn.killerplop.graphics.sprites;


import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.context.GraphicContext;

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
	 * @return the shape of the sprite
	 */
	public Shape getShape();

	/**
	 * Draw the sprite onto the graphics context provided
	 * @param graphicContext graphicContext
	 * 			Graphic context to draw on.
	 * @param x
	 *            The x location at which to draw the sprite
	 * @param y
	 *            The y location at which to draw the sprite
	 */
	public void draw(GraphicContext graphicContext, int x, int y);

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