package entities.shapes;

public class CircleShape extends Shape {

	protected double radius;

	public CircleShape(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
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
	
	public String toString() {
		return "Circle => x:"+x+"; y:"+y+" [radius="+radius+"]";
	}

}
