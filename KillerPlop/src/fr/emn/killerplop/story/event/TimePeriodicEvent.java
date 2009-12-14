package fr.emn.killerplop.story.event;


public abstract class TimePeriodicEvent extends TimeEvent {
	
	protected int period;
	
	protected TimePeriodicEvent(long firstActivationTime, int period) {
		super(firstActivationTime);
		this.period = period;
	}

	@Override
	public boolean isOneActivationOnly() {
		return false;
	}

}
