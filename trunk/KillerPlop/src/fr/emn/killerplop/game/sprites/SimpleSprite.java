package fr.emn.killerplop.game.sprites;

import fr.emn.killerplop.game.entities.shapes.RectShape;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.GraphicContext;
import fr.emn.killerplop.graphics.GraphicUtilities;

/**
 * A sprite to be displayed on the screen. This sprite is composed by only one
 * image.
 * 
 * @author Kevin Glass
 */
public class SimpleSprite implements Sprite {

	/** The image to be drawn for this sprite */
	protected String imageRef;
	/** The shape of the sprite */
	protected Shape shape;

	/**
	 * Create a new sprite based on an image
	 * 
	 * @param imageRef
	 *            The image that is this sprite
	 */
	public SimpleSprite(String imageRef, Shape shape) {
		this.imageRef = imageRef;
		this.shape = shape;
	}

	/**
	 * Create a new sprite based on an image with a default shape. The shape is
	 * a ShapeRectangle based on the size of the image.
	 * 
	 * @param imageRef
	 *            The image that is this sprite
	 */
	public SimpleSprite(String imageRef) {
		this.imageRef = imageRef;
		this.shape = new RectShape(GraphicUtilities.get().getWidthOf(imageRef),
				GraphicUtilities.get().getHeightOf(imageRef));
	}

	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param x
	 *            The x location at which to draw the sprite
	 * @param y
	 *            The y location at which to draw the sprite
	 */
	public void draw(GraphicContext graphicContext, int x, int y) {
		graphicContext.draw(imageRef, x, y);
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