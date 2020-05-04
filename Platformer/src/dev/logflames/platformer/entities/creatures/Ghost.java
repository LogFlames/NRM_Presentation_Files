package dev.logflames.platformer.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.tiles.Tile;

public class Ghost extends Creature {

	public Ghost(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEAFAULT_CREATURE_WIDTH, Creature.DEAFAULT_CREATURE_HEIGHT * 2);
	}
	
	@Override
	public void tick() {
		float addX = (x) - (handler.getWorld().getEntityManager().getPlayer().getX());
		float addY = (y) - (handler.getWorld().getEntityManager().getPlayer().getY());
		
		if (handler.getWorld().getEntityManager().getPlayer().getX() > handler.getWorld().getWidth() * Tile.TILEWIDTH || handler.getWorld().getEntityManager().getPlayer().getX() < 0) {
			addX = 0;
		}
		if (handler.getWorld().getEntityManager().getPlayer().getY() > handler.getWorld().getHeight() * Tile.TILEHEIGHT || handler.getWorld().getEntityManager().getPlayer().getY() < 0) {
			addY = 0;
		}
		
		x += -addX / 1000;
		y += -addY / 1000;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}
}
