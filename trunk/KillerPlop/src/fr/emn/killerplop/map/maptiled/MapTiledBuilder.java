package fr.emn.killerplop.map.maptiled;

import java.awt.Image;

import fr.emn.killerplop.map.tile.Tile;
import fr.emn.killerplop.map.tile.TileWareHouse;
import fr.emn.killerplop.map.tile.TileWareHouseImpl;


public class MapTiledBuilder extends MapTiledImpl {

	/**
	 * Constructeur de map à tuiles
	 * 
	 * @param mapWidth
	 *            Nombre de tuiles à l'horizontale
	 * @param mapHeight
	 *            Nombre de tuiles à la verticale
	 * @param tileWidth
	 *            Largeur des tuiles, en pixels
	 * @param tileHeight
	 *            Hauteur des tuiles, en pixels
	 */
	public MapTiledBuilder(int mapWidth, int mapHeight, int tileWidth,
			int tileHeight) {
		super(new TileWareHouseImpl(tileWidth, tileHeight), mapWidth, mapHeight, null);
		map = new Tile[mapWidth][mapHeight];
		
		fillWith(tileWareHouse.getNullTile());
	}

	/**
	 * Constructeur de map à tuiles
	 * 
	 * @param tileWareHouse
	 *            Entrepôt de stockage des tuiles
	 * @param mapWidth
	 *            Nombre de tuiles à l'horizontale
	 * @param mapHeight
	 *            Nombre de tuiles à la verticale
	 */
	public MapTiledBuilder(TileWareHouse tileWareHouse, int mapWidth,
			int mapHeight) {
		super(tileWareHouse, mapWidth, mapHeight, null);
	}

	private void addTile(Tile tile) {
		tileWareHouse.addTile(tile);
	}
	
	public Image getBackground() {
		return background;
	}
	public void setBackground(Image background) {
		this.background = background;
	}

	/**
	 * Rempli la carte avec une tuile particulière
	 * 
	 * @param tile
	 *            tuile à utiliser
	 */
	public void fillWith(Tile tile) {
		for (int x = 0; x < getMapWidth(); x++)
			for (int y = 0; y < getMapHeight(); y++)
				map[x][y] = tile;
		addTile(tile);
	}

	/**
	 * Place une tuile sur la carte
	 * 
	 * @param tile
	 *            tuile a positionner a cette case
	 * @param x
	 *            position x de la case
	 * @param y
	 *            position y de la case
	 */
	public void setTile(Tile tile, int x, int y) {
		if (x < 0 || y < 0 || x >= getMapWidth() || y >= getMapHeight())
			return;
		map[x][y] = tile;
		addTile(tile);
	}

	/**
	 * Trace une ligne horizontale avec la tuile spécifiée
	 * 
	 * @param tile
	 *            tuile a utiliser
	 * @param x
	 *            position x du debut de la ligne
	 * @param y
	 *            position y du debut de la ligne
	 * @param length
	 *            longueur de la ligne (peut etre negative)
	 */
	public void horizontal(Tile tile, int x, int y, int length) {
		if (length == 0)
			return;
		else if (length < 0)
			horizontal(tile, x - length, y, -length);
		else {
			if (x >= 0 && x + length < getMapWidth() && y >= 0
					&& y < getMapHeight())
				for (int xm = x; xm < x + length; xm++)
					map[xm][y] = tile;
			addTile(tile);
		}
	}

	/**
	 * Trace une ligne horizontale avec la tuile spécifiée
	 * 
	 * @param tile
	 *            tuile a utiliser
	 * @param x
	 *            position x du debut de la ligne
	 * @param y
	 *            position y du debut de la ligne
	 * @param length
	 *            longueur de la ligne (peut etre negative)
	 */
	public void vertical(Tile tile, int x, int y, int length) {
		if (length == 0)
			return;
		else if (length < 0)
			horizontal(tile, x, y - length, -length);
		else {
			if (x >= 0 && x < getMapWidth() && y >= 0
					&& y + length < getMapHeight())
				for (int ym = y; ym < y + length; ym++)
					map[x][ym] = tile;
			addTile(tile);
		}
	}

	/**
	 * Redimensionne la carte : perte de données si nouvelle taille est
	 * inferieure
	 * 
	 * @param width
	 *            largeur de la redimenssion
	 * @param height
	 *            hauteur de la redimenssion
	 */
	public void resize(int width, int height) {
		if (width <= 0 || height <= 0)
			return; // invalide

		int minw = Math.min(getMapWidth(), width);
		int minh = Math.min(getMapHeight(), height);

		Tile m[][] = new Tile[width][height]; // nouvelle carte
		// copie des valeurs
		for (int y = 0; y < minh; y++) {
			for (int x = 0; x < minw; x++) {
				m[x][y] = map[x][y];
			}
		}

		// remplace
		map = m;
		mapWidth = width;
		mapHeight = height;
	}

	/**
	 * Donne une portion (rectangulaire) de la carte (renvoi null si parametre
	 * invalide)
	 * 
	 * @param x
	 *            position x du coin en haut a gauche du debut de la portion
	 * @param y
	 *            position y du coin en haut a gauche du debut de la portion
	 * @param width
	 *            largeur de la portion
	 * @param height
	 *            hauteur de la portion
	 * @return une Map representant la MAPption
	 */
	public MapTiled subMap(int x, int y, int width, int height) {
		if (x < 0 || y < 0 || x + height >= getMapWidth()
				|| y + width >= getMapHeight())
			return null; // invalide

		Tile m[][] = new Tile[width][height]; // nouvelle carte

		// copie de la portion
		for (int ys = y; ys < y + height; ys++) {
			for (int xs = x; xs < x + width; xs++) {
				m[xs - x][ys - y] = map[xs][ys];
			}
		}
		return new MapTiledBuilder(tileWareHouse.clone(), width, height);
	}
	
	/**
	 * Sauvegarde la map
	 * @param path
	 */
	public void saveTo(String path) {
		
	}
}
