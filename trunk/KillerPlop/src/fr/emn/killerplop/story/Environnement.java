package fr.emn.killerplop.story;

import fr.emn.killerplop.graphics.context.GameWindow;

public interface Environnement {

	public void initGraphicContext();
	
	public GameWindow createGameWindow();

}
