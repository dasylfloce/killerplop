package fr.emn.killerplop.story.event;

import fr.emn.killerplop.controller.gamecontroller.GameController;



public abstract class TimeEvent implements Event {
	
	protected long nextActivationTime;
	
	protected TimeEvent(long nextActivationTime) {
		this.nextActivationTime = nextActivationTime;
	}

	@Override
	public boolean isActivationPoint(GameController gameController) {
		 return gameController.getTime() >= nextActivationTime;
	}
	
	public void setNextActivationTime(long nextActivationTime) {
		this.nextActivationTime = nextActivationTime;
	}

	@Override
	public boolean isPositionEvent() {
		return false;
	}

	@Override
	public boolean isTimeEvent() {
		return true;
	}
	
}
