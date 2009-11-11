package map;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Utilities {

	/**
	 * Charge une image
	 * 
	 * @param i
	 *            le chemin vers le fichier image
	 * @return l'image chargée ou null si erreur
	 */
	public static BufferedImage loadImage(String i) {
		try {
			BufferedImage img = ImageIO.read(new File(i));
			if (img != null)
				img.setAccelerationPriority(1.0f);
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

}
