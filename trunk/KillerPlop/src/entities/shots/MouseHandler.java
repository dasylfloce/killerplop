package entities.shots;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import entities.ship.ShipEntity;

public class MouseHandler extends MouseAdapter{
	
	private ShipEntity ship;
	
	
	
	public ShipEntity getShip() {
		return ship;
	}



	public void setShip(ShipEntity ship) {
		this.ship = ship;
	}



	public void mousePressed(MouseEvent e){
		
	}

}
