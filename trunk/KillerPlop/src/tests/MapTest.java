package tests;

import map.maptiled.MapTiled;
import map.maptiled.MapTiledBuilder;
import map.tile.SimpleTile;
import map.tile.Tile;

public class MapTest extends Test {

	public static MapTiled createMap() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		Tile tileHerbe = new SimpleTile("resources/tiles/background-star.png");
		Tile tileMur = new SimpleTile("resources/tiles/tile3.png");
		Tile tilePierre = new SimpleTile("resources/tiles/tile2.png");

		map.fillWith(tileHerbe);
		map.horizontal(tileMur, 0, 0, 499);
		map.horizontal(tileMur, 0, 9, 499);

		for (int x = 10; x < 500; x++) {
			int nb = r.nextInt(3);
			for (int y = 0; y < nb; y++)
				map.setTile(tilePierre, x, 1 + r.nextInt(8));
		}
		return map;
	}
}
