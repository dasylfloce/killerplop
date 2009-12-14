/**
 * TileWareHouse.java
 *
 * Created on 19 mars 2006, 18:30
 *
 * 
 */
package fr.emn.killerplop.game.map.tile;

/**
 * 
 * Entrepot des tuiles : c'est la que l'on stocke les Tile<br>
 * Chaque Tile ajoutée dans l'entrepot doit etre unique (id unique) et DE MEME
 * TAILLE <br>
 * Les Tiles dans l'entrepot sont classées par ordre croissant de leur id<br>
 * 
 * @author Guillaume Bouchon
 */
public interface TileWareHouse {

	/**
	 * Donne le nb de tuiles
	 * 
	 * @return le nombre de tuiles dans l'entrepot
	 */
	public int getNbTiles();

	/**
	 * Donne la largeur des tuiles
	 * 
	 * @return la largeur en pixels de toutes les tuiles (taille unique)
	 **/
	public int getTileWidth();

	/**
	 * Donne la hauteur des tuiles
	 * 
	 * @return la hauteur en pixels de toutes les tuiles (taille unique)
	 */
	public int getTileHeight();
	
	/**
	 * @return une tile sans texture
	 */
	public Tile getNullTile();

	/**
	 * Teste si la tile fait déjà partie du stock
	 * 
	 * @param tile
	 *            tile à ajouter
	 * @return true si elle est déjà en stock
	 */
	public boolean contains(Tile tile);

	/**
	 * Update les tuiles de la warehouse
	 * 
	 * @param delta
	 *            Temps écoulé (en ms) depuis la dernière update
	 */
	public void updateTiles(long delta);

	/**
	 * Ajoute une tuile, si elle n'est pas déjà en stock et non null <br>
	 * Renvoi -1 en cas d'erreur, et l'index en cas de succès<br>
	 * NOTE : toutes les tuiles doivent avoir la meme taille, si une tuile ne
	 * respecte pas la taille, elle n'est pas ajouté
	 * 
	 * @param tile
	 *            la tuile a ajouter
	 * @return -1 si erreur (mauvaise dimension, null etc) sinon l'index de la
	 *         tuile insérée
	 */
	public int addTile(Tile tile);

	/**
	 * Clone l'objet
	 * @return Un clone de l'objet
	 */
	public TileWareHouse clone();

}
