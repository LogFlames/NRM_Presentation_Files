package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock0100Tile extends Tile {

	public Rock0100Tile(int id) {
		super(Assets.rock0100, id);
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
