package myjavagame;
 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import collision_detect.BulletEntity;
import collision_detect.EnemyEntity;

public class Enemy extends GameObject implements EnemyEntity {
	
	private GameImages gImg;
	Random r = new Random();
	
	public static GameMain myGame;
	private Controller c;
	
	int enemySpeed = r.nextInt(3)+1;
	
	public Enemy(double x, double y, GameImages gImg, Controller c, GameMain myGame){
		super(x,y);
		this.gImg = gImg;
		this.c = c;
		Enemy.myGame = myGame;
	}
	public void tick(){
		y +=enemySpeed;
		if (y>GameMain.HEIGHT * GameMain.SCALE){
			enemySpeed = r.nextInt(3)+1;
			x = r.nextInt(640);
			y = -10;
		}
		
		for(int i = 0; i < myGame.ea.size(); i++){
			BulletEntity tempEnt = myGame.ea.get(i);
			if(CollisionDetect.Collision(this, tempEnt)){
				c.removeGameEntity(tempEnt);
				c.removeGameEntity(this);
				GameMain.enemy_killed+=1;
			    GameMain.score+=1;
			}
		}
		
		
	}
	
	public void render(Graphics g, boolean flag){
		if(flag==false)
		g.drawImage(gImg.enemy, (int)x, (int)y, null);
		else 
		g.drawImage(gImg.collide, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y, 32, 32);
	}
	
	public double getY(){
		return y;
	}
	public double getX(){
		return x;
	}
}
