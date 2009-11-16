package map.maptiled;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

import Constants.Constants;

import map.tile.Tile;
import map.tile.TileWareHouse;

public class MapTiledImpl implements MapTiled, Constants {

	/**
	 * Image de fond de la map, qui ne défile pas.
	 */
	protected Image background;

	/**
	 * La carte : contient les tuiles
	 */
	protected Tile map[][];

	/**
	 * Les tuiles de la map
	 */
	protected TileWareHouse tileWareHouse;

	/**
	 * Largeur de la carte en tuile
	 */
	protected int mapWidth;

	/**
	 * Hauteur de la carte en tuile
	 */
	protected int mapHeight;

	/**
	 * Obligation de passer par MapTiledBuilder pour creer une carte.
	 * 
	 * @param tileWareHouse
	 *            Entrepôt de stockage des tuiles
	 * @param mapWidth
	 *            Nombre de tuiles à l'horizontale
	 * @param mapHeight
	 *            Nombre de tuiles à la verticale
	 * @param background
	 *            Image de fond statique
	 */
	protected MapTiledImpl(TileWareHouse tileWareHouse, int mapWidth,
			int mapHeight, Image background) {
		this.tileWareHouse = tileWareHouse;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.background = background;
	}

	@Override
	public Tile getTileAt(int x, int y) throws ArrayIndexOutOfBoundsException {
		return map[x][y];
	}

	@Override
	public int getTileHeight() {
		return tileWareHouse.getTileHeight();
	}

	@Override
	public int getTileWidth() {
		return tileWareHouse.getTileWidth();
	}

	@Override
	public int getMapWidth() {
		return mapWidth;
	}

	@Override
	public int getMapHeight() {
		return mapHeight;
	}

	@Override
	public void updateTiles(long delta) {
		tileWareHouse.updateTiles(delta);
	}

	@Override
	public void render(Graphics2D g, double x, double y, int viewWidth,
			int viewHeight) throws ArrayIndexOutOfBoundsException {
		// affichage du background
		if (background != null)
			g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);

		// positions dans la carte
		int xm = ((int) x) / getTileWidth();
		int ym = ((int) y) / getTileHeight();

		// la vue passe au milieu d'une tuile
		int xo = ((int) x) % getTileWidth();
		int yo = ((int) y) % getTileHeight();

		// defini le clipping
		Shape clipping = g.getClip();
		g.setClip(0, 0, viewWidth, viewHeight);

		// affichage
		Tile t = null;
		int pxm = 0, pym = ym; // positions dans la map
		for (int yi = -yo; yi < viewHeight; yi += getTileHeight()) {
			pxm = xm;
			for (int xi = -xo; xi < viewWidth; xi += getTileWidth()) {
				t = getTileAt(pxm, pym);
				if (t != null)
					t.draw(g, xi, yi);
				pxm++;
			}
			pym++;
		}

		// remet le clipping
		g.setClip(clipping);
	}

}
