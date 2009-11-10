package tests;

import java.awt.Dimension;
import java.util.Random;

import story.event.EventManagerImpl;
import story.scenario.Scenario;
import story.scenario.ScenarioImpl;

import controller.GameWindow;
import controller.GameWindowImpl;
import exceptions.NoWindowException;

public class Test {
	
	public static final Dimension windowSize = new Dimension(800, 600);
	public static Random r = new Random();
	
	public static void main(String[] args) throws NoWindowException {
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), new EventManagerImpl());
		
		GameWindow gameWindow = new GameWindowImpl(windowSize);
		storyTest.setGameWindow(gameWindow);
		
		storyTest.start();
	}

}
