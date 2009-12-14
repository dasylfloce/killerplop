package fr.emn.killerplop.game.entities.shapes;

public class NullShape extends Shape {

	@Override
	public boolean contains(double x, double y) {
		return false;
	}

	@Override
	public int getType() {
		return NULL_SHAPE;
	}

	@Override
	protected boolean intersectsWithCircle(CircleShape circle) {
		return false;
	}

	@Override
	protected boolean intersectsWithRect(RectShape rect) {
		return false;
	}

}
