package dev.logflames.platformer.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.bullets.BulletManager;
import dev.logflames.platformer.entities.creatures.Player;
import dev.logflames.platformer.entities.creatures.PlayerClone;
import dev.logflames.platformer.entities.statics.StaticEntity;
import dev.logflames.platformer.particles.ParticleManager;
import dev.logflames.platformer.tiles.Tile;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<PlayerClone> clones;
	private ArrayList<StaticEntity> statics;
	
	private int staticTimer;

	private ParticleManager particleManager;
	private BulletManager bulletManager;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		clones = new ArrayList<PlayerClone>();
		statics = new ArrayList<StaticEntity>();

		entities.add(player);
		particleManager = new ParticleManager(handler);
		bulletManager = new BulletManager();
	}

	public void tick() {
		staticTimer++;
		if (staticTimer >= 60) {
			updateStaticEntities();
			staticTimer = 0;
		}
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();

			e.tick();
			if (!e.isActive()) {
				it.remove();
			}
		}
		particleManager.tick();
		bulletManager.tick();
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
		particleManager.render(g);
		bulletManager.render(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
		if (e.isClone()) {
			clones.add((PlayerClone) e);
		}
		if (e.isStatic()) {
			statics.add((StaticEntity) e);
		}
	}

	public ArrayList<PlayerClone> getClones() {
		return clones;
	}

	// Getters and Setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ParticleManager getParticleManager() {
		return particleManager;
	}

	public void clearEntities() {
		entities = new ArrayList<Entity>();
		entities.add(player);
	}

	public ArrayList<StaticEntity> getStatics() {
		return statics;
	}
	
	public void updateStaticEntities() {
		for (StaticEntity e : statics) {
			float tileX = e.getX() + e.getCollisionBounds(0, 0).x;
			float tileY = e.getY() + e.getCollisionBounds(0, 0).y;

			handler.getWorld().getTile((int) (tileX / Tile.TILEWIDTH), (int) (tileY / Tile.TILEHEIGHT))
					.staticEntityIn(e);

			tileX = e.getX() + e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width;
			tileY = e.getY() + e.getCollisionBounds(0, 0).y;

			handler.getWorld().getTile((int) (tileX / Tile.TILEWIDTH), (int) (tileY / Tile.TILEHEIGHT))
					.staticEntityIn(e);

			tileX = e.getX() + e.getCollisionBounds(0, 0).x;
			tileY = e.getY() + e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height;

			handler.getWorld().getTile((int) (tileX / Tile.TILEWIDTH), (int) (tileY / Tile.TILEHEIGHT))
					.staticEntityIn(e);

			tileX = e.getX() + e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width;
			tileY = e.getY() + e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height;

			handler.getWorld().getTile((int) (tileX / Tile.TILEWIDTH), (int) (tileY / Tile.TILEHEIGHT))
					.staticEntityIn(e);
		}
	}
	
	public void setBulletManager(BulletManager bm) {
		this.bulletManager = bm;
	}
	
	public BulletManager getBulletManager() {
		return bulletManager;
	}
}
