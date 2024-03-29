package fr.emn.killerplop.game.storybeans.event;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;

/**
 * Manages independent events.
 * 
 * @author Aur�lien RAMBAUX
 * 
 */
public interface EventManager {

	/**
	 * Checks all events on queue and activates them if necessary.
	 * 
	 * @param gameController
	 *            controller of the game
	 */
	public void activateEvents(GameController gameController);

	/**
	 * Add an event to the manager.
	 * 
	 * @param event
	 *            event to add.
	 */
	public void addEvent(Event event);

}
