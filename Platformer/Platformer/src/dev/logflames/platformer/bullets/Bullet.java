package dev.logflames.platformer.bullets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Animation;
import dev.logflames.platformer.tiles.Tile;

public abstract class Bullet {

	protected Animation anim;

	protected BufferedImage[] img;

	protected Handler handler;

	protected boolean toDelete = false;

	protected int x, y;
	protected Rectangle bounds;
	protected int width, height;

	protected int velX, velY;

	public Bullet(Handler handler, BufferedImage[] img, int animSpeed, int x, int y, int width, int height, int velX,
			int velY) {
		this.handler = handler;
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;

		bounds = new Rectangle(0, 0, width, height);
		anim = new Animation(animSpeed, img);
	}

	public abstract void tick();

	public void render(Graphics g) {
		g.drawImage(anim.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void move() {
		x += velX;
		y += velY;
	}

	public void hitTest() {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f, 0f))) {
				if (e.isStatic()) {
					delete();
				} else if (!e.getUnvulnerable()) {
					e.hurt(1);
				}
			}
		}
	}

	public void callAll() {
		hitTest();
		ofScreen();
		move();
		testTile();
		anim.tick();
	}

	public void ofScreen() {
		if ((int) (x - handler.getGameCamera().getxOffset()) < -width
				|| (int) (y - handler.getGameCamera().getyOffset()) < -height
				|| (int) (x - handler.getGameCamera().getxOffset()) > handler.getWidth() + width
				|| (int) (y - handler.getGameCamera().getyOffset()) > handler.getHeight() + height) {
			delete();
		}
	}

	public void delete() {
		toDelete = true;
	}

	public boolean getDelete() {
		return toDelete;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}
	
	public void testTile() {
		int tileX = (int) (x / Tile.TILEWIDTH);
		int tileY = (int) (y / Tile.TILEHEIGHT);
		
		if (handler.getWorld().getTile(tileX, tileY).isSolid()) {
			delete();
		}
		
		tileX = (int) ((x + bounds.width) / Tile.TILEWIDTH);
		tileY = (int) (y / Tile.TILEHEIGHT);
		
		if (handler.getWorld().getTile(tileX, tileY).isSolid()) {
			delete();
		}
		
		tileX = (int) ((x + bounds.width) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.height) / Tile.TILEHEIGHT);
		
		if (handler.getWorld().getTile(tileX, tileY).isSolid()) {
			delete();
		}
		
		tileX = (int) (x / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.height) / Tile.TILEHEIGHT);
		
		if (handler.getWorld().getTile(tileX, tileY).isSolid()) {
			delete();
		}				
	}
}
