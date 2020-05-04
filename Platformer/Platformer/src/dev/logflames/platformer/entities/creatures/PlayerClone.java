package dev.logflames.platformer.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.utils.Utils;

public class PlayerClone extends Creature {
	
	private float accX, accY;
	private float velX, velY;
	
	private int id;

	public PlayerClone(Handler handler, float x, float y, int id) {
		super(handler, x, y, Creature.DEAFAULT_CREATURE_WIDTH, Creature.DEAFAULT_CREATURE_HEIGHT);
		this.id = id;

		bounds.x = 0;
		bounds.y = 33;
		bounds.width = 63;
		bounds.height = 30;
	}

	@Override
	public void tick() {
		xMove = 0;
		yMove = 0;
		
		if (id > handler.getClonesAmount()) {
			active = false;
		}
		
		updateForce();
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.playerClone, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), Creature.DEAFAULT_CREATURE_WIDTH,
				Creature.DEAFAULT_CREATURE_HEIGHT, null);
	}
	
	private void applyForce(float x, float y) {
		accX += x;
		accY += y;
	}
	
	private void updateForce() {
		applyForce(0, 1.6f);
		velX += accX;
		velY += accY;
		accX = 0;
		accY = 0;
		xMove += velX;
		yMove += velY;
		velX *= 0.86f;
		velY *= 0.86f;

		velY = Utils.Limit(velY, 60);
		velX = Utils.Limit(velX, 60);
	}
	
	public void changeId() {
		id++;
	}
	
	@Override
	public boolean isClone() {
		return true;
	}
	
	@Override
	public void die() {
		if (!handler.Blod()) {
			return;
		}
		
		for (int i = 0; i < 40; i++) {
			handler.getWorld().getEntityManager().getParticleManager().addParticle(x + bounds.x + bounds.width / 2, y + bounds.y + bounds.height / 2, 4, 120, Color.red, Utils.randomValue(-10f, 10f), Utils.randomValue(-10f, 10f), false);
		}
	}
}
