package tests;

import java.awt.Dimension;
import java.util.Random;

import Constants.Constants;

import story.event.EventManager;
import story.event.EventManagerImpl;
import story.event.PeriodicEvent;
import story.event.TimeEvent;
import story.scenario.Scenario;
import story.scenario.ScenarioImpl;
import controller.GameController;
import controller.GameWindow;
import controller.GameWindowImpl;
import exceptions.NoWindowException;

public class Test implements Constants{
	
	public static final Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	public static Random r = new Random();
	
	public static void main(String[] args) throws NoWindowException {
		
		EventManager eventManager = new EventManagerImpl();
		//eventManager.addEvent(new RandomMapSpeedEvent(1500));
		eventManager.addEvent(new NormalMapSpeedEvent());
		
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);
		
		GameWindow gameWindow = new GameWindowImpl(windowSize);
		gameWindow.getKeyHandler().setShip(EntityTest.ship);
		storyTest.setGameWindow(gameWindow);
		
		storyTest.start();
	}
}

class RandomMapSpeedEvent extends PeriodicEvent {

	protected RandomMapSpeedEvent(int period) {
		super(period);
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(Test.r.nextDouble()*Test.windowSize.width/2);
	}
	
	
	
}
class NormalMapSpeedEvent implements TimeEvent {

	protected NormalMapSpeedEvent() {
		super();
	}

	@Override
	public void doEvent(GameController gameController) {
		gameController.setHorizontalMovement(80);
	}

	@Override
	public boolean isActivationPoint(long time) {
		
		return time>0;
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
