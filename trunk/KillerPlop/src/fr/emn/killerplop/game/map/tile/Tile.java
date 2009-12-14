/**
 * Tile.java
 *
 * Created on 19 mars 2006, 18:31
 * 
 */

package fr.emn.killerplop.game.map.tile;

import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.context.GraphicContext;

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
	 * @return le nom de la tuile (utilisé pour éviter les doublons dans le
	 *         warehouse)
	 */
	public String getName();

	/**
	 * Donne la shape de la tile. Si la tile est non blocante, return null.
	 * 
	 * @return la shape de la tile
	 */
	public Shape getShape();

	/**
	 * Teste si la tuile est blocante à la position (x, y). La position (0, 0)
	 * est le point supérieur gauche de la tile.
	 * 
	 * @param x
	 *            abscisse
	 * @param y
	 *            ordonnée
	 * @return true si la tuile est blocante.
	 */
	public boolean isBlockingAt(double x, double y);

	/**
	 * Update la tuile
	 * 
	 * @param delta
	 *            temps écoulé (en ms) depuis la dernière update
	 */
	public void update(long delta);

	/**
	 * Affiche la tuile
	 * 
	 * @param graphicContext
	 *            le Graphics où afficher
	 * @param x
	 *            la position x en pixels dans le Graphics
	 * @param y
	 *            la position y en pixels dans le Graphics
	 */
	public void draw(GraphicContext graphicContext, int x, int y);

}
