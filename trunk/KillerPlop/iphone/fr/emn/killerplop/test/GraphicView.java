/*
 * Author 	: SkyMan
 * web		: http://www.skysoft.fr
 */

package fr.emn.killerplop.test;

import java.util.Random;
import com.flexycore.iphone.coregraphics.CGContext;
import com.flexycore.iphone.coregraphics.CGContextRef;
import com.flexycore.iphone.coregraphics.CGRect;
import com.flexycore.iphone.uikit.UIKit;
import com.flexycore.iphone.uikit.UIScreen;
import com.flexycore.iphone.uikit.UIView;

public class GraphicView extends UIView implements Runnable {

	// the refresh rate
	private static final int REFRESH_RATE_MILLISECOND = 100;

	// For random
	private Random random;

	// size of screen
	private int width, height;

	// create graphic view
	public GraphicView() {
		// allocate UIView
		super();

		// Random Init
		random = new Random(123);

		// init width and height
		CGRect r = UIScreen.mainScreen().applicationFrame();
		width = (int) r.getSize().getWidth();
		height = (int) r.getSize().getHeight();
	}

	// refresh screen
	public void drawRect(CGRect clip) {
		// get graphic context
		CGContextRef context = UIKit.UIGraphicsGetCurrentContext();

		// clear screen
		CGContext.CGContextSetRGBFillColor(context, (float) 0, (float) 0,
				(float) 0.0, (float) 1.0);
		CGContext.CGContextFillRect(context, clip);

		//display 20 rects per frame
		for (int ii = 0; ii < 20; ii++) {
			 //Allocate a new rect

			// get coordinates of new rect
			int x = (int) (Math.abs(random.nextInt()) % (width));
			int y = (int) (Math.abs(random.nextInt()) % (height));

			// get coordinates of new rect
			int w = (int) (Math.abs(random.nextInt()) % (width - x));
			int h = (int) (Math.abs(random.nextInt()) % (height - y));

			// get RGB colors
			float r = (float) (Math.abs(random.nextFloat()) % 1);
			float g = (float) (Math.abs(random.nextFloat()) % 1);
			float b = (float) (Math.abs(random.nextFloat()) % 1);

			//Put it on display

			// set fill color
			CGContext.CGContextSetRGBFillColor(context, (float) r, (float) g,
					(float) b, (float) 1.0);

			// allocate a rect
			CGRect rect = new CGRect();
			rect.init(x, y, w, h);

			// push a fillrect on screen
			CGContext.CGContextFillRect(context, rect);
		}
	}

	// create the thread in charge of refresh
	public void activateRefresh() {
		Thread t = new Thread(this);
		t.start();
	}

	// periodic background thread body
	public void run() {
		while (true) {
			// ask for refresh, will call back drawRect
			setNeedsDisplay();

			// Sleeps
			try {
				Thread.sleep(REFRESH_RATE_MILLISECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
