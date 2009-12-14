package entities.manager;

import java.awt.Graphics2D;
import java.awt.Image;

import resources.ImageStore;
import entities.Entity;

public class Explosion {

	/** The array of images of the explosion */
	protected static Image[] images = new Image[] {
			ImageStore.get("resources/explosion/explosion1.png"),
			ImageStore.get("resources/explosion/explosion2.png"),
			ImageStore.get("resources/explosion/explosion3.png"),
			ImageStore.get("resources/explosion/explosion4.png"),
			ImageStore.get("resources/explosion/explosion5.png") };
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
		this.x = (int)entity.getX();
		this.y = (int)entity.getY();
		active = true;
		timeElapsed = 0;
		imageIndex = 0;
		return this;
	}
	
	public boolean isActive() {
		return active;
	}

	public void draw(Graphics2D g, int offsetX, int offsetY) {
		g.drawImage(images[imageIndex], x-offsetX, y-offsetY, null);
	}

	public void update(long delta) {
		timeElapsed += delta;
		if (timeElapsed >= duration) {
			imageIndex++;
			timeElapsed = timeElapsed % duration;
		}
		if (imageIndex >= images.length)
			active = false;
	}
}
