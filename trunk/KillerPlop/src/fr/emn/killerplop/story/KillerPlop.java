package fr.emn.killerplop.story;

import fr.emn.killerplop.game.storybeans.event.EventManager;
import fr.emn.killerplop.game.storybeans.event.EventManagerImpl;
import fr.emn.killerplop.game.storybeans.scenario.Scenario;
import fr.emn.killerplop.game.storybeans.scenario.ScenarioImpl;
import fr.emn.killerplop.game.storybeans.scenario.ScenarioManager;

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
