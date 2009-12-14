package fr.emn.killerplop.graphics.context;

import fr.emn.killerplop.game.entities.shapes.RectShape;

public abstract class GraphicUtilities {
	
	public static final String imgPackage = "fr/emn/killerplop/resources/";
	
	private static GraphicUtilities me;

	public static GraphicUtilities get() {
		return me;
	}

	public static void set(GraphicUtilities me) {
		GraphicUtilities.me = me;
	}
	
	public abstract int getWidthOf(String imageRef);
	public abstract int getHeightOf(String imageRef);

	public abstract void loadImage(String imageRef);
	
	public abstract RectShape getImageShape(String imageRef);
}
