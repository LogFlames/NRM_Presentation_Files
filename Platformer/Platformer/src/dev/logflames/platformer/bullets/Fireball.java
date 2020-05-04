package dev.logflames.platformer.bullets;

import java.awt.image.BufferedImage;

import dev.logflames.platformer.Handler;

public class Fireball extends Bullet {

	public Fireball(Handler handler, BufferedImage[] img, int animSpeed, int x, int y, int width, int height, int velX, int velY) {
		super(handler, img, animSpeed, x, y, width, height, velX, velY);
		// TODO: Fireball "Assets.fireball"
	}

	@Override
	public void tick() {
		
	}
}
