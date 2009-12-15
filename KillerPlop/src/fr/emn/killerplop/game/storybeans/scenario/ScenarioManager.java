package fr.emn.killerplop.game.storybeans.scenario;

public interface ScenarioManager extends Runnable {

	/**
	 * @return the next scenario of the game.
	 */
	public Scenario getNextScenario();
}
