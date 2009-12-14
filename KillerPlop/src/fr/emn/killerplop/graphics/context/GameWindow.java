package fr.emn.killerplop.graphics.context;

import fr.emn.killerplop.controls.KeyHandler;

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
	 * @return the graphic context of the window
	 */
	public GraphicContext getGraphicContext();
	
	/**
	 * Update the graphics on screen
	 */
	public void show();
	
	/**
	 * @return the width of the window.
	 */
	public int getWindowWidth();
	/**
	 * @return the height of the window.
	 */
	public int getWindowHeight();

	
	public KeyHandler getKeyHandler();
	
}