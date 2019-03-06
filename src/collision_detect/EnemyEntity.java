package collision_detect;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EnemyEntity {
	
	public void tick();
	public void render(Graphics g, boolean b);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
