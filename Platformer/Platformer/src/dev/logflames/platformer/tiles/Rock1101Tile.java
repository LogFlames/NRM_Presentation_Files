package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock1101Tile extends Tile {

	public Rock1101Tile(int id) {
		super(Assets.rock1101, id);
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
