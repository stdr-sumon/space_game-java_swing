package myjavagame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseControll implements MouseListener {
	GameMain spaceGame;
	public static boolean run = false;
	public MouseControll(GameMain spaceGame){
		this.spaceGame = spaceGame;
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my =e.getY();
		
		//PLAY BUTTON
		if(mx >= GameMain.WIDTH/2 + 120 && mx <= GameMain.WIDTH/2 + 220){
			if(my>=150 && my <=200){
				GameMain.score = 0;
				GameMain.state = GameMain.STATE.GAME;
				run = true;
			}
		}
		//QUIT BUTTON
		if(mx >= GameMain.WIDTH/2 + 120 && mx <= GameMain.WIDTH/2 + 220){
			if(my>=250 && my <=300){
				if(run==false)
				System.exit(1); 
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		
		
	}
}
