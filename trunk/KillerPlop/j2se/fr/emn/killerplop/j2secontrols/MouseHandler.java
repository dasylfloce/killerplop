package fr.emn.killerplop.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.emn.killerplop.game.entities.ship.ShipEntity;

public class MouseHandler extends MouseAdapter{
	
	private ShipEntity ship;
	
	
	
	public ShipEntity getShip() {
		return ship;
	}



	public void setShip(ShipEntity ship) {
		this.ship = ship;
	}



	@Override
	public void mousePressed(MouseEvent e){
		
	}

}
