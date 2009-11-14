package entities.shots;

import java.util.LinkedList;
import java.util.List;

import Constants.Constants;
import entities.sprites.SpriteStore;

public class ShotPool implements Constants{
	
	static public final int MAX_RESOURCES = NB_SHOTS_MAX;
	private List<ShotEntity> available = new LinkedList<ShotEntity>();
	static private ShotPool instance = new ShotPool(MAX_RESOURCES);
	
	static public ShotPool getInstance(){ 
		return instance;
	}
	
	private ShotPool(int nbShots){
		for (int i=0; i<nbShots; i++){ 
			available.add(new ShotEntity(SpriteStore.get().getSprite("resources/sprites/shot.gif"), 0, 0));
		}
	}
	
	public ShotEntity getShot(){
		return (available.size()>0 ? available.remove(0) : null);
	}
	
	public void returnShot(ShotEntity shot){
		available.add(shot);
	}
}
