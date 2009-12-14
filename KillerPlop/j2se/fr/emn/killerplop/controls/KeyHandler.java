package fr.emn.killerplop.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import fr.emn.killerplop.game.entities.ship.ShipEntity;

public class KeyHandler extends KeyAdapter{
	
	private ShipEntity ship;
		
	public ShipEntity getShip() {
		return ship;
	}

	public void setShip(ShipEntity ship) {
		this.ship = ship;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: this.getShip().setHmove(-1);break;
		case KeyEvent.VK_RIGHT: this.getShip().setHmove(1);break;
		case KeyEvent.VK_UP: this.getShip().setVmove(-1);break;
		case KeyEvent.VK_DOWN: this.getShip().setVmove(1);break;
		case KeyEvent.VK_SPACE: this.getShip().setShooting(true);break;
		default: break;
		}		
		*/
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.getShip().setHmove(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.getShip().setHmove(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			this.getShip().setVmove(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.getShip().setVmove(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			this.getShip().setShooting(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: this.getShip().setHmove(0);break;
		case KeyEvent.VK_RIGHT: this.getShip().setHmove(0);break;
		case KeyEvent.VK_UP: this.getShip().setVmove(0);break;
		case KeyEvent.VK_DOWN: this.getShip().setVmove(0);break;
		case KeyEvent.VK_SPACE: this.getShip().setShooting(false);break;
		default: break;
		}
		*/		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.getShip().setHmove(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.getShip().setHmove(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			this.getShip().setVmove(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.getShip().setVmove(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			this.getShip().setShooting(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
