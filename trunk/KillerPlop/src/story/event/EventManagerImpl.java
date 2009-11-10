package story.event;

import java.util.LinkedList;

import controller.GameController;


public class EventManagerImpl implements EventManager {

	protected LinkedList<TimeEvent> timeEventList;
	protected LinkedList<PositionEvent> positionEventList;

	protected long timeElapsed;

	public EventManagerImpl() {
		timeEventList = new LinkedList<TimeEvent>();
		positionEventList = new LinkedList<PositionEvent>();
		timeElapsed = 0;
	}

	@Override
	public void activateEvents(GameController gameController) {
		timeElapsed += gameController.getDelta();
		for (TimeEvent event : timeEventList)
			if (event.isActivated(timeElapsed))
				event.doEvent(gameController);

		for (PositionEvent event : positionEventList)
			if (event.isActivated(gameController.getX()))
				event.doEvent(gameController);
	}

	@Override
	public void addEvent(Event event) {
		if (event.isPositionEvent())
			positionEventList.add((PositionEvent)event);
		if (event.isTimeEvent())
			timeEventList.add((TimeEvent)event);
	}

}
