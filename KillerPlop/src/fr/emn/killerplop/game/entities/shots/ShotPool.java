package fr.emn.killerplop.game.entities.shots;

import java.util.LinkedList;
import java.util.List;

import fr.emn.killerplop.game.constants.Constants;

public class ShotPool implements Constants {

	private List<ShotEntity> available = new LinkedList<ShotEntity>();
	static private ShotPool instance = new ShotPool(NB_SHOTS_MAX);

	static public ShotPool getInstance() {
		return instance;
	}

	private ShotPool(int nbShots) {
		for (int i = 0; i < nbShots; i++) {
			available
					.add(new ShotEntity());
		}
	}

	public ShotEntity getShot() {
		return (available.size() > 0 ? available.remove(0) : null);
	}

	public void returnShot(ShotEntity shot) {
		shot.reactivate();
		available.add(shot);
	}
}
