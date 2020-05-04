package dev.logflames.platformer.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.statics.StaticEntity;
import dev.logflames.platformer.tiles.Tile;

public class Particles {

	private float x, y;
	private int size;
	private int life;
	private Color color;

	private int delay = 0;

	private Handler handler;
	private float gravity = 0.4f;

	private float velX, velY;
	private float accX, accY;

	private boolean inWall = false;
	private boolean playerBlod;

	private int timer;

	private BufferedImage texture = null;

	public Particles(Handler handler, float x, float y, int size, int life, Color color, float velX, float velY,
			boolean playerBlod) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.size = size;
		this.life = life;
		this.color = color;
		this.velX = velX;
		this.velY = velY;
		this.playerBlod = playerBlod;
	}

	public Particles(Handler handler, float x, float y, int size, int life, Color color, float velX, float velY,
			boolean playerBlod, float gravity) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.size = size;
		this.life = life;
		this.color = color;
		this.velX = velX;
		this.velY = velY;
		this.playerBlod = playerBlod;
		this.gravity = gravity;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public void tick() {
		if (delay > 0) {
			delay--;
		}
		if (gravity == 0f && inWall) {
			delete();
		}
		testLava();

		if (inWall) {
			life--;
		} else if (playerBlod) {
			life--;
		}
		if (timer > 0) {
			timer--;
			return;
		}
		if (!inWall) {
			applyForce(0, gravity);
			updateForce();
		}
		testCollision();
	}

	public void render(Graphics g) {
		if (texture != null) {
			g.drawImage(texture, ((int) (x - (size / 2) - handler.getGameCamera().getxOffset())),
					(int) (y - (size / 2) - handler.getGameCamera().getyOffset()), size, size, null);
		} else {
			g.setColor(color);
			g.fillRect((int) (x - (size / 2) - handler.getGameCamera().getxOffset()),
					(int) (y - (size / 2) - handler.getGameCamera().getyOffset()), size, size);
		}
	}

	public int getLife() {
		return life;
	}

	public void applyForce(float x, float y) {
		accX += x;
		accY += y;
	}

	private void updateForce() {
		velX += accX;
		velY += accY;
		accX = accY = 0;
		velX *= 0.95f;
		velY *= 0.95f;
		x += velX;
		y += velY;
	}

	@SuppressWarnings("deprecation")
	private void testCollision() {
		if (handler.getWorld().getTile((int) (x / Tile.TILEWIDTH), (int) (y / Tile.TILEHEIGHT)).isSolid()) {
			x -= velX;
			y -= velY;
			inWall = true;
			if (playerBlod) {
				timer = 80;
			}
		}
		for (StaticEntity e : handler.getWorld().getTile((int) (x / Tile.TILEWIDTH), (int) (y / Tile.TILEHEIGHT))
				.getStatics()) {
			if (e.getCollisionBounds(0, 0).inside((int) x, (int) y)) {
				x -= velX;
				y -= velY;
				inWall = true;
				if (playerBlod) {
					timer = 80;
				}
			}
		}
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void delete() {
		life = 0;
	}

	public void testLava() {
		int tileX = (int) (x / Tile.TILEWIDTH);
		int tileY = (int) (y / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenParticleIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);
	}

	public void setShowDelay(int delay) {
		this.delay = delay;
	}
}
