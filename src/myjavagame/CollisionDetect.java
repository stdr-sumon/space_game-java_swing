package myjavagame;

import collision_detect.BulletEntity;
import collision_detect.EnemyEntity;

public class CollisionDetect {
	GameImages col;
	public static boolean Collision(BulletEntity entb, EnemyEntity ente){
			if(entb.getBounds().intersects(ente.getBounds())){
				return true;
			}         
		return false;
	}
	public static boolean Collision(EnemyEntity ente, BulletEntity entb){
			if(ente.getBounds().intersects(entb.getBounds())){
				return true;
			}          
		return false;
	}	
}
