package map.tile;

import java.awt.Graphics2D;

import resources.sprites.ImageStore;
import resources.sprites.SimpleSprite;
import resources.sprites.Sprite;

public class SimpleTile implements Tile {

	/**
	 * L'image actuelle à dessiner.
	 */
	protected Sprite sprite;
	/**
	 * Nom de la tuile.
	 */
	protected String name; 
	
	/**
	 * Construit une tuile à partir d'une image
	 * @param name nom de la tuile (réference vers l'image)
	 */
	public SimpleTile(String name) {
		this.name = name;
		sprite = new SimpleSprite(ImageStore.get(name));
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
			return ((Tile)o).getName().equals(getName());
		}
		return false;
	}
}
