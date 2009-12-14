package fr.emn.killerplop.game.controller.gamecontroller;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.emn.killerplop.game.entities.ship.KeyHandler;

@SuppressWarnings("serial")
public class GameWindowImpl extends Canvas implements GameWindow {

	/** The game window that we'll update with the frame count */
	protected JFrame container;
	/** Size of the window */
	protected int width;
	protected int height;
	
	protected KeyHandler keyHandler;

	/**
	 * Construct our map and set it running.
	 */
	public GameWindowImpl(int width, int height) {
		// create a frame to contain our game
		container = new JFrame();
		this.width = width;
		this.height = height;

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(width,
				height));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, width, height);
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);

		// finally make the window visible
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		keyHandler = new KeyHandler();
		addKeyListener(keyHandler);

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
	}
	
	@Override
	public KeyHandler getKeyHandler(){
		return keyHandler;
	}

	
	@Override
	public Graphics2D getGraphics() {
		return (Graphics2D) getBufferStrategy().getDrawGraphics();
	}

	@Override
	public void setTitle(String title) {
		container.setTitle(title);
	}

	@Override
	public void show() {
		getBufferStrategy().show();
	}

	@Override
	public int getWindowHeight() {
		return height;
	}

	@Override
	public int getWindowWidth() {
		return width;
	}

}
