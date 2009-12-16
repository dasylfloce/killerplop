package fr.emn.killerplop.j2se.graphics;

import java.awt.Graphics;

import fr.emn.killerplop.graphics.context.GraphicContext;

public class AWTGraphicContext implements GraphicContext {
	
	protected Graphics g;
	
	public AWTGraphicContext() {}
	
	public void setGraphics(Graphics g) {
		this.g = g;
	}
	
	@Override
	public void draw(String imageRef, int x, int y) {
		g.drawImage(AWTImageStore.get(imageRef), x, y, null);
	}

}
