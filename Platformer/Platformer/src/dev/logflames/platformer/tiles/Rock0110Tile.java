package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock0110Tile extends Tile {

	public Rock0110Tile(int id) {
		super(Assets.rock0110, id);
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
