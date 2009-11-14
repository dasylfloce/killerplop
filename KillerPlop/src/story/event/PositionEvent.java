package story.event;

import controller.GameController;



public abstract class PositionEvent implements Event {

	protected long nextActivationPoint;

	@Override
	public boolean isActivationPoint(GameController gameController) {
		 return gameController.getX() >= nextActivationPoint;
	}
	
	public void setNextActivationPoint(long nextActivationPoint) {
		this.nextActivationPoint = nextActivationPoint;
	}

	@Override
	public boolean isPositionEvent() {
		return true;
	}

	@Override
	public boolean isTimeEvent() {
		return false;
	}
	
}
