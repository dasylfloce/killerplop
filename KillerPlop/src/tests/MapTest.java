package tests;

import map.Utilities;
import map.maptiled.MapTiled;
import map.maptiled.MapTiledBuilder;
import map.tile.SimpleTile;
import map.tile.Tile;

public class MapTest extends Test {

	/**
	 * Run the main game loop. This method keeps rendering the scene and
	 * requesting that the callback update its screen.
	 */
	// protected void gameLoop() {
	// long lastLoopTime = System.nanoTime();
	// long sleep;
	//
	// // keep looping round til the game ends
	// while (true) {
	// // work out how long its been since the last update, this
	// // will be used to calculate how far the entities should
	// // move this loop
	// long delta = (System.nanoTime() - lastLoopTime) / 1000000;
	// lastLoopTime = System.nanoTime();
	//
	// // update the frame counter
	// lastFpsTime += delta;
	// fps++;
	//
	// // update our FPS counter if a second has passed since
	// // we last recorded
	// if (lastFpsTime >= 1000) {
	// container.setTitle(windowTitle + " (FPS: " + fps + ")");
	// lastFpsTime = 0;
	// fps = 0;
	//
	// //Speed update
	// gameController.setHorizontalMovement(40*r.nextInt(10)+1);
	// //gameController.setHorizontalMovement(120);
	// }
	//
	// // Get hold of a graphics context for the accelerated
	// // surface and blank it out
	// Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
	// // g.setColor(Color.black);
	// // g.fillRect(0, 0, 800, 600);
	//
	// // Drawing the map
	// //gameController.update(delta);
	// // gameController.render(g);
	//
	// // finally, we've completed drawing so clear up the graphics
	// // and flip the buffer over
	// g.dispose();
	// strategy.show();
	//
	// // we want each frame to take 10 milliseconds, to do this
	// // we've recorded when we started the frame. We add 10 milliseconds
	// // to this and then factor in the current time to give
	// // us our final value to wait for
	// if ((sleep = 1000/FPS - (System.nanoTime() - lastLoopTime) / 1000000) >
	// 0)
	// try {
	// Thread.sleep(sleep);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// System.out.println("Débordement : " + sleep);
	// }
	// }
	// }

	public static MapTiled createMap() {
		MapTiledBuilder map = new MapTiledBuilder(500, 20, 32, 32);
		Tile tileHerbe = new SimpleTile("herbe", Utilities
				.loadImage("src/gfx/tile4.png"));
		Tile tileMur = new SimpleTile("mur", Utilities
				.loadImage("src/gfx/tile3.png"));
		Tile tilePierre = new SimpleTile("pierre", Utilities
				.loadImage("src/gfx/tile2.png"));

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
