package dev.logflames.platformer.tiles;

import java.awt.Rectangle;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Animation;
import dev.logflames.platformer.gfx.Assets;

public class SawBladeTile extends Tile {

	public SawBladeTile(int id) {
		super(Assets.sawblade, id);
		anim = new Animation(100, Assets.sawblade);
	}

	@Override
	public void tick(int x, int y, Handler handler) {
		anim.tick();
	}

	@Override
	public void whenIn(Entity e, int x, int y) {
		Rectangle bounds = new Rectangle(x + 6, y + 6, 52, 52);
		if (!e.getUnvulnerable()) {
			if (bounds.intersects(e.getCollisionBounds(0, 0))) {
				e.hurt(1);
			}
		}
	}

	@Override
	public boolean hasAnimation() {
		return true;
	}

}
