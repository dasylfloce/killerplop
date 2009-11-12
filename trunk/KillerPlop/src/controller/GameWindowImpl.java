package controller;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.ship.KeyHandler;

@SuppressWarnings("serial")
public class GameWindowImpl extends Canvas implements GameWindow {

	/** The stragey that allows us to use accelerate page flipping */
	protected BufferStrategy strategy;
	/** The game window that we'll update with the frame count */
	protected JFrame container;
	/** Size of the window */
	protected Dimension windowSize;

	/**
	 * Construct our map and set it running.
	 */
	public GameWindowImpl(Dimension windowSize) {
		// create a frame to contain our game
		container = new JFrame();
		this.windowSize = windowSize;

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(windowSize.width,
				windowSize.height));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, windowSize.width, windowSize.height);
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
		addKeyListener(new KeyHandler());

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	
	@Override
	public Graphics2D getGraphics() {
		return (Graphics2D) strategy.getDrawGraphics();
	}

	@Override
	public Dimension getSize() {
		return windowSize;
	}

	@Override
	public void setTitle(String title) {
		container.setTitle(title);
	}

	@Override
	public void show() {
		strategy.show();
	}

}
