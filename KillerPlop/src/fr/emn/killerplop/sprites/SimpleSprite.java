package fr.emn.killerplop.sprites;

import java.awt.Graphics2D;
import java.awt.Image;

import fr.emn.killerplop.entities.shapes.RectShape;
import fr.emn.killerplop.entities.shapes.Shape;

/**
 * A sprite to be displayed on the screen. This sprite is composed by only one
 * image.
 * 
 * @author Kevin Glass
 */
public class SimpleSprite implements Sprite {

	/** The image to be drawn for this sprite */
	protected Image image;
	/** The shape of the sprite */
	protected Shape shape;

	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image
	 *            The image that is this sprite
	 */
	public SimpleSprite(Image image, Shape shape) {
		this.image = image;
		this.shape = shape;
	}

	/**
	 * Create a new sprite based on an image with a default shape. The shape is
	 * a ShapeRectangle based on the size of the image.
	 * 
	 * @param image
	 *            The image that is this sprite
	 */
	public SimpleSprite(Image image) {
		this.image = image;
		this.shape = new RectShape(getWidth(), getHeight());
	}

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}

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
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	@Override
	public void update(long delta) {
		// Nothing to do.
	}

	@Override
	public Shape getShape() {
		return shape;
	}
	
	@Override
	public void setShape(Shape shape) {
		this.shape = shape;
	}
}