package fr.emn.killerplop.tests;

import java.util.Random;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.story.event.EventManager;
import fr.emn.killerplop.game.story.event.EventManagerImpl;
import fr.emn.killerplop.game.story.event.TimeEvent;
import fr.emn.killerplop.game.story.event.TimePeriodicEvent;
import fr.emn.killerplop.game.story.scenario.Scenario;
import fr.emn.killerplop.game.story.scenario.ScenarioImpl;
import fr.emn.killerplop.graphics.AWTGameWindow;
import fr.emn.killerplop.graphics.AWTGraphicUtilities;
import fr.emn.killerplop.graphics.context.GraphicUtilities;
import fr.emn.killerplop.graphics.imageCenter.ImageCenterLoader;

public class Test implements Constants {
	
	public static Random r = new Random();
	public static final  int WINDOW_HEIGHT = 320;
	public static final  int WINDOW_WIDTH = 600;

	public static void main(String[] args) {
		GraphicUtilities.set(new AWTGraphicUtilities());
		ImageCenterLoader.loadAllImages();
		
		EventManager eventManager = new EventManagerImpl();
		//eventManager.addEvent(new RandomMapSpeedEvent(1500));
		eventManager.addEvent(new NormalMapSpeedEvent());
		
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);
		
		AWTGameWindow gameWindow = new AWTGameWindow(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameWindow.getKeyHandler().setShip(EntityTest.ship);
		storyTest.setGameWindow(gameWindow);
		
		storyTest.run();
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
