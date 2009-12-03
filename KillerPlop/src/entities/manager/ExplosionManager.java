package entities.manager;

import java.awt.Graphics2D;
import java.util.LinkedList;

import entities.Entity;

public class ExplosionManager {

	protected LinkedList<Explosion> explosionPool;
	protected LinkedList<Explosion> activeExplosions;

	protected boolean endLoop;

	public ExplosionManager() {
		explosionPool = new LinkedList<Explosion>();
		activeExplosions = new LinkedList<Explosion>();

		for (int i = 0; i < 10; i++)
			explosionPool.add(new Explosion());
	}

	public void destroy(Entity entity) {
		if (explosionPool.isEmpty()) {
			explosionPool.add(new Explosion());
		}
		activeExplosions
				.addLast(explosionPool.removeFirst().activateOn(entity));
	}

	public void update(long delta) {
		for (Explosion explosion : activeExplosions) {
			explosion.update(delta);
		}
		do {
			if (activeExplosions.isEmpty()) {
				endLoop = false;
			} else {
				if (!activeExplosions.getFirst().isActive()) {
					explosionPool.add(activeExplosions.removeFirst());
					endLoop = true;
				} else {
					endLoop = false;
				}
			}
		} while (endLoop);
	}
	
	public void render(Graphics2D g, int offsetX, int offsetY) {
		for (Explosion explosion : activeExplosions) {
			explosion.draw(g, offsetX, offsetY);
		}
	}
}
