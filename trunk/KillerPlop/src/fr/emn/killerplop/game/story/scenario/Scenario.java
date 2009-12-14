package fr.emn.killerplop.game.story.scenario;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.entitymanager.EntityManager;
import fr.emn.killerplop.game.controller.gamecontroller.GameWindow;
import fr.emn.killerplop.game.exceptions.NoWindowException;
import fr.emn.killerplop.game.map.maptiled.MapTiled;
import fr.emn.killerplop.game.story.event.EventManager;


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
