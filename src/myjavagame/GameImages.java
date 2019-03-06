package myjavagame;

import java.awt.image.BufferedImage;

public class GameImages {
	
	public BufferedImage player, bullet, enemy,collide;
	
	private SpriteSheet ss = null;
	
	public GameImages(GameMain myGame){
		ss = new SpriteSheet(myGame.getSpriteSheet());
		getGameImages();
	}
	private void getGameImages(){
		player = ss.grabImage(1, 1, 32, 32);
		bullet = ss.grabImage(3,1, 32, 32);
		enemy = ss.grabImage(2,1, 32, 32);
		collide = ss.grabImage(4,1, 32, 32);
	}
}
