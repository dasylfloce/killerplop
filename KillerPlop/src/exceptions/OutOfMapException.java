package exceptions;

import map.maptiled.MapTiled;

@SuppressWarnings("serial")
public class OutOfMapException extends Exception {
	
	protected MapTiled map;
	protected double x;
	protected double y;
	public OutOfMapException(MapTiled map, double x, double y) {
		super();
		this.map = map;
		this.x = x;
		this.y = y;
	}
	
	public String getMessage() {
		return "Invalid position : ("+x+"; "+y+")\n\tMap size : [x:"+map.getMapWidth()*map.getTileWidth()+"; y:"+map.getMapHeight()*map.getTileHeight()+"]"; 
	}
}