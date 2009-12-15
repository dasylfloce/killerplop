package fr.emn.killerplop.game.storybeans.scenario;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.entitymanager.EntityManager;
import fr.emn.killerplop.game.map.maptiled.MapTiled;
import fr.emn.killerplop.game.storybeans.event.EventManager;
import fr.emn.killerplop.graphics.context.GameWindow;


/**
 * Manage the creation of a scenario.
 * Loading the map, the entity manager, etc...
 * 
 * @author Aurélien RAMBAUX
 *
 */
public interface Scenario extends Constants, Runnable {

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
	 * @return true if the scenario is terminated.
	 */
	public boolean isTerminated();
	
}
