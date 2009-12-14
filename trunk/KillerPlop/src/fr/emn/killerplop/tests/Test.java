package fr.emn.killerplop.tests;

import java.util.Random;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.controller.gamecontroller.GameWindow;
import fr.emn.killerplop.game.controller.gamecontroller.GameWindowImpl;
import fr.emn.killerplop.game.exceptions.NoWindowException;
import fr.emn.killerplop.game.story.event.EventManager;
import fr.emn.killerplop.game.story.event.EventManagerImpl;
import fr.emn.killerplop.game.story.event.TimeEvent;
import fr.emn.killerplop.game.story.event.TimePeriodicEvent;
import fr.emn.killerplop.game.story.scenario.Scenario;
import fr.emn.killerplop.game.story.scenario.ScenarioImpl;

public class Test implements Constants {
	
	public static Random r = new Random();
	public static final  int WINDOW_HEIGHT = 320;
	public static final  int WINDOW_WIDTH = 600;

	public static void main(String[] args) throws NoWindowException {
		
		EventManager eventManager = new EventManagerImpl();
		//eventManager.addEvent(new RandomMapSpeedEvent(1500));
		eventManager.addEvent(new NormalMapSpeedEvent());
		
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);
		
		GameWindow gameWindow = new GameWindowImpl(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameWindow.getKeyHandler().setShip(EntityTest.ship);
		storyTest.setGameWindow(gameWindow);
		
		storyTest.start();
	}
}

class RandomMapSpeedEvent extends TimePeriodicEvent {

	protected RandomMapSpeedEvent(int period) {
		super(0, period);
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(Test.r.nextDouble()*Test.WINDOW_WIDTH/2);
	}
		
}
class NormalMapSpeedEvent extends TimeEvent {

	protected NormalMapSpeedEvent() {
		super(0);
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(60);
	}

	@Override
	public boolean isOneActivationOnly() {
		return true;
	}

}
