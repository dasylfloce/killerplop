package entities.manager;

import java.awt.Graphics2D;

import controller.GameController;
import entities.Entity;

/**
 * This control the movement and collision of any entities of the game.
 * 
 * Entities are activated when the controller reach a particular position, and
 * are desactivated when they go off the view.
 * 
 * @author Aurélien RAMBAUX
 * 
 */
public interface EntityManager {

	/**
	 * Activate all the entities that should be activated at the "x" position
	 * 
	 * @param x
	 *            Horizontal position of activation
	 */
	public void activateEntities(double x);

	/**
	 * Desactivate a particular entity. The entity can be re-activated later.
	 * 
	 * @param entity
	 *            Entity to desactivate.
	 */
	public void desactivateEntity(Entity entity);

	/**
	 * Move all the entities that are active
	 * 
	 * @param gameController
	 *            Controller of the current level.
	 */
	public void moveEntities(GameController gameController);

	/**
	 * Perform the collisions between entities that are activated.
	 */
	public void resolveCollisions();

	/**
	 * Draw all active entities on the graph, with an offset that is required by
	 * the map placement.
	 * 
	 * @param g
	 *            Graph
	 * @param offsetX
	 *            Horizontal offset
	 * @param offsetY
	 *            Vertical offset
	 */
	public void render(Graphics2D g, int offsetX, int offsetY);
}
