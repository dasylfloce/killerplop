package entities.ship;

import Constants.Constants;
import controller.GameController;
import entities.EntityImpl;
import entities.sprites.Sprite;

public class ShipEntity extends EntityImpl implements Constants{
	
	private int Vmove, Hmove;
	private boolean isShooting;
	private double shipSpeed;
	private long lastShoot;
		
	public ShipEntity(Sprite sprite, int x, int y, double shipSpeed) {
		super(sprite, x, y);
		this.shipSpeed = shipSpeed;
		this.Vmove = 0;
		this.Hmove = 0;
		this.lastShoot = SHOOTDELAY;
		this.isShooting = false;
	}

	public int getVmove() {
		return Vmove;
	}

	public void setVmove(int vmove) {
		Vmove = vmove;
	}

	public int getHmove() {
		return Hmove;
	}

	public void setHmove(int hmove) {
		Hmove = hmove;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		if((lastShoot >= SHOOTDELAY)||(!isShooting)){
			this.isShooting = isShooting;
			if(isShooting){
				lastShoot = 0;
			}			
		}		
	}
	
	@Override
	public void calculateSpeed(GameController gameController) {
		lastShoot += gameController.getDelta();
		this.setHorizontalMovement(gameController.getHorizontalMovement()+Hmove*this.shipSpeed);
		this.setVerticalMovement(gameController.getVerticalMovement()+Vmove*this.shipSpeed);
	}

	@Override
	public boolean isAlienEntity() {
		return false;
	}

	@Override
	public boolean isShipEntity() {
		return true;
	}

	@Override
	public boolean isShotEntity() {
		return false;
	}

}
