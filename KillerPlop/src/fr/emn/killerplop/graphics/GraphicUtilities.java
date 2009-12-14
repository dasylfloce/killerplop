package fr.emn.killerplop.graphics;

public abstract class GraphicUtilities {
	
	private static GraphicUtilities me;

	public static GraphicUtilities get() {
		return me;
	}

	public static void setMe(GraphicUtilities me) {
		GraphicUtilities.me = me;
	}
	
	public abstract int getWidthOf(String imageRef);
	public abstract int getHeightOf(String imageRef);

}
