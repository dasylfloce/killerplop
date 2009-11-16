package map.tile;

import java.awt.Graphics2D;

public class NullTile implements Tile {

	protected int width;
	protected int height;
	
	/**
	 * Construit une tuile vide.
	 * @param width largeur de la tuile vide
	 * @param height hauteur de la tuile vide
	 */
	public NullTile(int width, int height) {
		this.width = width;
		this.height = height;
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
	public void draw(Graphics2D g, int x, int y) {
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
}
