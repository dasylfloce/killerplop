package tests;


import resources.ImageStore;
import map.maptiled.MapTiled;
import map.maptiled.MapTiledBuilder;
import map.tile.SimpleTile;
import map.tile.Tile;

public class MapTest extends Test {

	public static MapTiled createMap() {
		return background();
	}

	public static MapTiled background() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		map.setBackground(ImageStore.get("resources/backgrounds/espace1.jpg"));
		
		Tile tileAsteroide = new SimpleTile("resources/tiles/asteroide_transp.png", true);

		for (int x = 10; x < 400; x+=3) {
			int nb = r.nextInt(3);
			for (int y = 0; y < nb; y++)
				map.setTile(tileAsteroide, x, 1 + r.nextInt(8));
		}
		return map;
	}
	public static MapTiled fullTiles() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		Tile tileHerbe = new SimpleTile("resources/tiles/fond20en.png", false);
		Tile tileMur = new SimpleTile("resources/tiles/asteroide_transp.png", true);
		Tile tilePierre = new SimpleTile("resources/tiles/asteroide_transp.png", true);

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
