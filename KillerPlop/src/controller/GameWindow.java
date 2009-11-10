package controller;

import java.awt.Dimension;
import java.awt.Graphics2D;

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
	public Graphics2D getGraphics();
	
	/**
	 * Update the graphics on screen
	 */
	public void show();
	
	/**
	 * @return the size of the window.
	 */
	public Dimension getSize();
	
	/**
	 * Check if a particular key is pressed
	 * 
	 * @param keyCode The code associate with the key to check
	 * @return True if the particular key is pressed
	 */
	public boolean isKeyPressed(int keyCode);
}