package fr.emn.killerplop.game.map.maptiled;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.game.exceptions.OutOfMapException;
import fr.emn.killerplop.game.map.tile.Tile;
import fr.emn.killerplop.game.map.tile.TileWareHouse;
import fr.emn.killerplop.graphics.context.GraphicContext;

public class MapTiledImpl implements MapTiled, Constants {

	/**
	 * Image de fond de la map, qui ne d�file pas.
	 */
	protected String background;

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
	 *            Entrep�t de stockage des tuiles
	 * @param mapWidth
	 *            Nombre de tuiles � l'horizontale
	 * @param mapHeight
	 *            Nombre de tuiles � la verticale
	 * @param background
	 *            Image de fond statique
	 */
	protected MapTiledImpl(TileWareHouse tileWareHouse, int mapWidth,
			int mapHeight, String background) {
		this.tileWareHouse = tileWareHouse;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.background = background;
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

	private Tile getTileAt(double x, double y) throws OutOfMapException {
		try {
			return map[((int) x) / getTileWidth()][((int) y) / getTileHeight()];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new OutOfMapException(this, x, y);
		}
	}

	@Override
	public void render(GraphicContext graphicContext, double x, double y, int viewWidth,
			int viewHeight) throws OutOfMapException {

		if (x < 0 || y < 0 || x + viewWidth > mapWidth * getTileWidth()
				|| y + viewHeight > mapHeight * getTileHeight())
			throw new OutOfMapException(this, x, y);

		// affichage du background
		if (background != null)
			graphicContext.draw(background, 0, 0);

		// positions dans la carte
		int xm = ((int) x) / getTileWidth();
		int ym = ((int) y) / getTileHeight();

		// la vue passe au milieu d'une tuile
		int xo = ((int) x) % getTileWidth();
		int yo = ((int) y) % getTileHeight();

		// affichage
		Tile t = null;
		int pxm = xm, pym = ym; // positions dans la map
		for (int yi = -yo; yi < viewHeight; yi += getTileHeight()) {
			pxm = xm;
			for (int xi = -xo; xi < viewWidth; xi += getTileWidth()) {
				t = map[pxm][pym];
				if (t != null)
					t.draw(graphicContext, xi, yi);
				pxm++;
			}
			pym++;
		}
	}

	@Override
	public boolean isBlockedAt(double x, double y) throws OutOfMapException {
			return getTileAt(x, y).isBlockingAt(x % getTileWidth(),
					y % getTileHeight());
	}

	@Override
	public Shape getShapeAt(double x, double y) throws OutOfMapException {
		return getTileAt(x, y).getShape();
	}

}
