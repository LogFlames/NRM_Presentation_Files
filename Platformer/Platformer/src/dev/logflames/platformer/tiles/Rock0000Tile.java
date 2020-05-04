package dev.logflames.platformer.tiles;

import dev.logflames.platformer.gfx.Assets;

public class Rock0000Tile extends Tile {

	public Rock0000Tile(int id) {
		super(Assets.rock0000, id);
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
