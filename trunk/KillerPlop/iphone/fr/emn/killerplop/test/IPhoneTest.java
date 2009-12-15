/*
 * Author 	: SkyMan
 * web		: http://www.skysoft.fr
 */

package fr.emn.killerplop.test;

import com.flexycore.iphone.uikit.UIApplication;
import com.flexycore.iphone.uikit.UIApplicationDelegate;

import fr.emn.killerplop.graphics.context.GameWindow;
import fr.emn.killerplop.story.Environnement;
import fr.emn.killerplop.story.KillerPlop;

public class IPhoneTest extends UIApplicationDelegate implements Environnement {

	public void applicationDidFinishLaunching(UIApplication application) {
		KillerPlop killerPlop = new KillerPlop(new IPhoneTest());
		killerPlop.run();
	}

	@Override
	public GameWindow createGameWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initGraphicContext() {
		// TODO Auto-generated method stub
		
	}
}
