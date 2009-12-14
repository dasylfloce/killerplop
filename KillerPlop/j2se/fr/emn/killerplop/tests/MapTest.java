package fr.emn.killerplop.tests;


import fr.emn.killerplop.game.map.maptiled.MapTiled;
import fr.emn.killerplop.game.map.maptiled.MapTiledBuilder;
import fr.emn.killerplop.game.map.tile.SimpleTile;
import fr.emn.killerplop.game.map.tile.Tile;
import fr.emn.killerplop.graphics.imageCenter.ImageCenter;

public class MapTest extends Test implements ImageCenter {

	public static MapTiled createMap() {
		return background();
	}

	public static MapTiled background() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		map.setBackground(BG_SPACE);
		
		Tile tileAsteroide = new SimpleTile(TILE_ASTEROIDE, true);

		for (int x = 6; x < 400; x+=12) {
			int nb = r.nextInt(3);
			for (int y = 0; y < nb; y++)
				map.setTile(tileAsteroide, x+r.nextInt(9), 1 + r.nextInt(8));
		}
		return map;
	}
	public static MapTiled fullTiles() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		Tile tileHerbe = new SimpleTile(TILE_NOIRE, false);
		Tile tileMur = new SimpleTile(TILE_ASTEROIDE, true);
		Tile tilePierre = new SimpleTile(TILE_ASTEROIDE, true);

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
