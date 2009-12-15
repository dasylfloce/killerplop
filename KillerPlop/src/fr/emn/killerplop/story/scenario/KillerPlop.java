package fr.emn.killerplop.story.scenario;

import fr.emn.killerplop.story.event.EventManager;
import fr.emn.killerplop.story.event.EventManagerImpl;

public abstract class KillerPlop implements ScenarioManager {

	@Override
	public Scenario getNextScenario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		initGraphicContext();
		
		EventManager eventManager = new EventManagerImpl();
		//eventManager.addEvent(new RandomMapSpeedEvent(1500));
		eventManager.addEvent(new NormalMapSpeedEvent());
		
				
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);


	}

	public abstract void initGraphicContext();
}
