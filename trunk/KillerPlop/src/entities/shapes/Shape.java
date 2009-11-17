package entities.shapes;


public abstract class Shape {

	protected static final int SHAPE_CIRCLE = 0;
	protected static final int SHAPE_RECTANGLE = 1;

	protected double x;
	protected double y;

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean intersectsWith(Shape shape) {
		if (shape.getType() == SHAPE_CIRCLE)
			return intersectsWithCircle((ShapeCircle) shape);
		else if (shape.getType() == SHAPE_RECTANGLE)
			return intersectsWithRect((ShapeRectangle) shape);
		else {
			System.err.println("Error Shape[" + getType()
					+ "].intersectsWith(shape[" + shape.getType() + "])");
			System.exit(-1);
		}
		return false;

	}

	protected abstract boolean intersectsWithCircle(ShapeCircle circle);

	protected abstract boolean intersectsWithRect(ShapeRectangle rect);

	public abstract int getType();

	public abstract boolean contains(double x, double y);

	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public static void main(String[] args) {
		ShapeRectangle rect1 = new ShapeRectangle(10, 10);
		rect1.setPosition(100, 50);
		ShapeRectangle rect2 = new ShapeRectangle(20, 5);
		for (int y = 40; y < 130; y += 3) {
		for (int x = 80; x < 140; x += 7) {
				rect2.setPosition(x, y);
				System.out.println(rect1.getBoundsToString() + "\n" + rect2.getBoundsToString() + "\n\t"
						+ rect1.intersectsWith(rect2));
			}
		}
		
		rect2.setPosition(81, 52);
		System.out.println("\n\n"+rect1.getBoundsToString() + "\n" + rect2.getBoundsToString()+"\n"+ rect1.intersectsWith(rect2)+"\n");
		//System.out.println(rect2.contains(x2, y2))
	}
}
