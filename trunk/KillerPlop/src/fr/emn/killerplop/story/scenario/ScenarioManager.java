package fr.emn.killerplop.story.scenario;

public interface ScenarioManager extends Runnable {

	/**
	 * @return the next scenario of the game.
	 */
	public Scenario getNextScenario();
}
