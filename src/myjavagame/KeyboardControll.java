package myjavagame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardControll extends KeyAdapter{
	GameMain spaceGame;
	public KeyboardControll(GameMain spaceGame){
		this.spaceGame = spaceGame;
	}
	public void keyPressed(KeyEvent e){
		spaceGame.keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		spaceGame.keyReleased(e);
	}
}
