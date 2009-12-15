package fr.emn.killerplop.story.event;

import java.util.Random;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.storybeans.event.TimePeriodicEvent;

public class RandomMapSpeedEvent extends TimePeriodicEvent {
	
	protected Random r;
	protected double maxVx;

	public RandomMapSpeedEvent(int period, double maxVx) {
		super(0, period);
		r = new Random();
		this.maxVx = maxVx;
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(r.nextDouble()*maxVx);
	}
		
}