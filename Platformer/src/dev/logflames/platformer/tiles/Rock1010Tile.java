package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock1010Tile extends Tile {

	public Rock1010Tile(int id) {
		super(Assets.rock1010, id);
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
