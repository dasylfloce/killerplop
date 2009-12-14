package fr.emn.killerplop.graphics.sprites;

import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.context.GraphicUtilities;

/**
 * A sprite to be displayed on the screen. This sprite can be composed by
 * several different images.
 * 
 * @author Aurélien RAMBAUX
 */
public class AnimatedSprite extends SimpleSprite {

	/** The array of images to be drawn for this sprite */
	protected String[] imagesRef;
	/** The duration of each images */
	protected long[] durations;
	/** The index of the actual image drawn */
	protected int imageIndex;
	/** Current time elapsed since the last image change */
	protected long timeElapsed;

	/**
	 * Create a new sprite based on an array of images, with constant delay
	 * between flipping images (in ms). A default shape is set, as a
	 * ShapeRectangle bounding the image size.
	 * 
	 * @param imagesRef
	 *            The images representing this sprite
	 * @param duration
	 *            Time between each flipping
	 */
	public AnimatedSprite(String[] imagesRef, long duration) {
		this(imagesRef, 0, GraphicUtilities.get().getImageShape(
				imagesRef[0]));
	}

	/**
	 * Create a new sprite based on an array of images, with different delays
	 * between flipping images for each image (in ms). A default shape is set,
	 * as a ShapeRectangle bounding the image size.
	 * 
	 * @param imagesRef
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping, for each different image
	 */
	public AnimatedSprite(String[] imagesRef, long[] durations) {
		this(imagesRef, durations, GraphicUtilities.get().getImageShape(
				imagesRef[0]));
	}

	/**
	 * Create a new sprite based on an array of images, with constant delay
	 * between flipping images (in ms). The shape is used for collision area.
	 * 
	 * @param imagesRef
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping
	 * @param shape
	 *            Shape used for collision
	 */
	public AnimatedSprite(String[] imagesRef, long duration, Shape shape) {
		this(imagesRef, null, shape);
		durations = new long[imagesRef.length];
		for (int i = 0; i < durations.length; i++)
			durations[i] = duration;
	}

	/**
	 * Create a new sprite based on an array of images, with different delays
	 * between flipping images for each image (in ms).
	 * 
	 * @param imagesRef
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping, for each different image
	 * @param shape
	 *            Shape used for collision
	 */
	public AnimatedSprite(String[] imagesRef, long[] durations, Shape shape) {
		super(imagesRef[0], shape);
		this.imagesRef = imagesRef;
		this.durations = durations;
	}

	@Override
	public void update(long delta) {
		timeElapsed += delta;
		if (timeElapsed >= durations[imageIndex]) {
			timeElapsed -= durations[imageIndex];
			imageIndex = (imageIndex + 1) % imagesRef.length;
			imageRef = imagesRef[imageIndex];
		}
	}

}