package fr.emn.killerplop.game.sprites;

import fr.emn.killerplop.game.entities.shapes.Shape;

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
	 * between flipping images (in ms).
	 * A default shape is set, as a ShapeRectangle bounding the image size.
	 * 
	 * @param images
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping
	 */
	public AnimatedSprite(String[] images, long duration) {
		this(images, null);
		durations = new long[images.length];
		for (int i = 0; i < images.length; i++)
			durations[i] = duration;
	}

	/**
	 * Create a new sprite based on an array of images, with different delays
	 * between flipping images for each image (in ms).
	 * A default shape is set, as a ShapeRectangle bounding the image size.
	 * 
	 * @param images
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping, for each different image
	 */
	public AnimatedSprite(String[] images, long[] durations) {
		super(images[0]);
		this.imagesRef = images;
		this.durations = durations;
	}

	/**
	 * Create a new sprite based on an array of images, with constant delay
	 * between flipping images (in ms).
	 * The shape is used for collision area.
	 * 
	 * @param images
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping
	 * @param shape
	 *            Shape used for collision
	 */
	public AnimatedSprite(String[] images, long duration, Shape shape) {
		this(images, null, shape);
		durations = new long[images.length];
		for (int i = 0; i < images.length; i++)
			durations[i] = duration;
	}

	/**
	 * Create a new sprite based on an array of images, with different delays
	 * between flipping images for each image (in ms).
	 * 
	 * @param images
	 *            The image representing this sprite
	 * @param duration
	 *            Time between each flipping, for each different image
	 * @param shape
	 *            Shape used for collision
	 */
	public AnimatedSprite(String[] images, long[] durations, Shape shape) {
		super(images[0], shape);
		this.imagesRef = images;
		this.durations = durations;
	}

	public void update(long delta) {
		timeElapsed += delta;
		if (timeElapsed >= durations[imageIndex]) {
			timeElapsed -= durations[imageIndex];
			imageIndex = (imageIndex + 1) % imagesRef.length;
			imageRef = imagesRef[imageIndex];
		}
	}

}