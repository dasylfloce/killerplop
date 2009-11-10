package map.mapTiled;

import java.awt.Graphics2D;
import java.awt.Shape;

import map.tile.Tile;
import map.tile.TileWareHouse;

public class MapTiledImpl implements MapTiled {

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
	 */
	protected MapTiledImpl(TileWareHouse tileWareHouse, int mapWidth,
			int mapHeight) {
		this.tileWareHouse = tileWareHouse;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
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
		zoneRender(g, x, y, viewWidth, viewHeight, 0, 0);
	}

	/**
	 * Dessine une zone sur le graph à partir de zéro.
	 * 
	 * @param g
	 *            Graphic sur lequel dessiner.
	 * @param posx
	 *            la position x en pixels de la vue
	 * @param posy
	 *            la position y en pixels de la vue
	 * @param vw
	 *            largeur en pixels de la vue
	 * @param vh
	 *            hauteur en pixels de la vue
	 * @param xd
	 *            la position x où afficher dans le Graphics
	 * @param yd
	 *            la position y où afficher dans le Graphics
	 */
	private void zoneRender(Graphics2D g, double posx, double posy, int vw,
			int vh, int xd, int yd) throws ArrayIndexOutOfBoundsException {
		// positions dans la carte
		int xm = ((int) posx) / getTileWidth();
		int ym = ((int) posy) / getTileHeight();

		// la vue passe au milieu d'une tuile
		int xo = ((int) posx) % getTileWidth();
		int yo = ((int) posy) % getTileHeight();

		// defini le clipping
		Shape clipping = g.getClip();
		g.setClip(xd, yd, vw, vh);

		// affichage
		Tile t = null;
		int pxm = 0, pym = ym; // positions dans la map
		for (int y = -yo; y < vh; y += getTileHeight()) {
			pxm = xm;
			for (int x = -xo; x < vw; x += getTileWidth()) {
				t = getTileAt(pxm, pym);
				if (t != null)
					t.draw(g, x + xd, y + yd);
				pxm++;
			}
			pym++;
		}

		// remet le clipping
		g.setClip(clipping);
	}

}
