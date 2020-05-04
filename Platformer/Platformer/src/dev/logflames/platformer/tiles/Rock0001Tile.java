package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock0001Tile extends Tile {

	public Rock0001Tile(int id) {
		super(Assets.rock0001, id);
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
