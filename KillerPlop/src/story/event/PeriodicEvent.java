package story.event;


public abstract class PeriodicEvent implements TimeEvent {
	
	protected int period;
	protected long nextActivation;
	
	protected PeriodicEvent(int period) {
		this.period = period;
	}

	@Override
	public boolean isActivationPoint(long time) {
		if (time > nextActivation) {
			nextActivation += period;
			return true;
		}
		return false;
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
