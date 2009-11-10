package story.scenario;

import java.awt.Graphics2D;

import story.event.EventManager;


import map.maptiled.MapTiled;

import controller.GameController;
import controller.GameControllerImpl;
import controller.GameWindow;
import entities.manager.EntityManager;

public class ScenarioImpl implements Scenario {

	protected GameWindow gameWindow;
	protected String title;

	public static final int FRAME_RATE = 50;

	protected GameController gameController;
	protected EventManager eventManager;

	/**
	 * Constructor
	 * 
	 * @param gameWindow
	 *            Window in which the game is running
	 */
	public ScenarioImpl(GameWindow gameWindow, String title) {
		super();
		this.gameWindow = gameWindow;
		this.title = title;
	}

	@Override
	public void start() {
		loop();
	}

	protected void loop() {
		// sleep : time to sleep before the next frame
		// delta : time elapsed since the previous frame (in ms)
		// lastFpsTime : time elapsed since the last record of fps
		// fps : recorded fps
		long sleep = 0, delta = 0, lastFpsTime = 0, fps = 0;
		long lastLoopTime = System.nanoTime();

		// keep looping round til the game ends
		while (!isTerminated()) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			delta = (System.nanoTime() - lastLoopTime) / 1000000;
			lastLoopTime = System.nanoTime();

			// update the frame counter
			lastFpsTime += delta;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000) {
				gameWindow.setTitle(title + " (FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;

				// Speed update
				//gameController.setHorizontalMovement(40 * r.nextInt(10) + 1);
			}
			
			// Updating the game
			gameController.updateView(delta);

			// Activating independant events
			eventManager.activateEvents(gameController);
			
			// Moving entities
			gameController.updateEntities();

			// Get hold of a graphics context for the accelerated
			// surface
			Graphics2D g = gameWindow.getGraphics();

			// Drawing everything
			gameController.render(g);

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			gameWindow.show();

			// we want each frame to take x milliseconds, to do this
			// we've recorded when we started the frame. We add x milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			if ((sleep = 1000 / FRAME_RATE - (System.nanoTime() - lastLoopTime)
					/ 1000000) > 0)
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("Débordement : " + sleep);
				}
		}

	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public void initialization(MapTiled mapTiled, EntityManager entityManager,
			EventManager eventManager) {
		gameController = new GameControllerImpl(mapTiled, entityManager, 0, 0,
				gameWindow.getSize().width, gameWindow.getSize().height);
		this.eventManager = eventManager;
	}

}
