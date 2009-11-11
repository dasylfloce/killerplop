package story.event;



public interface PositionEvent extends Event {

	/**
	 * @param position the position of activation (on x axis)
	 * @return true if the event has just been activated, regarding the position
	 */
	public boolean isActivationPoint(double position);
	
}
