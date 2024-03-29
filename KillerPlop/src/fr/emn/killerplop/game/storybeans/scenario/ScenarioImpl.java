package fr.emn.killerplop.game.storybeans.scenario;

import fr.emn.killerplop.game.controller.entitymanager.EntityManager;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.controller.gamecontroller.GameControllerImpl;
import fr.emn.killerplop.game.exceptions.OutOfMapException;
import fr.emn.killerplop.game.exceptions.ViewSizeNullException;
import fr.emn.killerplop.game.map.maptiled.MapTiled;
import fr.emn.killerplop.game.storybeans.event.EventManager;
import fr.emn.killerplop.graphics.context.GameWindow;

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
	public void run() {
		if (gameWindow == null) {
			System.err.println("No window !");
			System.exit(-1);
		}

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
		int refreshViewRatio = 0;

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

			// Show on window
			if (refreshViewRatio % REFRESH_VIEW_RATIO == 0)
				gameWindow.render(gameController);
			refreshViewRatio++;

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
