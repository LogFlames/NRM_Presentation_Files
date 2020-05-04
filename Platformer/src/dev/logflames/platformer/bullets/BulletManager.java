package dev.logflames.platformer.bullets;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class BulletManager {
	
	private ArrayList<Bullet> bullets;
	
	public BulletManager() {
		bullets = new ArrayList<Bullet>();
	}
	
	public void tick() {
		Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet b = it.next();

			b.callAll();
			b.tick();
			if (b.getDelete()) {
				it.remove();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Bullet b : bullets) {
			b.render(g);
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public void clearBullets() {
		bullets = new ArrayList<Bullet>();
	}
}
