package fr.emn.killerplop.entities.movement;

import fr.emn.killerplop.controller.gamecontroller.GameController;
import fr.emn.killerplop.entities.Entity;

/**
 * Control the movement of entities
 * 
 * @author Aurélien RAMBAUX
 * 
 */
public interface Movement {

	/**
	 * Calculate and set the horizontal and vertical speed of an entity.
	 * 
	 * @param entity Entity to configure.
	 * @param gameController Game controller.
	 */
	public void setMovement(Entity entity, GameController gameController);
}
