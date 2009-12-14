package story.scenario;

import map.maptiled.MapTiled;
import story.event.EventManager;
import Constants.Constants;
import controller.gamecontroller.GameWindow;
import entities.manager.EntityManager;
import exceptions.NoWindowException;


/**
 * Manage the creation of a scenario.
 * Loading the map, the entity manager, etc...
 * 
 * @author Aurélien RAMBAUX
 *
 */
public interface Scenario extends Constants {

	/**
	 * Create the game controller from the scenario
	 */
	public void initialization(MapTiled mapTiled, EntityManager entityManager, EventManager eventManager);
	
	/**
	 * 
	 * @param gameWindow
	 */
	public void setGameWindow(GameWindow gameWindow);
	
	/**
	 * Start the scenario
	 */
	public void start() throws NoWindowException;
	
	/**
	 * @return true if the scenario is terminated.
	 */
	public boolean isTerminated();
	
}
