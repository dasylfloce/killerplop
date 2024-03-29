package fr.emn.killerplop.game.entities.shapes;

public class CircleShape extends Shape {

	protected double radius;

	public CircleShape(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x+radius, y+radius);
	}

	@Override
	public int getType() {
		return CIRCLE_SHAPE;
	}

	@Override
	protected boolean intersectsWithCircle(CircleShape circle) {
		return distance(x, y, circle.x, circle.y) < radius
		+ circle.radius;
	}

	@Override
	protected boolean intersectsWithRect(RectShape rect) {
		return rect.intersectsWithCircle(this);
	}

	@Override
	public boolean contains(double x, double y) {
		return distance(this.x, this.y, x, y) <= radius;
	}
	
	@Override
	public String toString() {
		return "Circle => x:"+x+"; y:"+y+" [radius="+radius+"]";
	}

	@Override
	public double getHeight() {
		return 2*radius;
	}

	@Override
	public double getWidth() {
		return 2*radius;
	}

}
