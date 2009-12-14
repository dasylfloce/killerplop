package fr.emn.killerplop.game.map.tile;

import fr.emn.killerplop.game.entities.shapes.NullShape;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.context.GraphicContext;
import fr.emn.killerplop.graphics.sprites.SimpleSprite;
import fr.emn.killerplop.graphics.sprites.Sprite;

public class SimpleTile implements Tile {

	/**
	 * L'image actuelle à dessiner.
	 */
	protected Sprite sprite;
	/**
	 * Nom de la tuile.
	 */
	protected String imageRef;
	
	/**
	 * Construit une tuile à partir d'une image.
	 * 
	 * @param name
	 *            nom de la tuile (réference vers l'image)
	 */
	public SimpleTile(String imageRef, boolean blocking) {
		this.imageRef = imageRef;
		sprite = new SimpleSprite(imageRef);
		if (!blocking)
			sprite.setShape(new NullShape());
	}

	@Override
	public int getWidth() {
		return (int)sprite.getShape().getWidth();
	}

	@Override
	public int getHeight() {
		return (int)sprite.getShape().getHeight();
	}

	@Override
	public String getName() {
		return imageRef;
	}

	@Override
	public void update(long delta) {
		sprite.update(delta);
	}

	@Override
	public void draw(GraphicContext graphicContext, int x, int y) {
		sprite.draw(graphicContext, x, y);
	}

	@Override
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
