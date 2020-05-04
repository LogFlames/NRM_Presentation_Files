package dev.logflames.platformer.entities.statics;

import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.tiles.Tile;

public class HalfRockTile extends StaticEntity {

	public HalfRockTile(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = 20;
	}

	@Override
	public void tick() {
		health = 3;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.halfRock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
	}

	@Override
	public void die() {
		
	}

}
