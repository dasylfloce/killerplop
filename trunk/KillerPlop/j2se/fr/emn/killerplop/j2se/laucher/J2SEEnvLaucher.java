package fr.emn.killerplop.j2se.laucher;

import fr.emn.killerplop.game.constants.Constants;
import fr.emn.killerplop.graphics.context.GameWindow;
import fr.emn.killerplop.graphics.context.GraphicUtilities;
import fr.emn.killerplop.graphics.imageCenter.ImageCenterLoader;
import fr.emn.killerplop.j2se.graphics.AWTGameWindow;
import fr.emn.killerplop.j2se.graphics.AWTGraphicUtilities;
import fr.emn.killerplop.story.Environnement;
import fr.emn.killerplop.story.KillerPlop;
import fr.emn.killerplop.story.test.EntityTest;

public class J2SEEnvLaucher implements Environnement, Constants {

	public static void main(String[] args) {
		KillerPlop killerPlop = new KillerPlop(new J2SEEnvLaucher());
		killerPlop.run();

	}

	@Override
	public GameWindow createGameWindow() {
		AWTGameWindow gameWindow = new AWTGameWindow(WINDOW_WIDTH,
				WINDOW_HEIGHT);
		gameWindow.getKeyHandler().setShip(EntityTest.ship);
		return gameWindow;
	}

	@Override
	public void initGraphicContext() {
		GraphicUtilities.set(new AWTGraphicUtilities());
		ImageCenterLoader.loadAllImages();
	}
}
