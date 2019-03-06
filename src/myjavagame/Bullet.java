package myjavagame;

import java.awt.Graphics;
import java.awt.Rectangle;

import collision_detect.BulletEntity;

public class Bullet extends GameObject implements BulletEntity {
	
	private GameImages gImg;
	
	public Bullet(double x, double y, GameImages gImg, GameMain myGame){
		super(x,y);
		this.gImg = gImg;
	}
	
	public void tick(){
		y-=6;
	}
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x,(int)y, width, height);
	}
	public void render(Graphics g){
		g.drawImage(gImg.bullet,(int) x, (int) y, null);
	}
	public double getY(){
		return y;
	}
	public double getX(){
		return x;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y, 32, 32);
	}
}
