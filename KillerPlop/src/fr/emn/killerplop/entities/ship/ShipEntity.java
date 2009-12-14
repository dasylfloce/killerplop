package fr.emn.killerplop.entities.ship;

import fr.emn.killerplop.constants.Constants;
import fr.emn.killerplop.controller.gamecontroller.GameController;
import fr.emn.killerplop.entities.EntityImpl;
import fr.emn.killerplop.exceptions.OutOfMapException;
import fr.emn.killerplop.sprites.Sprite;

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
		double Vspeed = Vmove*this.shipSpeed;
		double Hspeed = Hmove*this.shipSpeed;
		if((getX()<gameController.getX() && Hmove == -1) ||
			(getX()>gameController.getX()+gameController.getViewWidth()-40 && Hmove == 1)){
			Hspeed = 0;
		}
		if((getY()<gameController.getY() && Vmove == -1)|| 
			(getY()>gameController.getY()+gameController.getViewHeight()-30 && Vmove == 1)){
			Vspeed = 0;
		}
		this.setHorizontalMovement(gameController.getHorizontalMovement()+Hspeed);
		this.setVerticalMovement(gameController.getVerticalMovement()+Vspeed);
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
	
	@Override
	public void hit() {
		destroyed = true;
	}

	@Override
	public void isOutOfMap(OutOfMapException e) {
		e.printStackTrace();
		System.err.println("Ship out of the map !? (x:"+x+"; y:"+y);
		System.exit(0);
	}

}
