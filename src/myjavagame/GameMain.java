package myjavagame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import collision_detect.BulletEntity;
import collision_detect.EnemyEntity;

public class GameMain extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=320;
	public static final int HEIGHT=WIDTH/12*9;
	public static final int SCALE = 2;
	private boolean running = false;
	private Thread thread;
	public static boolean gameover=false;
	private BufferedImage spriteSheet = null; 
	private BufferedImage background = null;
	
	private boolean is_shooting = false;
	private boolean firstTime=true;
	private int enemy_count = 4;
	public static int enemy_killed = 0;
	public static int score = 0;
	private Player p;
	private Controller c;
	private GameImages gImg;
	private Menu menu;
	
	public LinkedList<BulletEntity> ea;
	public LinkedList<EnemyEntity> eb;
	
	public static int HEALTH = 160;
	public static enum STATE{
		MENU,GAME;
	}; 
	public static STATE state = STATE.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/mySpritSheet.png");
			background = loader.loadImage("/backgroud.png");
		} catch (IOException e){
			e.printStackTrace();
		}
		
		gImg = new GameImages(this);
		c = new Controller(gImg,this);
		p = new Player(200, 200, gImg, this,c);
		menu = new Menu();
		
		ea = c.getBulletEntity();
		eb = c.getEnemyEntity();
		
		this.addKeyListener(new KeyboardControll(this));
		this.addMouseListener(new MouseControll(this));
		c.createEnemy(enemy_count);
	}
	private synchronized void start(){
		 if(running)
			 return;
		 running = true;
		 thread = new Thread(this);
		 thread.start();
	 }
	 private synchronized void stop(){
		 if(!running)
			 return;
		 running =  false;
		 try {
			thread.join();
		} catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.exit(1);
	 }
	 
		public void run(){
			// TODO Auto-generated method stub
			init();
			long lastTime = System.nanoTime();
			final double fpsTime=60.0;
			double ns = 1000000000/fpsTime;
			double delta =0;
			int updates = 0;
			int frames = 0;
			long timer = System.currentTimeMillis();
			while(running){
				long currentTime = System.nanoTime();
				delta += (currentTime-lastTime)/ns;
				lastTime = currentTime;
				if(delta>1){
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;
				if(System.currentTimeMillis()-timer>1000){
					timer+=1000;
					System.out.println(updates + "Ticks, FPS" + frames);
					updates = 0;frames=0;				}
			}
			stop();
		}
		private void tick(){
			if(state == STATE.GAME){
				p.tick();
				c.tick();
			}
			
			if(enemy_killed>=enemy_count){
				enemy_count += 2;
				enemy_killed = 0;
				c.createEnemy(enemy_count);
			}
		}
		private void render(){
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null){
				createBufferStrategy(4);
				return;
			}
			Graphics g = bs.getDrawGraphics();
			//////////////////////////
			
			g.drawImage(background, 0, 0, null);
			
			if(MouseControll.run==true){
				p.render(g);
				c.render(g); 
				
				g.setColor(Color.red);
				g.fillRect(5, 5, 160, 30);
				
				g.setColor(Color.green);
				g.fillRect(5, 5, HEALTH, 30);
				
				g.setColor(Color.white);
				g.drawRect(5, 5, 160, 30);
			
				if(gameover==true)
				{   HEALTH=160;
					MouseControll.run=false;
					gameover=false;
					firstTime=false;
				}
			}else{  
				
				
				menu.render(g);
				if(firstTime==false){	
					
					Graphics2D g2d= (Graphics2D)g;
					Font fnt = new Font("arial", Font.BOLD,30);
					g2d.setFont(fnt);
					g2d.setColor(Color.RED);
					g2d.drawString(" Game  Over", GameMain.WIDTH/2+70, 350);
					g2d.setColor(Color.RED);
					g2d.drawString("   Score : "+String.valueOf(score), GameMain.WIDTH/2+70, 400);
				}
			}
			
			//////////////////////////
			g.dispose();
			bs.show();
		}
		
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			
			if(state == STATE.GAME){
				if(key==KeyEvent.VK_D){
					p.setVelX(5);     //For Smooth Movement
					//p.setX(p.getX() + 5 );
				}else if(key==KeyEvent.VK_A){
					p.setVelX(-5);
					//p.setX(p.getX() - 5 );
				}else if(key==KeyEvent.VK_S){
					p.setVelY(5);
					//p.setY(p.getY() + 5 );
				}else if(key==KeyEvent.VK_W){
					
					p.setVelY(-5);
					//p.setY(p.getY() - 5 );
				}else if(key==KeyEvent.VK_SPACE && !is_shooting){
					is_shooting = true;
					c.addGameEntity(new Bullet(p.getX(),p.getY(),gImg,this));
				}
			}
		}
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_D){
				p.setVelX(0);     //For Smooth Movement
			}else if(key==KeyEvent.VK_A){
				p.setVelX(0);
			}else if(key==KeyEvent.VK_S){
				p.setVelY(0);
			}else if(key==KeyEvent.VK_W){
				p.setVelY(0);
			}else if(key==KeyEvent.VK_SPACE){
				is_shooting = false;
			}
		}
	 
	public static void main(String[] args){
		GameMain myGame = new GameMain();
		myGame.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		JFrame frame=new JFrame("Survibe on Space");
		frame.add(myGame);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGame.start();

	} 
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	public int getEnemy_count() {
		return enemy_count;
	}
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	public int getEnemy_killed() {
		return enemy_killed;
	}

}
