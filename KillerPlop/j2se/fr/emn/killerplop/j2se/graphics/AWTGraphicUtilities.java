package fr.emn.killerplop.j2se.graphics;

import fr.emn.killerplop.game.entities.shapes.RectShape;
import fr.emn.killerplop.graphics.context.GraphicUtilities;

public class AWTGraphicUtilities extends GraphicUtilities {

	@Override
	public int getHeightOf(String imageRef) {
		return AWTImageStore.get(imageRef).getHeight();
	}

	@Override
	public int getWidthOf(String imageRef) {
		return AWTImageStore.get(imageRef).getWidth();
	}
	
	@Override
	public void loadImage(String imageRef) {
		AWTImageStore.load(imageRef);
	}

	@Override
	public RectShape getImageShape(String imageRef) {
		return new RectShape(GraphicUtilities.get().getWidthOf(imageRef),
				GraphicUtilities.get().getHeightOf(imageRef));
		}

}
