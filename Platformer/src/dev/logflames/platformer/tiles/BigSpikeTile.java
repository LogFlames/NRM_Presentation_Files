package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class BigSpikeTile extends Tile {

	public BigSpikeTile(int id) {
		super(Assets.big_spikes, id);
	}
	
	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getBottomY() > y + 22) {
				e.hurt(1);
			}
		}
	}
}
