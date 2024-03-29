package fr.emn.killerplop.game.controller.entitymanager;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.entities.Entity;
import fr.emn.killerplop.graphics.context.GraphicContext;

/**
 * This control the movement and collision of any entities of the game.
 * 
 * Entities are activated when the controller reach a particular position, and
 * are desactivated when they go off the view.
 * 
 * @author Aur�lien RAMBAUX
 * 
 */
public interface EntityManager {

	/**
	 * Adds an entity to the entity manager.
	 * 
	 * @param entity
	 *            the entity to add.
	 */
	public void addEntity(Entity entity);

	/**
	 * Activate all the alien entities that should be activated at the "x"
	 * position
	 * 
	 * @param x
	 *            Horizontal position of activation
	 */
	public void activateEntities(double x);

	/**
	 * Move all the entities that are active
	 * 
	 * @param gameController
	 *            Controller of the current level.
	 */
	public void moveEntities(GameController gameController);

	/**
	 * Perform the collisions between entities that are activated.
	 * 
	 * @param viewWidth
	 * @param viewHeight
	 * @param y
	 * @param x
	 */
	public void resolveCollisions(double x, double y, int viewHeight,
			int viewWidth);

	/**
	 * Draw all active entities on the graph, with an offset that is required by
	 * the map placement.
	 * 
	 * @param graphicContext
	 *            Graph
	 * @param offsetX
	 *            Horizontal offset
	 * @param offsetY
	 *            Vertical offset
	 */
	public void render(GraphicContext graphicContext, int offsetX, int offsetY);

	public void resolveShot(long delta);
	
	public void manageExplosions(long delta);
	
	/**
	 * Delete all entities that have been marked destroyed
	 */
	public void cleanUpEntities();
}
