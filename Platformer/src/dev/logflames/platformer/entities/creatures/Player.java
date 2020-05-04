package dev.logflames.platformer.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.tiles.Tile;
import dev.logflames.platformer.utils.Timer;
import dev.logflames.platformer.utils.Utils;

public class Player extends Creature {

	private float respawnX, respawnY;

	private Timer particleTimer;

	private float accX, accY;
	private float velX, velY;

	private int timer;
	private boolean inGoal = false;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEAFAULT_CREATURE_WIDTH, Creature.DEAFAULT_CREATURE_HEIGHT);

		bounds.x = 0;
		bounds.y = 34;
		bounds.width = 63;
		bounds.height = 29;

		respawnX = x;
		respawnY = y;

		particleTimer = new Timer(50);
	}

	@Override
	public void tick() {
		if (timer <= 0) {
			handler.getGameCamera().centerOnEntity(this);

			getInput();
			updateForce();
			float saveX = x;
			move();
			if (x == saveX && !handler.getKeyManager().left && !handler.getKeyManager().right) {
				velX = 0;
				xMove = 0;
			}

			particleTimer.tick();

			if (!inAir) {
				if (xMove > 3 && particleTimer.done()) {
					handler.getWorld().getEntityManager().getParticleManager().addParticle(x + bounds.x - 4,
							getBottomY() - 2, 8, 4, new Color(51, 51, 51), Utils.randomValue(-2f, 2f), -2f, false);
				} else if (xMove < -3 && particleTimer.done()) {
					handler.getWorld().getEntityManager().getParticleManager().addParticle(
							x + bounds.x + bounds.width + 4, getBottomY(), 8, 4, new Color(51, 51, 51),
							Utils.randomValue(-1f, 1f), -2f, false);
				}
			}
		} else {
			health = Entity.DEAFAULT_HEALTH;
			unvulnerable = true;
			timer--;
			testRespawn();
		}
	}

	@Override
	public void render(Graphics g) {
		if (timer > 0) {
			return;
		}

		g.drawImage(Assets.currentPlayer, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), (int) (Creature.DEAFAULT_CREATURE_WIDTH),
				Creature.DEAFAULT_CREATURE_HEIGHT, null);
	}

	private void getInput() {
		xMove = 0.0f;
		yMove = 0.0f;

		if (handler.getKeyManager().left) {
			applyForce(-speed / 4, 0);
		}
		if (handler.getKeyManager().right) {
			applyForce(speed / 4, 0);
		}
		if (handler.getKeyManager().jump && !inAir) {
			applyForce(0, -Creature.DEAFAULT_JUMP_FORCE);
		}
		if (handler.getKeyManager().getKeys(KeyEvent.VK_M)) {
			applyForce(0, -4f);
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			die();
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_U)) {
			unvulnerable = !unvulnerable;
		}
		if (handler.getKeyManager().getKeys(KeyEvent.VK_G)) {
			velX *= 1.3;
			velY *= 1.3;
		}
	}

	@Override
	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			respawn();
			die();
		}
	}

	private void applyForce(float x, float y) {
		accX += x;
		accY += y;
	}

	private void updateForce() {
		applyForce(0, 0.9f);
		velX += accX;
		velY += accY;
		accX = 0;
		accY = 0;
		xMove += velX;
		yMove += velY;
		velX *= 0.86f;
		velY *= 0.86f;

		velY = Utils.Limit(velY, 40);
		velX = Utils.Limit(velX, 40);
	}

	public void respawn() {
		resetForce();
		timer = 120;

		for (PlayerClone e : handler.getWorld().getEntityManager().getClones()) {
			e.setActive(false);
		}
	}

	@Override
	public void die() {

		if (handler.Blod()) {
			for (int i = 0; i < 30; i++) {
				handler.getWorld().getEntityManager().getParticleManager().addParticle(x + bounds.x + bounds.width / 2,
						y + bounds.y + bounds.height / 2, 8, 121, Utils.randomColor(Assets.currentPlayer),
						Utils.randomValue(-7f, 7f), Utils.randomValue(-10f, 10f), true);
			}
		}
	}

	@Override
	public void setX(float x) {
		this.x = x;
		respawnX = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
		respawnY = y;
	}

	private void testRespawn() {
		if (timer <= 0) {
			x = respawnX;
			y = respawnY;
			unvulnerable = false;
		}
		if (timer == 58) {
			x = handler.getWorld().getWidth() * Tile.TILEWIDTH * 10;
			y = handler.getWorld().getHeight() * Tile.TILEHEIGHT * 10;
		}
	}

	public void resetForce() {
		velX = velY = accX = accY = 0;
		inGoal = false;
	}

	@Override
	public boolean isPlayer() {
		return true;
	}

	@Override
	public void setGoal(boolean goal) {
		this.inGoal = goal;
	}

	public boolean getInGoal() {
		return inGoal;
	}

	public void setSpawnPoint(float x, float y) {
		respawnX = x;
		respawnY = y;
	}

	public void kill() {
		hurt(100000);
	}
}
