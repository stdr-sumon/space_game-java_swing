package myjavagame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public Rectangle btnPlay = new Rectangle(GameMain.WIDTH/2 +120,150,100,50); 
	public Rectangle btnQuit = new Rectangle(GameMain.WIDTH/2 +120,250,100,50);
	
	public void render(Graphics g){
		Graphics2D g2d= (Graphics2D)g;
		
		Font fnt = new Font("arial", Font.BOLD,50);
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("SPACE SHOOTER", GameMain.WIDTH/3, 100);
		
		Font listFont = new Font("Bold Italic", Font.BOLD,30);
		g.setFont(listFont);
		g.drawString("PLAY",btnPlay.x+10,btnPlay.y+35);
		g2d.draw(btnPlay);
		g.drawString("QUIT",btnQuit.x+10,btnQuit.y+35);
		g2d.draw(btnQuit);
	}
}
