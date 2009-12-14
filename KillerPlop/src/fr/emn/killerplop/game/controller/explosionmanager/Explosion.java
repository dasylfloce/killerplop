package fr.emn.killerplop.game.controller.explosionmanager;

import fr.emn.killerplop.game.entities.Entity;
import fr.emn.killerplop.graphics.GraphicContext;

public class Explosion {

	/** The array of images of the explosion */
	protected static String[] imagesRef = new String[] {
			"resources/explosion/explosion1.png",
			"resources/explosion/explosion2.png",
			"resources/explosion/explosion3.png",
			"resources/explosion/explosion4.png",
			"resources/explosion/explosion5.png" };

	/** The duration of each images */
	protected static long duration = 50;

	/** The index of the actual image drawn */
	protected int imageIndex;
	/** Current time elapsed since the last image change */
	protected long timeElapsed;

	/** Current position on x of the explosion */
	protected int x;
	/** Current position on y of the explosion */
	protected int y;
	/** Status of the explosion */
	protected boolean active;

	public Explosion() {
	}

	public Explosion activateOn(Entity entity) {
		this.x = (int) entity.getX();
		this.y = (int) entity.getY();
		active = true;
		timeElapsed = 0;
		imageIndex = 0;
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public void draw(GraphicContext graphicContext, int offsetX, int offsetY) {
		graphicContext.draw(imagesRef[imageIndex], x - offsetX, y - offsetY);
	}

	public void update(long delta) {
		timeElapsed += delta;
		if (timeElapsed >= duration) {
			imageIndex++;
			timeElapsed = timeElapsed % duration;
		}
		if (imageIndex >= imagesRef.length)
			active = false;
	}
}
