package story.event;



public interface TimeEvent extends Event {

	/**
	 * @param time the time elapsed since the beginning of the scenario
	 * @return true if the event has just been activated, regarding the time
	 */
	public boolean isActivated(long time);
	
}
