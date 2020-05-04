package dev.logflames.platformer.gfx;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;
	private int followSpeed;

	public GameCamera(Handler handler, float xOffset, float yOffset, int followSpeed) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.followSpeed = followSpeed;
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;

		checkBlankSpace();
	}

	public void centerOnEntity(Entity e) {
		followEntity(e);

		// xOffset = e.getX() - game.getWidth() / 2 + e.getWidth() / 2;
		// yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;

		checkBlankSpace();
	}

	public void followEntity(Entity e) {
		float xOff = (e.getX() - handler.getWidth() / 2 + e.getWidth() / 2) - xOffset;
		float yOff = (e.getY() - handler.getHeight() / 2 + e.getHeight() / 2) - yOffset;

		xOffset += xOff / followSpeed;
		yOffset += yOff / followSpeed;
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setxOffset(float newX) {
		xOffset = newX;
	}

	public void setyOffset(float newY) {
		yOffset = newY;
	}
}
