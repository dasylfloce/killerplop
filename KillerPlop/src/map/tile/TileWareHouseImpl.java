package map.tile;

import java.util.ArrayList;

public class TileWareHouseImpl implements TileWareHouse {

	/**
	 * Liste contenant les tuiles
	 */
	private ArrayList<Tile> tiles;

	/**
	 * Largeur des tuiles : chaque tuiles a la meme dimension
	 */
	private int tileWidth;

	/**
	 * Hauteur des tuiles : chaque tuiles a la meme dimension
	 */
	private int tileHeight;

	/**
	 * Construit un nouveau entrepot de stockage.
	 * @param tileWidth
	 *            Largeur des tuiles, en pixels
	 * @param tileHeight
	 *            Hauteur des tuiles, en pixels
	 */
	public TileWareHouseImpl(int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		tiles = new ArrayList<Tile>();
		tiles.add(new NullTile(tileWidth, tileHeight));
	}
	
	/**
	 * Construit un nouveau entrepot de stockage.
	 * @param tileWidth
	 *            Largeur des tuiles, en pixels
	 * @param tileHeight
	 *            Hauteur des tuiles, en pixels
	 * @param tiles
	 *            Collection des tuiles de l'entrepot.
	 */
	@SuppressWarnings("unchecked")
	public TileWareHouseImpl(int tileWidth, int tileHeight, ArrayList<Tile> tiles) {
		this(tileWidth, tileHeight);

		if (tiles != null)
			this.tiles = (ArrayList<Tile>) tiles.clone();
	}

	@Override
	public int addTile(Tile tile) {
		if (tile == null)
			return -1;
		if (tile.getWidth() != tileWidth
				|| tile.getHeight() != tileHeight)
			return -1;

		// Tile valide
		if (!contains(tile))
			tiles.add(tile);
		return tiles.indexOf(tile);
	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

	@Override
	public int getTileWidth() {
		return tileWidth;
	}

	@Override
	public int getNbTiles() {
		return tiles.size();
	}
	
	@Override
	public Tile getNullTile() {
		return tiles.get(0);
	}

	@Override
	public boolean contains(Tile tile) {
		return tiles.contains(tile);
	}

	@Override
	public void updateTiles(long delta) {
		for (Tile tile : tiles) {
			tile.update(delta);
		}
	}

	@Override
	public TileWareHouse clone() {
		return new TileWareHouseImpl(tileWidth, tileHeight, tiles);
	}
}
