package tests;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import controller.GameControllerImpl;
import entities.manager.EntityManager;
import entities.manager.EntityManagerImpl;

import map.Utilities;
import map.maptiled.MapTiled;
import map.maptiled.MapTiledBuilder;
import map.tile.SimpleTile;
import map.tile.Tile;

@SuppressWarnings("serial")
public class MapTest extends Canvas {

	public static Random r = new Random();

	/** The stragey that allows us to use accelerate page flipping */
	protected BufferStrategy strategy;

	/** The last time at which we recorded the frame rate */
	protected long lastFpsTime;
	/** The current number of frames recorded */
	protected int fps;
	/** The normal title of the game window */
	protected String windowTitle = "Test Map";
	/** The game window that we'll update with the frame count */
	protected JFrame container;

	protected GameController gameController;
	
	protected int FPS = 50;
	protected Dimension windowSize = new Dimension(480, 320);

	/**
	 * Construct our map and set it running.
	 */
	public MapTest() {
		// create a frame to contain our game
		container = new JFrame(windowTitle);
	}
	/**
	 * Start the rendering process. This method will not return. 
	 */
	public void startRendering() {

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(windowSize.width,
				windowSize.height));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, windowSize.width, windowSize.height);
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);

		// finally make the window visible
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		gameController = new GameControllerImpl(createMap(), createEntityManager(), 0, 0, windowSize.width,
				windowSize.height);
		
		gameLoop();
	}
	
	/**
	 * Run the main game loop. This method keeps rendering the scene
	 * and requesting that the callback update its screen.
	 */
	protected void gameLoop() {
		long lastLoopTime = System.nanoTime();
		long sleep;

		// keep looping round til the game ends
		while (true) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long delta = (System.nanoTime() - lastLoopTime) / 1000000;
			lastLoopTime = System.nanoTime();

			// update the frame counter
			lastFpsTime += delta;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000) {
				container.setTitle(windowTitle + " (FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;

				//Speed update
				gameController.setHorizontalMovement(40*r.nextInt(10)+1);
				//gameController.setHorizontalMovement(120);
			}

			// Get hold of a graphics context for the accelerated
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			// g.setColor(Color.black);
			// g.fillRect(0, 0, 800, 600);

			// Drawing the map
			//gameController.update(delta);
			gameController.render(g);

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();

			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			if ((sleep = 1000/FPS - (System.nanoTime() - lastLoopTime) / 1000000) > 0)
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("Débordement : " + sleep);
				}
		}
	}

	public MapTiled createMap() {
		MapTiledBuilder map = new MapTiledBuilder(500, 10, 32, 32);
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
	
	protected EntityManager createEntityManager() {
		return new EntityManagerImpl(null, null);
	}

	/**
	 * The entry point into the game. We'll simply create an instance of class
	 * which will start the display and game loop.
	 * 
	 * @param argv
	 *            The arguments that are passed into our game
	 */
	public static void main(String argv[]) {
		MapTest mapTest = new MapTest();

		// Start the main game loop, note: this method will not
		// return until the game has finished running. Hence we are
		// using the actual main thread to run the game.
		mapTest.startRendering();
	}
}
