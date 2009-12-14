package fr.emn.killerplop.game.entities.shapes;

public class RectShape extends Shape {

	protected double width;
	protected double height;

	public RectShape(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int getType() {
		return RECTANGLE_SHAPE;
	}

	@Override
	protected boolean intersectsWithCircle(CircleShape circle) {
		if (circle.y < this.x) {
			// Centre du cercle au dessus (peut-être à gauche ou à droite du
			// rectangle)
			// 3 cas : à gauche, à droite, ou au milieu
			if (circle.x < this.x) // à gauche
				return distance(this.x, this.y, circle.x, circle.y) <= circle
						.getRadius();
			else if (circle.x > this.x) // à droite
				return distance(this.x + this.width, this.y, circle.x, circle.y) <= circle
						.getRadius();
			else
				// au milieu
				return circle.x + circle.getRadius() >= this.y;

		} else if (circle.y > this.y) {
			// Centre du cercle en dessous (peut-être à gauche ou à droite du
			// rectangle)
			// 3 cas : à gauche, à droite, ou au milieu
			if (circle.x < this.x) // à gauche
				return distance(this.x, this.y + this.height, circle.x,
						circle.y) <= circle.getRadius();
			else if (circle.x > this.x) // à droite
				return distance(this.x + this.width, this.y + this.height,
						circle.x, circle.y) <= circle.getRadius();
			else
				// au milieu
				return circle.x - circle.getRadius() <= this.y + this.height;

		} else if (circle.x < this.x) {
			// Centre du cercle à gauche (au même niveau)
			return circle.x + circle.getRadius() >= this.x;
		} else if (circle.x > this.x) {
			// Centre du cercle à droite (au même niveau)
			return circle.x - circle.getRadius() <= this.x + this.width;
		} else {
			// Centre du cercle dans le rectangle
			return true;
		}
	}

	@Override
	protected boolean intersectsWithRect(RectShape rect) {
		double maxLeft = Math.max(x, rect.x);
		double minRight = Math.min(x+width, rect.x+rect.width);

		double maxUp = Math.max(y, rect.y);
		double minDown = Math.min(y+height, rect.y+rect.height);

		return maxLeft <= minRight && maxUp <= minDown;
	}

	@Override
	public boolean contains(double x2, double y2) {
		return x <= x2 && x + width >= x2 && y <= y2 && y + height >= y2;
	}

	@Override
	public String toString() {
		return "x:" + x + "; y:" + y + " [width=" + width + " | height="
				+ height + "]";
	}
	
	public String getBoundsToString() {
		return "("+x+", "+y+", "+(x+width)+", "+(y+height)+")";
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

}
