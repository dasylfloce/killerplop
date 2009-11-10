package story.event;

import controller.GameController;

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
public interface Event {

	/**
	 * @return true if the event is a time event
	 */
	public boolean isTimeEvent();
	
	/**
	 * @return true if the event is a position event
	 */
	public boolean isPositionEvent();
	
	/**
	 * 
	 * @param gameController
	 */
	public void doEvent(GameController gameController);

}
