package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;

public class CheckpointTile extends Tile {

	public CheckpointTile(int id) {
		super(Assets.spikes, id);
	}
	
	@Override
	public void whenIn(Entity e, int x, int y) {
		if (e.isPlayer()) {
			e.setSpawnPoint(e.getX(), e.getY());
		}
	}
}
