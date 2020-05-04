package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock1110Tile extends Tile {

	public Rock1110Tile(int id) {
		super(Assets.rock1110, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public String getType() {
		return "rock";
	}
}
