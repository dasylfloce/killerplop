package tests;

import java.awt.Dimension;
import java.util.Random;

import story.event.EventManager;
import story.event.EventManagerImpl;
import story.event.TimeEvent;
import story.event.TimePeriodicEvent;
import story.scenario.Scenario;
import story.scenario.ScenarioImpl;
import Constants.Constants;
import controller.GameController;
import controller.GameWindow;
import controller.GameWindowImpl;
import exceptions.NoWindowException;

public class Test implements Constants {
	
	public static Random r = new Random();
	
	public static void main(String[] args) throws NoWindowException {
		
		EventManager eventManager = new EventManagerImpl();
		//eventManager.addEvent(new RandomMapSpeedEvent(1500));
		eventManager.addEvent(new NormalMapSpeedEvent());
		
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);
		
		GameWindow gameWindow = new GameWindowImpl(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
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
		gameController.setHorizontalMovement(Test.r.nextDouble()*WINDOW_WIDTH/2);
	}
		
}
class NormalMapSpeedEvent extends TimeEvent {

	protected NormalMapSpeedEvent() {
		super(0);
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(240);
	}

	@Override
	public boolean isOneActivationOnly() {
		return true;
	}

}
