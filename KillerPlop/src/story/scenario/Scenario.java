package story.scenario;

import controller.GameWindow;
import story.event.EventManager;
import map.maptiled.MapTiled;
import entities.manager.EntityManager;
import exceptions.NoWindowException;


/**
 * Manage the creation of a scenario.
 * Loading the map, the entity manager, etc...
 * 
 * @author Aurélien RAMBAUX
 *
 */
public interface Scenario {

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
