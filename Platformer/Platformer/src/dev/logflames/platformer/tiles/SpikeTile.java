package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class SpikeTile extends Tile {

	public SpikeTile(int id) {
		super(Assets.spikes, id);
	}

	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getBottomY() > y + 38) {
				e.hurt(1);
			}
		}
	}
}
