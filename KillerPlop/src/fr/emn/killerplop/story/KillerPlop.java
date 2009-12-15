package fr.emn.killerplop.story;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.game.storybeans.event.EventManager;
import fr.emn.killerplop.game.storybeans.event.EventManagerImpl;
import fr.emn.killerplop.game.storybeans.scenario.Scenario;
import fr.emn.killerplop.game.storybeans.scenario.ScenarioImpl;
import fr.emn.killerplop.game.storybeans.scenario.ScenarioManager;
import fr.emn.killerplop.story.event.NormalMapSpeedEvent;
import fr.emn.killerplop.story.test.EntityTest;
import fr.emn.killerplop.story.test.MapTest;

public class KillerPlop implements ScenarioManager, Constants {

	protected Environnement env;
	
	public KillerPlop(Environnement env) {
		this.env = env;
	}

	@Override
	public Scenario getNextScenario() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void run() {
		env.initGraphicContext();
		
		EventManager eventManager = new EventManagerImpl();
		eventManager.addEvent(new NormalMapSpeedEvent(60));
		Scenario storyTest = new ScenarioImpl("Test");
		storyTest.initialization(MapTest.createMap(), EntityTest.createEntityManager(), eventManager);

		storyTest.setGameWindow(env.createGameWindow());
		storyTest.run();
	}

}
