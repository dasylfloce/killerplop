package story.scenario;

import story.event.EventManager;
import map.maptiled.MapTiled;
import entities.manager.EntityManager;


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
	 * Start the scenario
	 */
	public void start();
	
	/**
	 * @return true if the scenario is terminated.
	 */
	public boolean isTerminated();
	
}
