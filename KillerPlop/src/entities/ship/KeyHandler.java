package entities.ship;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: ShipEntity.Hmove = -1;break;
		case KeyEvent.VK_RIGHT: ShipEntity.Hmove = 1;break;
		case KeyEvent.VK_UP: ShipEntity.Vmove = -1;break;
		case KeyEvent.VK_DOWN: ShipEntity.Vmove = 1;break;
		case KeyEvent.VK_SPACE: ShipEntity.isShooting = true;break;
		default: break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: ShipEntity.Hmove = 0;break;
		case KeyEvent.VK_RIGHT: ShipEntity.Hmove = 0;break;
		case KeyEvent.VK_UP: ShipEntity.Vmove = 0;break;
		case KeyEvent.VK_DOWN: ShipEntity.Vmove = 0;break;
		case KeyEvent.VK_SPACE: ShipEntity.isShooting = false;break;
		default: break;
		}		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
