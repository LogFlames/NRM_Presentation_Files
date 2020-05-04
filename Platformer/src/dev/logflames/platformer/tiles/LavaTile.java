package dev.logflames.platformer.tiles;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Animation;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.particles.Particles;

public class LavaTile extends Tile {

	public LavaTile(int id) {
		super(Assets.lava, id);
		anim = new Animation(lavaAnimTimer, Assets.lava);
	}

	@Override
	public void tick(int x, int y, Handler handler) {
		anim.tick();
	}

	@Override
	public boolean hasAnimation() {
		return true;
	}

	@Override
	public void whenIn(Entity e, int x, int y) {
		if (e.getBottomY() > y + 44) {
			if (!e.getUnvulnerable()) {
				if (e.isPlayer()) {
					e.lavaDeath();
					e.respawn();
				} else {
					e.lavaDeath();
					e.setActive(false);
				}
			}
		}
	}

	@Override
	public void whenParticleIn(Particles p, int x, int y) {

		if (p.getY() > y + 38) {
			p.delete();
		}
	}

	@Override
	public String getType() {
		return "lava";
	}
}
