package fr.emn.killerplop.controller;

import java.awt.Graphics2D;

import fr.emn.killerplop.entities.manager.EntityManager;
import fr.emn.killerplop.exceptions.OutOfMapException;
import fr.emn.killerplop.map.maptiled.MapTiled;

/**
 * Control the evolution of the game.
 * 
 * Everything is updated with "delta", the amount of time in ms passed since the
 * last update. Control the view on the map, and the entities that evolve on it.
 * 
 * @author Aurélien RAMBAUX
 * 
 */
public interface GameController {

	/**
	 * Define the horizontal position of this controller (top left corner).
	 * 
	 * @param x
	 *            The horizontal position, in pxl.
	 */
	public void setX(double x);

	/**
	 * Define the vertical position of this controller (top left corner).
	 * 
	 * @param y
	 *            The vertical position, in pxl.
	 */
	public void setY(double y);

	/**
	 * @return the horizontal position of this controller (top left corner).
	 */
	public double getX();

	/**
	 * @return the vertical position of this controller (top left corner)
	 */
	public double getY();
	
	/**
	 * @return the vertical size of this controller 
	 */
	public int getViewHeight();
	
	/**
	 * @return the horizontal size of this controller 
	 */
	public int getViewWidth();

	/**
	 * Define the size of the view, in pixels.
	 * 
	 * @param width
	 *            Width of the view
	 * @param height
	 *            Height of the view
	 */
	public void setViewSize(int width, int height);

	/**
	 * Return the horizontal speed of this controller.
	 * 
	 * @return The horizontal speed of this controller (pixels/sec)
	 */
	public double getHorizontalMovement();

	/**
	 * Return the vertical speed of this controller.
	 * 
	 * @return The vertical speed of this controller (pixels/sec)
	 */
	public double getVerticalMovement();

	/**
	 * Set the horizontal speed of this controller.
	 * 
	 * @param dx
	 *            The horizontal speed of this controller (pixels/sec)
	 */
	public void setHorizontalMovement(double dx);

	/**
	 * Set the vertical speed of this controller.
	 * 
	 * @param dx
	 *            The vertical speed of this controller (pixels/sec)
	 */
	public void setVerticalMovement(double dy);

	/**
	 * Update the position of the view on the map, and set displacement factor
	 * for entities.
	 * 
	 * @param delta
	 *            Time elapsed, in ms, since the last update.
	 * */
	public void updateView(long delta);

	/**
	 * Update the position of all the active entities.
	 */
	public void updateEntities();

	/**
	 * @return the time in ms elapsed since the last update.
	 */
	public long getDelta();

	/**
	 * @return the time in ms elapsed since the beginning of the scenario.
	 */
	public long getTime();

	/**
	 * @return the tiled map of this controller.
	 */
	public MapTiled getMap();

	/**
	 * @return the entity manager of this controller.
	 */
	public EntityManager getManager();

	/**
	 * Draw the view of the controller on the graphics context provided.
	 * 
	 * @param g
	 *            The graphics context on which to draw
	 */
	public void render(Graphics2D g) throws OutOfMapException;
}
