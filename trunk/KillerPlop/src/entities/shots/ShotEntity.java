package entities.shots;

import entities.aliens.AlienEntity;
import entities.movement.Movement;
import entities.sprites.Sprite;

public class ShotEntity extends AlienEntity {

	public ShotEntity(Sprite sprite, int y, Movement movement,
			int activationPoint) {
		super(sprite, activationPoint, y, movement);
		// TODO Auto-generated constructor stub
	}

}
