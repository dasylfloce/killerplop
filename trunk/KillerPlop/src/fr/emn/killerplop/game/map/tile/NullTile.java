package fr.emn.killerplop.game.map.tile;

import fr.emn.killerplop.game.entities.shapes.NullShape;
import fr.emn.killerplop.game.entities.shapes.Shape;
import fr.emn.killerplop.graphics.GraphicContext;

public class NullTile implements Tile {

	protected int width;
	protected int height;
	
	protected Shape shape;
	
	/**
	 * Construit une tuile vide.
	 * @param width largeur de la tuile vide
	 * @param height hauteur de la tuile vide
	 */
	public NullTile(int width, int height) {
		this.width = width;
		this.height = height;
		shape = new NullShape();
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getName() {
		return "null";
	}

	@Override
	public void update(long delta) {
		//Rien à faire dans une tuile vide.
	}

	@Override
	public void draw(GraphicContext graphicContext, int x, int y) {
		//Rien à dessiner
	}

	public boolean equals(Object o) {
		if (o instanceof Tile) {
			return ((Tile)o).getName().equals("null");
		}
		return false;
	}

	@Override
	public boolean isBlockingAt(double x, double y) {
		//Jamais blocant.
		return false;
	}

	@Override
	public Shape getShape() {
		return shape;
	}
}
