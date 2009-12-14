package fr.emn.killerplop.story.scenario;

import fr.emn.killerplop.constants.Constants;
import fr.emn.killerplop.controller.entitymanager.EntityManager;
import fr.emn.killerplop.controller.gamecontroller.GameWindow;
import fr.emn.killerplop.exceptions.NoWindowException;
import fr.emn.killerplop.map.maptiled.MapTiled;
import fr.emn.killerplop.story.event.EventManager;


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
