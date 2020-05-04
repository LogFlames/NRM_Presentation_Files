package dev.logflames.platformer.tiles;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Animation;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.particles.Particles;
import dev.logflames.platformer.utils.Utils;

public class GoalTile extends Tile {

	public GoalTile(int id) {
		super(Assets.goal, id);
		anim = new Animation(150, Assets.goal);
	}

	@Override
	public void tick(int x, int y, Handler handler) {
		anim.tick();

		for (int i = 0; i < 1; i++) {
			Particles p = new Particles(handler, Utils.randomValue(x - 8, x + Tile.TILEWIDTH + 8),
					Utils.randomValue(y, y + Tile.TILEHEIGHT), 6, 240, Utils.randomPortalColor(),
					Utils.randomValue(-4f, 4f), Utils.randomValue(4f, -4f), true, 0f);
			p.setShowDelay(60);
			handler.getWorld().getEntityManager().getParticleManager().addParticle(p);
		}
	}

	@Override
	public void whenIn(Entity e, int x, int y) {
		if (e.isPlayer()) {
			e.setGoal(true);
		} else {
			e.hurt(1);
		}
	}

	@Override
	public boolean hasAnimation() {
		return true;
	}
}
