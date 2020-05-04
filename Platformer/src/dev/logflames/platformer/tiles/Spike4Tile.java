package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class Spike4Tile extends Tile {

	public Spike4Tile(int id) {
		super(Assets.spikes4, id);
	}
	
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.getLeftX() < x + 22) {
				e.hurt(1);
			}
		}
	}

}
