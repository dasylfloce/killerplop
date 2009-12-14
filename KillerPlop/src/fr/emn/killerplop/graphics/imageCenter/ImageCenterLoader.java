package fr.emn.killerplop.graphics.imageCenter;

import fr.emn.killerplop.graphics.context.GraphicUtilities;

public class ImageCenterLoader implements ImageCenter {
	
	public static void loadAllImages() {
		GraphicUtilities gu = GraphicUtilities.get();
		
		//Ship
		gu.loadImage(SHIP_1);
		gu.loadImage(SHIP_2);
		//Explosion
		gu.loadImage(EXPLOSION_1);
		gu.loadImage(EXPLOSION_2);
		gu.loadImage(EXPLOSION_3);
		gu.loadImage(EXPLOSION_4);
		gu.loadImage(EXPLOSION_5);
		//Shot
		gu.loadImage(SHIP_SHOT);

		//Map test
		gu.loadImage(TILE_ASTEROIDE);
		gu.loadImage(TILE_NOIRE);
		gu.loadImage(BG_SPACE);
		
		//Sonic bleu
		gu.loadImage(SONIC_BLEU_1);
		gu.loadImage(SONIC_BLEU_2);
		gu.loadImage(SONIC_BLEU_3);
		//Sonic or
		gu.loadImage(SONIC_OR_1);
		gu.loadImage(SONIC_OR_2);
		gu.loadImage(SONIC_OR_3);

		//Boss
		gu.loadImage(BOSS_1);
		gu.loadImage(BOSS_2);
		gu.loadImage(BOSS_3);
		gu.loadImage(BOSS_4);
	}

}
