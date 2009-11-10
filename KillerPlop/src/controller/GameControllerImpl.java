package controller;

import java.awt.Graphics2D;

import map.maptiled.MapTiled;
import entities.manager.EntityManager;

/**
 * Implements the GameController interface.
 * 
 * @author Aur�lien RAMBAUX
 * 
 */
public class GameControllerImpl implements GameController {

	/**
	 * Abscisse du point sup�rieur gauche du controleur (en pixels).
	 */
	protected double x;
	/**
	 * Ordonn�e du point sup�rieur gauche du controleur (en pixels).
	 */
	protected double y;

	/**
	 * Largeur de la vue du controleur, en pixels.
	 */
	protected int viewWidth;
	/**
	 * Hauteur de la vuedu controleur, en pixels.
	 */
	protected int viewHeight;

	/**
	 * The current speed of this controller horizontally (pixels/sec)
	 */
	protected double dx;
	/**
	 * The current speed of this controller vertically (pixels/sec)
	 */
	protected double dy;

	/**
	 * Time elapsed since the last update, in ms.
	 */
	protected long delta;

	/**
	 * Map sur laquelle la vue se d�place.
	 */
	protected MapTiled mapTiled;

	/**
	 * Manager g�rant les entit�s.
	 */
	protected EntityManager entityManager;

	/**
	 * Constructeur du controleur de jeu.
	 * 
	 * @param mapTiled
	 *            Map du niveau.
	 * @param entityManager
	 *            Entity manager du niveau
	 * @param viewX
	 *            Abscisse du point sup�rieur gauche initial (en pixels).
	 * @param viewY
	 *            Ordonn�e verticale du point sup�rieur gauche initial (en
	 *            pixels).
	 * @param viewWidth
	 *            Largeur de la vue, en pixels.
	 * @param viewHeight
	 *            Hauteur de la vue, en pixels.
	 */
	public GameControllerImpl(MapTiled mapTiled, EntityManager entityManager,
			double x, double y, int viewWidth, int viewHeight) {
		this.x = x;
		this.y = y;
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.mapTiled = mapTiled;
		this.entityManager = entityManager;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setViewSize(int width, int height) {
		viewWidth = width;
		viewHeight = height;
	}

	@Override
	public double getHorizontalMovement() {
		return dx;
	}

	@Override
	public double getVerticalMovement() {
		return dy;
	}

	@Override
	public void setHorizontalMovement(double dx) {
		this.dx = dx;
	}

	@Override
	public void setVerticalMovement(double dy) {
		this.dy = dy;
	}

	@Override
	public long getDelta() {
		return delta;
	}

	@Override
	public EntityManager getManager() {
		return entityManager;
	}

	@Override
	public MapTiled getMap() {
		return mapTiled;
	}

	@Override
	public void render(Graphics2D g) {
		mapTiled.render(g, x, y, viewWidth, viewHeight);
		entityManager.render(g, (int) x, (int) y);
	}

	@Override
	public void updateView(long delta) {
		this.delta = delta;

		// update positions
		x += delta * dx / 1000;
		y += delta * dy / 1000;

		if (delta != 30)
		System.out.println("GameController.update(delta=" + delta + ")\n\tx:"
				+ x + "\ty:" + y);
	}

	@Override
	public void updateEntities() {
		entityManager.moveEntities(this);
		entityManager.activateEntities(x + viewWidth);
		entityManager.resolveCollisions();
	}

}
