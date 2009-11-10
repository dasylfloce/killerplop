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
	 * Set the game display resolution
	 * 
	 * @param x The new x resolution of the display
	 * @param y The new y resolution of the display
	 */
	public void setResolution(int x,int y);
	
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