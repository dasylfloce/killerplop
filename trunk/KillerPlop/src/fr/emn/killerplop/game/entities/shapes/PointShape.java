package fr.emn.killerplop.game.entities.shapes;

public class PointShape extends Shape {
	
	protected double offsetX;
	protected double offsetY;
	
	public PointShape(double offsetX, double offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x+offsetX, y+offsetY);
	}

	@Override
	public boolean contains(double x, double y) {
		return this.x == x && this.y == y;
	}

	@Override
	public int getType() {
		return POINT_SHAPE;
	}

	@Override
	protected boolean intersectsWithCircle(CircleShape circle) {
		return circle.contains(this.x, this.y);
	}

	@Override
	protected boolean intersectsWithRect(RectShape rect) {
		return rect.contains(this.x, this.y);
	}

}
