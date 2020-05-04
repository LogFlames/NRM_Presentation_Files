package dev.logflames.platformer.entities.creatures;

import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.tiles.Tile;

public class Creature extends Entity {

	public static final float DEAFAULT_SPEED = 5.0f;
	public static final float DEAFAULT_JUMP_FORCE = 20.0f;
	public static final int DEAFAULT_CREATURE_WIDTH = 64, DEAFAULT_CREATURE_HEIGHT = 64;

	protected float speed;
	protected float xMove, yMove;
	protected boolean inAir;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEAFAULT_SPEED;
		xMove = 0.0f;
		yMove = 0.0f;
	}

	public void move() {
		testSpike();
		inAir = true;
		if (!checkEntityCollision(xMove, 0)) {
			moveX();
		} else if (xMove > 0 && !checkEntityCollision(1, 0)) {
			float tempX = xMove;
			xMove = 1;
			moveX();
			xMove = tempX;
		} else if (xMove < 0 && !checkEntityCollision(-1, 0)) {
			float tempX = xMove;
			xMove = -1;
			moveX();
			xMove = tempX;
		}
		if (!checkEntityCollision(0, yMove)) {
			moveY();
		} else if (yMove > 0 && !checkEntityCollision(0, 1)) {
			float tempY = yMove;
			yMove = 1;
			moveY();
			yMove = tempY;
		} else if (yMove < 0 && !checkEntityCollision(0, -1)) {
			float tempY = yMove;
			yMove = -1;
			moveY();
			yMove = tempY;
		}
		if (checkEntityCollision(0, 1)) {
			inAir = false;
		}
		testSpike();
	}

	public void moveX() {
		if (xMove > 0) {
			// Right

			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}

		else if (xMove < 0) {
			// Left

			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH - bounds.x + bounds.width + 1;
			}
		}
	}

	public void moveY() {
		if (yMove < 0) {
			// Up

			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		} else if (yMove > 0) {
			// Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				inAir = false;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		if (handler.getWorld().getTile(x, y).isSolid()) {
			return true;
		}
		return false;
	}

	public boolean inTile() {
		// upper left
		if (collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, (int) (y + bounds.y) / Tile.TILEHEIGHT)) {
			return true;
		}
		// upper right
		if (collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH,
				(int) (y + bounds.y) / Tile.TILEHEIGHT)) {
			return true;
		}
		// down right
		if (collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH,
				(int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
			return true;
		}
		// down left
		if (collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH,
				(int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
			return true;
		}
		return false;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public void die() {

	}

	public void testSpike() {
		int tileX = (int) ((x + bounds.x) / Tile.TILEWIDTH);
		int tileY = (int) ((y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);

		tileX = (int) ((x + bounds.x + bounds.width / 2) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);

		tileX = (int) ((x + bounds.x + bounds.width) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);
		
		tileX = (int) ((x + bounds.x) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.y) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);

		tileX = (int) ((x + bounds.x + bounds.width / 2) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.y) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);

		tileX = (int) ((x + bounds.x + bounds.width) / Tile.TILEWIDTH);
		tileY = (int) ((y + bounds.y) / Tile.TILEHEIGHT);
		handler.getWorld().getTile(tileX, tileY).whenIn(this, tileX * Tile.TILEWIDTH, tileY * Tile.TILEHEIGHT);
	}
}
