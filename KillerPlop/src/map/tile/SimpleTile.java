package map.tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
	 * @param image Image de la tuile
	 * @param name nom de la tuile
	 */
	public SimpleTile(String name, BufferedImage image) {
		// TODO Générer exception si image == null
		this.image = image;
		this.name = name;
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

	@Override
	public void convert(int imageType) {
		if (image.getType() == imageType)
			return;

		// conversion
		BufferedImage i = new BufferedImage(getWidth(), getHeight(),
				imageType); // nouveau format
		i.setAccelerationPriority(1.0f);
		Graphics g = i.createGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();

		image = i;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Tile) {
			return ((Tile)o).getName().equals(getName());
		}
		return false;
	}
}
