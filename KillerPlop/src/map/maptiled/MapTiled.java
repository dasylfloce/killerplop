/**
 * MapTiled.java
 *
 * Created on 19 mars 2006, 18:34
 *
 * 
 *
 */

package map.maptiled;

import java.awt.Graphics2D;

import entities.shapes.Shape;
import fr.emn.killerplop.exceptions.OutOfMapException;

/**
 * La carte : c'est un tableau a deux dimensions. Chaque cellule contient l'id
 * de la tuile utilisée<br>
 */
public interface MapTiled {

	/**
	 * Donne le nombre de tuile dans la direction horizontale.
	 * 
	 * @return la largeur en tuile de la map
	 */
	public int getMapWidth();

	/**
	 * Donne le nombre de tuile dans la direction verticale.
	 * 
	 * @return la hauteur en tuile de la map
	 */
	public int getMapHeight();

	/**
	 * Donne la largeur des tuiles
	 * 
	 * @return la largeur en pixels des tuiles
	 */
	public int getTileWidth();

	/**
	 * Donne la hauteur des tuiles
	 * 
	 * @return la heuteur en pixels des tuiles
	 */
	public int getTileHeight();

	/**
	 * Teste l'état de la map à la position (x, y), blocante ou non.
	 * 
	 * @param x
	 *            position x dans la carte
	 * @param y
	 *            position y dans la carte
	 * @return true si la map est blocante.
	 * @exception OutOfMapException
	 *                si la position est hors de la map
	 */
	public boolean isBlockedAt(double x, double y) throws OutOfMapException;

	/**
	 * Retourne la shape de la tile presente à la position (x, y).
	 * 
	 * @param x
	 *            position x dans la carte
	 * @param y
	 *            position y dans la carte
	 * @return la shape de la tile concernée.
	 * @exception OutOfMapException
	 *                si la position est hors de la map
	 */
	public Shape getShapeAt(double x, double y) throws OutOfMapException;

	/**
	 * Update toutes les tuiles de la carte
	 * 
	 * @param delta
	 *            Temps ecoulé (en ms) depuis la dernière update.
	 */
	public void updateTiles(long delta);

	/**
	 * Dessine la position (x; y) de la map sur le graph. La taille de la vue
	 * est paramétrable.
	 * 
	 * @param g
	 *            graph sur lequel dessiner
	 * @param x
	 *            position horizontale dans la map
	 * @param y
	 *            position verticale dans la map
	 * @param viewWidth
	 *            largeur de la vue
	 * @param viewHeight
	 *            hauteur de la vue
	 * @exception OutOfMapException
	 *                si la position est hors de la map
	 */
	void render(Graphics2D g, double x, double y, int viewWidth, int viewHeight)
			throws OutOfMapException;
}
