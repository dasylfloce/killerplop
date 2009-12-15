package fr.emn.killerplop.graphics.context;

import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.exceptions.OutOfMapException;


/**
 * The window in which the game will be displayed.
 *
 * @author Aurélien RAMBAUX
 */
public interface GameWindow {
	
	/**
	 * Set the title of the game window
	 * 
	 * @param title The new title for the game window
	 */
	public void setTitle(String title);
	
	/**
	 * Draws everything on the window
	 * @param gameController game
	 */
	public void render(GameController gameController) throws OutOfMapException;
	
	/**
	 * @return the width of the window.
	 */
	public int getWindowWidth();
	/**
	 * @return the height of the window.
	 */
	public int getWindowHeight();

}