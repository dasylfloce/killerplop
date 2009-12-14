package fr.emn.killerplop.game.controller.explosionmanager;

import java.util.LinkedList;

import fr.emn.killerplop.game.entities.Entity;
import fr.emn.killerplop.graphics.GraphicContext;

public class ExplosionManager {

	protected LinkedList<Explosion> explosionPool;
	protected LinkedList<Explosion> activeExplosions;

	protected boolean endLoop;

	public ExplosionManager() {
		explosionPool = new LinkedList<Explosion>();
		activeExplosions = new LinkedList<Explosion>();

		for (int i = 0; i < 5; i++)
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
	
	public void render(GraphicContext graphicContext, int offsetX, int offsetY) {
		for (Explosion explosion : activeExplosions) {
			explosion.draw(graphicContext, offsetX, offsetY);
		}
	}
}
