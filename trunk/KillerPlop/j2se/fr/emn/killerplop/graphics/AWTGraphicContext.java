package fr.emn.killerplop.graphics;

import java.awt.Graphics2D;

import fr.emn.killerplop.graphics.context.GraphicContext;

public class AWTGraphicContext implements GraphicContext {
	
	protected Graphics2D g;
	
	public AWTGraphicContext(Graphics2D g) {
		this.g = g;
	}

	@Override
	public void draw(String imageRef, int x, int y) {
		g.drawImage(AWTImageStore.get(imageRef), x, y, null);
	}

}
