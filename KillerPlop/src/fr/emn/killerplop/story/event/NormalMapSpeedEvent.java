package fr.emn.killerplop.story.event;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.storybeans.event.TimeEvent;

public class NormalMapSpeedEvent extends TimeEvent {
	
	protected double speed;

	public NormalMapSpeedEvent(double speed) {
		super(0);
		this.speed = speed;
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(speed);
	}

	@Override
	public boolean isOneActivationOnly() {
		return true;
	}

}