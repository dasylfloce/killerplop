package fr.emn.killerplop.story.event;

import fr.emn.killerplop.controller.GameController;

/**
 * Manages independent events.
 * 
 * @author Aurélien RAMBAUX
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
