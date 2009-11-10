/**
 * Tile.java
 *
 * Created on 19 mars 2006, 18:31
 * 
 */

package map.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Les tuiles de la carte<br>
 * 
 * @author Guillaume Bouchon
 */
public interface Tile {
	
	/**
	 * Donne la largeur de la tuile
	 * 
	 * @return la largeur
	 */
	public int getWidth();

	/**
	 * Donne la hauteur de la tuile
	 * 
	 * @return la hauteur
	 */
	public int getHeight();
	
	/**
	 * Donne l'image en cours
	 * 
	 * @return l'image en cours
	 **/
	public BufferedImage getImage();
	
	/**
	 * @return le nom de la tuile (utilis� pour �viter les doublons dans le warehouse)
	 */
	public String getName();

	/**
	 * Update la tuile
	 * 
	 * @param delta temps �coul� (en ms) depuis la derni�re update
	 */
	public void update(long delta);

	/**
	 * Affiche la tuile
	 * 
	 * @param g
	 *            le Graphics o� afficher
	 * @param x
	 *            la position x en pixels dans le Graphics
	 * @param y
	 *            la position y en pixels dans le Graphics
	 */
	public void draw(Graphics2D g, int x, int y);

	/**
	 * Convertion des images en autre format (pour des problemes d'optmisation
	 * d'affichage)<br>
	 * Voir les types pour BufferedImage<br>
	 * 
	 * @param image_type
	 *            le type d�sir� pour l'image, voir les numeros des types dans
	 *            BufferedImage
	 */
	public void convert(int image_type);
}
