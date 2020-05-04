package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock0101Tile extends Tile {

	public Rock0101Tile(int id) {
		super(Assets.rock0101, id);
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
