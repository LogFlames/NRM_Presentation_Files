package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock1000Tile extends Tile {

	public Rock1000Tile(int id) {
		super(Assets.rock1000, id);
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
