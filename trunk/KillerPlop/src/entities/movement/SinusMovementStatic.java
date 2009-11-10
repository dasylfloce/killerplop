package entities.movement;

import controller.GameController;
import entities.Entity;

public class SinusMovementStatic implements Movement {

	protected long time;
	protected double amplitude;
	protected double vx;
	protected double period;

	/**
	 * 
	 * @param amplitude
	 *            Amplitude in pxl
	 * @param period
	 *            Period in sec
	 * @param vx
	 */
	public SinusMovementStatic(double amplitude, double period, double vx) {
		super();
		time = 0;
		this.vx = vx;
		this.amplitude = amplitude;
		this.period = (period * 1000) / (2 * Math.PI);
	}

	@Override
	public void setMovement(Entity entity, GameController gameController) {
		time += gameController.getDelta();
		entity.setHorizontalMovement(vx);
		entity.setVerticalMovement(amplitude * Math.sin(time / period));
	}

}
