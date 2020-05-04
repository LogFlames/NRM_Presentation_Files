package dev.logflames.platformer.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.utils.Utils;

public abstract class Entity {

	public static final int DEAFAULT_HEALTH = 3;

	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active = true;
	protected int health;

	protected boolean unvulnerable = false;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEAFAULT_HEALTH;

		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void die();

	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isClone() {
		return false;
	}

	public boolean isPlayer() {
		return false;
	}

	public void setGoal(boolean a) {

	}

	public int getBottomY() {
		return (int) (y + bounds.y + bounds.height);
	}

	public int getTopY() {
		return (int) (y + bounds.y);
	}

	public int getLeftX() {
		return (int) (x + bounds.x);
	}

	public int getRightX() {
		return (int) (x + bounds.x + bounds.width);
	}

	public void respawn() {

	}

	public void lavaDeath() {
		float lavaPosX = x + bounds.x + bounds.width / 2;
		float lavaPosY = y + bounds.y + bounds.height - 20;

		for (int i = 0; i < 20; i++) {
			handler.getWorld().getEntityManager().getParticleManager().addParticle(lavaPosX, lavaPosY,
					Utils.randomValue(4, 8), Utils.randomValue(60, 120), Utils.randomLavaColor(),
					Utils.randomValue(-4f, 4f), Utils.randomValue(-6f, -4f), false);
		}
	}

	public void setSpawnPoint(float x, float y) {

	}

	public boolean getUnvulnerable() {
		return unvulnerable;
	}

	public boolean isStatic() {
		return false;
	}
}
