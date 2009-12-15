/*
 * Author 	: SkyMan
 * web		: http://www.skysoft.fr
 */

package fr.emn.killerplop.test;

import AWTGraphicUtilities;

import com.flexycore.iphone.coregraphics.CGRect;
import com.flexycore.iphone.uikit.UIApplication;
import com.flexycore.iphone.uikit.UIApplicationDelegate;
import com.flexycore.iphone.uikit.UIScreen;
import com.flexycore.iphone.uikit.UIWindow;

import fr.emn.killerplop.graphics.context.GraphicUtilities;
import fr.emn.killerplop.graphics.imageCenter.ImageCenterLoader;

public class IPhoneTest extends UIApplicationDelegate {

	UIWindow window;
	GraphicView graphicView;

	public void applicationDidFinishLaunching(UIApplication application) {
		//GraphicUtilities.set(new IGraphicUtilities());
		ImageCenterLoader.loadAllImages();

		// UIWindow creation
		UIScreen uscreen = UIScreen.mainScreen();
		CGRect displayRect = uscreen.applicationFrame();
		window = new UIWindow();
		CGRect mbounds = uscreen.bounds();
		window.initWithFrame(mbounds);

		// Create the GraphicView, and puts it on the screen
		graphicView = new GraphicView();
		graphicView.initWithFrame(displayRect);
		window.addSubview(graphicView);
		window.makeKeyAndVisible();

		// ActivateRefresh
		graphicView.activateRefresh();
	}
}
