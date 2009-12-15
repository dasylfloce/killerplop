package fr.emn.killerplop.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.emn.killerplop.controls.KeyHandler;
import fr.emn.killerplop.game.controller.gamecontroller.GameController;
import fr.emn.killerplop.game.exceptions.OutOfMapException;
import fr.emn.killerplop.graphics.context.GameWindow;

@SuppressWarnings("serial")
public class AWTGameWindow extends Canvas implements GameWindow {

	/** The game window that we'll update with the frame count */
	protected JFrame container;
	/** Size of the window */
	protected int width;
	protected int height;

	protected KeyHandler keyHandler;

	protected AWTGraphicContext graphicContext;

	public AWTGameWindow(int width, int height) {
		// create the graphic context
		graphicContext = new AWTGraphicContext();

		// create a frame to contain our game
		container = new JFrame();
		this.width = width;
		this.height = height;

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(width, height));
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
			@Override
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

	public KeyHandler getKeyHandler() {
		return keyHandler;
	}

	@Override
	public void setTitle(String title) {
		container.setTitle(title);
	}

	@Override
	public int getWindowHeight() {
		return height;
	}

	@Override
	public int getWindowWidth() {
		return width;
	}

	@Override
	public void render(GameController gameController) throws OutOfMapException {
		graphicContext.setGraphics(getBufferStrategy().getDrawGraphics());
		
		// Drawing everything
		gameController.render(graphicContext);

		// finally, we've completed drawing so clear up the graphics
		// and flip the buffer over
		getBufferStrategy().show();
	}

}
