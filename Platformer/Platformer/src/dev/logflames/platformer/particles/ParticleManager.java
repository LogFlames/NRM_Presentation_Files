package dev.logflames.platformer.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.logflames.platformer.Handler;

public class ParticleManager {
	
	private Handler handler;

	private ArrayList<Particles> particles;

	public ParticleManager(Handler handler) {
		this.handler = handler;
		particles = new ArrayList<Particles>();
	}
	
	public void addParticle(float x, float y, int size, int life, Color color, float velX, float velY, boolean playerBlod) {
		particles.add(new Particles(handler, x, y, size, life, color, velX, velY, playerBlod));
	}
	
	public void addParticle(float x, float y, int size, int life, Color color, float velX, float velY, boolean playerBlod, float gravity) {
		particles.add(new Particles(handler, x, y, size, life, color, velX, velY, playerBlod, gravity));
	}
	
	public void addParticle(Particles p) {
		particles.add(p);
	}
	
	public void tick() {
		Iterator<Particles> it = particles.iterator();
		while (it.hasNext()) {
			Particles e = it.next();
			e.tick();
			if (e.getLife() <= 0) {
				it.remove();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Particles p : particles) {
			p.render(g);
		}
	}
	
	public void clearPlarticles() {
		particles = new ArrayList<Particles>();
	}
}
