package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class Spike3Tile extends Tile {

	public Spike3Tile(int id) {
		super(Assets.spikes3, id);
	}
	
	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getRightX() > x + 42) {
				e.hurt(1);
			}
		}
	}
}
