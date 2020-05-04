package dev.logflames.platformer.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;
import dev.logflames.platformer.entities.statics.StaticEntity;
import dev.logflames.platformer.gfx.Animation;
import dev.logflames.platformer.particles.Particles;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	public static Tile backgroundTile = new BackgroundTile(0);
	public static Tile goalTile = new GoalTile(1);
	public static Tile bigSpikeTile = new BigSpikeTile(7);
	public static Tile lavaFullTile = new LavaFullTile(8);
	public static Tile bigSpike2Tile = new BigSpike2Tile(9);
	
	public static Tile spikeTile = new SpikeTile(80);
	public static Tile spike2Tile = new Spike2Tile(81);
	public static Tile spike3Tile = new Spike3Tile(82);
	public static Tile spike4Tile = new Spike4Tile(83);
	public static Tile sawbladeTile = new SawBladeTile(90);

	public static Tile rock0000 = new Rock0000Tile(10);
	public static Tile rock0001 = new Rock0001Tile(101);
	public static Tile rock0010 = new Rock0010Tile(102);
	public static Tile rock0011 = new Rock0011Tile(103);
	public static Tile rock0100 = new Rock0100Tile(104);
	public static Tile rock0101 = new Rock0101Tile(105);
	public static Tile rock0110 = new Rock0110Tile(106);
	public static Tile rock0111 = new Rock0111Tile(107);
	public static Tile rock1000 = new Rock1000Tile(108);
	public static Tile rock1001 = new Rock1001Tile(109);
	public static Tile rock1010 = new Rock1010Tile(200);
	public static Tile rock1011 = new Rock1011Tile(201);
	public static Tile rock1100 = new Rock1100Tile(202);
	public static Tile rock1101 = new Rock1101Tile(203);
	public static Tile rock1110 = new Rock1110Tile(204);
	public static Tile rock1111 = new Rock1111Tile(205);
	
	public static Tile lavaTile = new LavaTile(40);
	
	public static Tile checkpointTile = new CheckpointTile(99);

	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected ArrayList<StaticEntity> staticEntitiesIn;

	protected BufferedImage texture;
	protected BufferedImage[] texture2;

	protected Animation anim;
	protected final int id;
	
	
	// All lava anim timers
	protected int lavaAnimTimer = 210;

	public Tile(BufferedImage texture, int id) {
		staticEntitiesIn = new ArrayList<StaticEntity>();
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public Tile(BufferedImage[] texture2, int id) {
		staticEntitiesIn = new ArrayList<StaticEntity>();
		this.texture2 = texture2;
		this.id = id;
		
		tiles[id] = this;
	}

	public void tick(int x, int y, Handler handler) {

	}

	public void render(Graphics g, int x, int y) {
		if (hasAnimation()) {
			g.drawImage(anim.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
		} else {
			g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		}
	}

	public boolean isSolid() {
		return false;
	}

	public int getID() {
		return id;
	}

	public void whenIn(Entity e, int x, int y) {

	}

	public boolean hasAnimation() {
		return false;
	}
	
	public void whenParticleIn(Particles p, int x, int y) {
		
	}
	
	public void staticEntityIn(StaticEntity e) {
		if (staticEntitiesIn.contains(e)) {
			return;
		}
		staticEntitiesIn.add(e);
	}
	
	public ArrayList<StaticEntity> getStatics() {
		return staticEntitiesIn;
	}
	
	public String getType() {
		return "";
	}
}
