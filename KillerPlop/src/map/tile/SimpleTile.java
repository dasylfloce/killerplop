package map.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import resources.ImageStore;

public class SimpleTile implements Tile {

	/**
	 * L'image actuelle à dessiner.
	 */
	protected BufferedImage image;
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
		image = ImageStore.get(name);
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void update(long delta) {
		//Rien à faire dans une tuile normale.
	}

	@Override
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public boolean equals(Object o) {
		if (o instanceof Tile) {
			return ((Tile)o).getName().equals(getName());
		}
		return false;
	}
}
