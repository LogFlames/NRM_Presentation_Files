package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class Spike2Tile extends Tile {

	public Spike2Tile(int id) {
		super(Assets.spikes2, id);
	}

	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getTopY() < y + 26) {
				e.hurt(1);
			}
		}
	}
}
