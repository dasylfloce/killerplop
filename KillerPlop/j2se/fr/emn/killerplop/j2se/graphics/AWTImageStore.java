package fr.emn.killerplop.j2se.graphics;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;

import fr.emn.killerplop.graphics.context.GraphicUtilities;

/**
 * A resource manager for image in the game. Its often quite important how and
 * where you get your game resources from. In most cases it makes sense to have
 * a central resource loader that goes away, gets your resources and caches them
 * for future use.
 * <p>
 * [singleton]
 * <p>
 * 
 * @author Aur�lien RAMBAUX
 */
public class AWTImageStore {

	/** The single instance of this class */
	private static AWTImageStore single = new AWTImageStore();

	/**
	 * Get the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static AWTImageStore get() {
		return single;
	}
	public static void load(String ref) {
		single.loadImage(ref);
	}
	
	public void loadImage(String ref) {
		if (images.get(ref) != null) {
			return;
		}
		
		String absoluteRef = GraphicUtilities.imgPackage + ref;
		System.out.println("Loading "+absoluteRef);

		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;

		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.
			URL url = this.getClass().getClassLoader().getResource(
					absoluteRef);

			if (url == null) {
				fail("Can't find ref: " + absoluteRef);
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: " + absoluteRef);
		}
		if (sourceImage == null) {
			fail("Error loading: " + absoluteRef);
		}

		// create an accelerated image of the right size
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		BufferedImage image = gc.createCompatibleImage(sourceImage.getWidth(),
				sourceImage.getHeight(), Transparency.BITMASK);

		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		images.put(ref, image);
	}

	/**
	 * Retrieve a image from the store
	 * 
	 * @param ref
	 *            The reference to the image
	 * @return An accelerate image of the request reference
	 */
	public static BufferedImage get(String ref) {
		return get().getImage(ref);
	}

	/**
	 * Converts the type of all images.<br>
	 * See BufferedImage types<br>
	 * 
	 * @param imageType
	 *            Type to convert to
	 */
	public static void convertAllImages(int imageType) {
		single.convert(imageType);
	}

	/** The cached image map, from reference to image instance */
	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	private BufferedImage getImage(String ref) {
		if (images.get(ref) != null) {
			return images.get(ref);
		}
		fail("Not loaded ref: " + ref);
		return null;
	}

	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message
	 *            The message to display on failure
	 */
	private void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}

	private void convert(int imageType) {
		for (Iterator<String> it = images.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			BufferedImage image = getImage(key);
			if (image.getType() != imageType) {
				// conversion
				BufferedImage i = new BufferedImage(image.getWidth(), image
						.getHeight(), imageType); // new format
				i.setAccelerationPriority(1.0f);
				Graphics g = i.createGraphics();
				g.drawImage(i, 0, 0, null);
				g.dispose();

				images.put(key, i);
				image = i;
			}
		}
	}
}