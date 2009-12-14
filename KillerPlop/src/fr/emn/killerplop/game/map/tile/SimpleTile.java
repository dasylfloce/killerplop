package fr.emn.killerplop.game.map.tile;

import java.awt.Graphics2D;

import fr.emn.killerplop.game.entities.shapes.NullShape;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.game.resources.ImageStore;
import fr.emn.killerplop.game.sprites.SimpleSprite;
import fr.emn.killerplop.game.sprites.Sprite;

public class SimpleTile implements Tile {

	/**
	 * L'image actuelle � dessiner.
	 */
	protected Sprite sprite;
	/**
	 * Nom de la tuile.
	 */
	protected String name;
	
	/**
	 * Construit une tuile � partir d'une image.
	 * 
	 * @param name
	 *            nom de la tuile (r�ference vers l'image)
	 */
	public SimpleTile(String name, boolean blocking) {
		this.name = name;
		sprite = new SimpleSprite(ImageStore.get(name));
		if (!blocking)
			sprite.setShape(new NullShape());
	}

	@Override
	public int getWidth() {
		return sprite.getWidth();
	}

	@Override
	public int getHeight() {
		return sprite.getHeight();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void update(long delta) {
		sprite.update(delta);
	}

	@Override
	public void draw(Graphics2D g, int x, int y) {
		sprite.draw(g, x, y);
	}

	public boolean equals(Object o) {
		if (o instanceof Tile) {
			return ((Tile) o).getName().equals(getName());
		}
		return false;
	}

	@Override
	public boolean isBlockingAt(double x, double y) {
		return sprite.getShape().contains(x, y);
	}

	@Override
	public Shape getShape() {
		return sprite.getShape();
	}
}
