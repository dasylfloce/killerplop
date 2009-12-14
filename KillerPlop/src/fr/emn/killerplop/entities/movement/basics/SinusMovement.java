package fr.emn.killerplop.entities.movement.basics;

import fr.emn.killerplop.controller.GameController;
import fr.emn.killerplop.entities.Entity;

public class SinusMovement extends ControlledMovement {

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
	public SinusMovement(double amplitude, double period, double vx) {
		time = 0;
		this.vx = vx;
		this.amplitude = amplitude;
		this.period = (period * 1000) / (2 * Math.PI);
	}

	@Override
	protected void calculateSpeed(Entity entity, GameController gameController) {
		time += gameController.getDelta();
		setVx(vx);
		setVy(amplitude * Math.sin(time / period));
	}

}
