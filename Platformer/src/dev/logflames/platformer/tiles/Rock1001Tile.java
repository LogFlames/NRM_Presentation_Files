package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock1001Tile extends Tile {

	public Rock1001Tile(int id) {
		super(Assets.rock1001, id);
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
