package dev.logflames.platformer.tiles;

import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.particles.Particles;

public class LavaFullTile extends Tile {

	public LavaFullTile(int id) {
		super(Assets.lavaFull, id);
	}
	
	@Override
	public void whenIn(Entity e, int x, int y) {
		if (!e.getUnvulnerable()) {
			if (e.isPlayer()) {
				e.lavaDeath();
				e.respawn();
			} else {
				e.setActive(false);
			}
		}
	}
	
	@Override
	public void whenParticleIn(Particles p, int x, int y) {
		p.delete();
	}
}
