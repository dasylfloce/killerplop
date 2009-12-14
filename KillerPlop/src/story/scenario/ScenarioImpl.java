package story.scenario;

import java.awt.Graphics2D;

import map.maptiled.MapTiled;
import story.event.EventManager;
import controller.GameController;
import controller.gamecontroller.GameControllerImpl;
import controller.gamecontroller.GameWindow;
import entities.manager.EntityManager;
import fr.emn.killerplop.exceptions.NoWindowException;
import fr.emn.killerplop.exceptions.OutOfMapException;
import fr.emn.killerplop.exceptions.ViewSizeNullException;

public class ScenarioImpl implements Scenario {

	protected GameWindow gameWindow;
	protected String title;

	protected GameController gameController;
	protected EventManager eventManager;

	/**
	 * Constructor
	 * 
	 * @param gameWindow
	 *            Window in which the game is running
	 */
	public ScenarioImpl(String title) {
		this.title = title;
	}

	@Override
	public void start() throws NoWindowException {
		if (gameWindow == null)
			throw new NoWindowException();

		gameController.setViewSize(gameWindow.getWindowWidth(), gameWindow
				.getWindowHeight());
		try {
			loop();
		} catch (ViewSizeNullException e) {
			e.printStackTrace();
		} catch (OutOfMapException e) {
			e.printStackTrace();
		}
	}

	protected void loop() throws ViewSizeNullException, OutOfMapException {
		if (gameController.getViewWidth() * gameController.getViewHeight() == 0)
			throw new ViewSizeNullException();

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
			}

			// Updating the game, activating events and moving entities
			gameController.updateView(delta);
			eventManager.activateEvents(gameController);
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

			// we want each frame to take x milliseconds (given by the number of
			// frame per second), to do this we've recorded when we started the
			// frame. We add x milliseconds to this and then factor in the
			// current time to give us our final value to wait for
			if ((sleep = 1000 / FRAME_RATE - (System.nanoTime() - lastLoopTime)
					/ 1000000) > 0)
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("Débordement : " + sleep);
				}
			if (sleep <= 1000 / FRAME_RATE * 0.8)
				System.out.println("ScenarioImpl.loop() : " + sleep);
		}

	}

	@Override
	public boolean isTerminated() {
		return false;
	}

	@Override
	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	@Override
	public void initialization(MapTiled mapTiled, EntityManager entityManager,
			EventManager eventManager) {
		gameController = new GameControllerImpl(mapTiled, entityManager, 0, 0);
		this.eventManager = eventManager;
	}

}
