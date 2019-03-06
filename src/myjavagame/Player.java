package myjavagame;

import java.awt.Graphics;
import java.awt.Rectangle;

import collision_detect.BulletEntity;
import collision_detect.EnemyEntity;

public class Player extends GameObject implements BulletEntity{
	private double velX = 0;
	private double velY = 0;
	
	GameMain myGame;
	Controller controller;
	private GameImages gImg;
	
	public Player(double x, double y, GameImages gImg, GameMain myGame, Controller controller){
		super(x,y);
		this.gImg = gImg;
		this.myGame = myGame;
		this.controller = controller;
	}
	public void tick(){
		x+= velX;
		y+= velY;
		
		if(x<=0)
			x=0;
		if(x>=625)
			x = 625;    //for bounding the Rocket in Screen
		if(y<=0)
			y=0;
		if(y>=445)
			y = 445;
		
		for(int i = 0; i < myGame.eb.size();i++){
			EnemyEntity tempEnt = myGame.eb.get(i);
			
			if(CollisionDetect.Collision(this, tempEnt)){
				controller.removeGameEntity(tempEnt);
				GameMain.HEALTH-=50;
				GameMain.enemy_killed+=1;
				if(GameMain.HEALTH<=0){
					GameMain.gameover=true;
				}
			}
		}
	}
	
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x,(int)y, width, height);
	}
	
	public void render(Graphics g){
		g.drawImage(gImg.player, (int)x, (int)y, null);
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y, 32, 32);
	}
	
}
