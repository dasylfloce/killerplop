package fr.emn.killerplop.game.storybeans.event;

import java.util.LinkedList;
import java.util.ListIterator;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;


public class EventManagerImpl implements EventManager {

	protected LinkedList<Event> eventList;

	public EventManagerImpl() {
		eventList = new LinkedList<Event>();
	}

	@Override
	public void activateEvents(GameController gameController) {
		ListIterator<Event> it = eventList.listIterator();
		while (it.hasNext()) {
			Event event = it.next();
			if (event.isActivationPoint(gameController)) {
				event.doEvent(gameController);
				if (event.isOneActivationOnly())
					it.remove();
			}
		}
	}
	
	@Override
	public void addEvent(Event event) {
			eventList.add(event);
	}

}
