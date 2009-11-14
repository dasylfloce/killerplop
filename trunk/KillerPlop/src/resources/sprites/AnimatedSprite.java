package resources.sprites;

import java.awt.Image;

/**
 * A sprite to be displayed on the screen. This sprite can be composed by
 * several different images.
 * 
 * @author Aurélien RAMBAUX
 */
public class AnimatedSprite extends SimpleSprite {

	/** The array of images to be drawn for this sprite */
	protected Image[] images;
	/** The duration of each images */
	protected long[] durations;
	/** The index of the actual image drawn */
	protected int imageIndex;
	/** Current time elapsed since the last image change */
	protected long timeElapsed;
	
	
	/**
	 * Create a new sprite based on an array of images, with constant temporisation
	 * between flipping images (in ms).
	 * 
	 * @param images
	 *            The image that is this sprite
	 */
	public AnimatedSprite(Image[] images, long duration) {
		super(images[0]);
		this.images = images;
		durations = new long[images.length];
		for(int i=0; i<images.length; i++)
			durations[i] = duration;
	}
	/**
	 * Create a new sprite based on an array of images, with temporisation
	 * between flipping images (in ms).
	 * 
	 * @param images
	 *            The image that is this sprite
	 */
	public AnimatedSprite(Image[] images, long[] durations) {
		super(images[0]);
		this.images = images;
		this.durations = durations;
	}

	public void update(long delta) {
		timeElapsed += delta;
		if (timeElapsed >= durations[imageIndex]) {
			timeElapsed -= durations[imageIndex];
			imageIndex = (imageIndex+1)%images.length;
			image = images[imageIndex];
		}
	}

}