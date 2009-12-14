package fr.emn.killerplop.game.entities.shots;

import java.util.LinkedList;
import java.util.List;

import fr.emn.killerplop.game.constants.Constants;

public class ShipShotPool implements Constants {

	private List<ShipShotEntity> available = new LinkedList<ShipShotEntity>();
	static private ShipShotPool instance = new ShipShotPool(NB_SHOTS_MAX);

	static public ShipShotPool getInstance() {
		return instance;
	}

	private ShipShotPool(int nbShots) {
		for (int i = 0; i < nbShots; i++) {
			available
					.add(new ShipShotEntity());
		}
	}

	public ShipShotEntity getShot() {
		return (available.size() > 0 ? available.remove(0) : null);
	}

	public void returnShot(ShipShotEntity shot) {
		shot.reactivate();
		available.add(shot);
	}
}
