package myjavagame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import collision_detect.BulletEntity;
import collision_detect.EnemyEntity;

public class Controller {
	private LinkedList<BulletEntity> ea = new LinkedList<BulletEntity>();
	public LinkedList<EnemyEntity> eb = new LinkedList<EnemyEntity>();
	public static EnemyEntity gp =null;
	BulletEntity entb;
	EnemyEntity	ente;
	private GameImages gImg;
	Random r = new Random();
	
	private GameMain myGame;
	int timeFlag = 10;
	public Controller(GameImages gImg, GameMain myGame){
		this.gImg = gImg;
		this.myGame = myGame;
	}
	
	public void createEnemy(int enemy_count){
		for(int i = 0; i < enemy_count; i++){
			addGameEntity(new Enemy(r.nextInt(640), -10, gImg, this, myGame));
		}
	}
	
	public void tick(){
		//Bullet Interface
		for(int i = 0; i<ea.size(); i++){
			entb = ea.get(i);
			
			entb.tick();
		}
		//Enemy Interface
				for(int i = 0; i<eb.size(); i++){
					ente = eb.get(i);
					
					ente.tick();
				}
	}
	public void render(Graphics g){
		
		
		
		//Bullet Interface
		for(int i = 0; i<ea.size();i++){
			entb = ea.get(i);
			
			entb.render(g);
		}
		//Enemy Interface
				for(int i = 0; i<eb.size();i++){
					if(gp!=null){
						gp.render(g, true);
					}
					ente = eb.get(i);
					
					ente.render(g,false);
				}
				timeFlag--;
				if(timeFlag<0){
					gp=null;
				}
				g.setColor(Color.GRAY);
				g.drawString(String.valueOf("Score : "+GameMain.score), 550, 20);			
			
	}
	public void addGameEntity(BulletEntity block){
		ea.add(block);
	}
	public void removeGameEntity(BulletEntity block){
		ea.remove(block);
	}
	public void addGameEntity(EnemyEntity block){
		
		eb.add(block);
	}
	public void removeGameEntity(EnemyEntity block){
	
		gp = block;
		timeFlag=10;
		eb.remove(block);
	}
	public LinkedList<BulletEntity> getBulletEntity(){
			return ea;
	}
	public LinkedList<EnemyEntity> getEnemyEntity(){
		return eb;
	}
	
}
