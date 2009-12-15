package fr.emn.killerplop.story.event;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;

/**
 * A simple event. Superclass of all events.
 * Can be managed by an EventManager
 * 
 * An event can be a TimeEvent : executed after a while
 * 					 PositionEvent : executed when the controller reach a position
 * 
 * @author Aurélien RAMBAUX
 *
 */
public interface Event extends Constants {

	/**
	 * Checks if the event is actually activated, because of the time or the
	 * position.
	 * 
	 * @param gameController
	 *            controller of the game
	 * @return true if the event has just been activated
	 */
	public boolean isActivationPoint(GameController gameController);

	/**
	 * @return true if the event is a time event.
	 */
	public boolean isTimeEvent();
	
	/**
	 * @return true if the event is a position event.
	 */
	public boolean isPositionEvent();
	
	/**
	 * 
	 * @param gameController.
	 */
	public void doEvent(GameController gameController);
	
	/**
	 * @return true if this event must be activated only once.
	 */
	public boolean isOneActivationOnly();

}
