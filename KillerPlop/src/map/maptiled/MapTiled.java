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

import map.tile.*;

/**
 * La carte : c'est un tableau a deux dimensions. Chaque cellule contient l'id
 * de la tuile utilisée<br>
 */
public interface MapTiled {

	/**
	 * Donne la largeur
	 * 
	 * @return la largeur en tuile de la map
	 */
	public int getMapWidth();

	/**
	 * Donne la hauteur
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
	 * Donne la tuile a l'endroit indiqué
	 * 
	 * @param x
	 *            position x dans la carte
	 * @param y
	 *            position y dans la carte
	 * @return l'objet Tile a cet endroit ou null si aucune
	 * @exception ArrayIndexOutOfBoundsException
	 *                si hors de la map
	 */
	public Tile getTileAt(int x, int y) throws ArrayIndexOutOfBoundsException;

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
	 * @exception ArrayIndexOutOfBoundsException
	 *                si hors de la map
	 */
	void render(Graphics2D g, double x, double y, int viewWidth, int viewHeight)
			throws ArrayIndexOutOfBoundsException;
}
