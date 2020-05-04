package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class BigSpike2Tile extends Tile {

	public BigSpike2Tile(int id) {
		super(Assets.big_spikes2, id);
	}
	
	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getTopY() < y + 42) {
				e.hurt(1);
			}
		}
	}

}
