package entities.shapes;

public class ShapeRectangle extends Shape {

	protected double width;
	protected double height;

	public ShapeRectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int getType() {
		return SHAPE_RECTANGLE;
	}

	@Override
	protected boolean intersectsWithCircle(ShapeCircle circle) {
		if (circle.y < this.x) {
			// Centre du cercle au dessus (peut-�tre � gauche ou � droite du
			// rectangle)
			// 3 cas : � gauche, � droite, ou au milieu
			if (circle.x < this.x) // � gauche
				return distance(this.x, this.y, circle.x, circle.y) <= circle
						.getRadius();
			else if (circle.x > this.x) // � droite
				return distance(this.x + this.width, this.y, circle.x, circle.y) <= circle
						.getRadius();
			else
				// au milieu
				return circle.x + circle.getRadius() >= this.y;

		} else if (circle.y > this.y) {
			// Centre du cercle en dessous (peut-�tre � gauche ou � droite du
			// rectangle)
			// 3 cas : � gauche, � droite, ou au milieu
			if (circle.x < this.x) // � gauche
				return distance(this.x, this.y + this.height, circle.x,
						circle.y) <= circle.getRadius();
			else if (circle.x > this.x) // � droite
				return distance(this.x + this.width, this.y + this.height,
						circle.x, circle.y) <= circle.getRadius();
			else
				// au milieu
				return circle.x - circle.getRadius() <= this.y + this.height;

		} else if (circle.x < this.x) {
			// Centre du cercle � gauche (au m�me niveau)
			return circle.x + circle.getRadius() >= this.x;
		} else if (circle.x > this.x) {
			// Centre du cercle � droite (au m�me niveau)
			return circle.x - circle.getRadius() <= this.x + this.width;
		} else {
			// Centre du cercle dans le rectangle
			return true;
		}
	}

	@Override
	protected boolean intersectsWithRect(ShapeRectangle rect) {
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

	public String toString() {
		return "x:" + x + "; y:" + y + " [width=" + width + " | height="
				+ height + "]";
	}
	
	public String getBoundsToString() {
		return "("+x+", "+y+", "+(x+width)+", "+(y+height)+")";
	}

}
