package dev.logflames.platformer.worlds;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.EntityManager;
import dev.logflames.platformer.entities.creatures.Creature;
import dev.logflames.platformer.entities.creatures.Player;
import dev.logflames.platformer.entities.creatures.PlayerClone;
import dev.logflames.platformer.entities.statics.HalfRockTile;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.states.State;
import dev.logflames.platformer.tiles.Tile;
import dev.logflames.platformer.ui.ClickListener;
import dev.logflames.platformer.ui.UIImageButton;
import dev.logflames.platformer.ui.UIManager;
import dev.logflames.platformer.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int tiles[][];

	private UIManager uiManager;

	private EntityManager entityManager;

	private long timer;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(15, 15, 64, 64, Assets.pause_btn2, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().pauseState);
			}
		}));

		loadWorld(path);
	}

	public void tick() {
		entityManager.tick();
		uiManager.tick();

		timer += handler.getSeconds();

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && timer >= 1000000000) {
			Creature pc = new PlayerClone(handler, entityManager.getPlayer().getX(),
					entityManager.getPlayer().getY() + 32, 1);

			if (!pc.checkEntityCollision(0, 0)) {
				if (!pc.inTile()) {
					entityManager.addEntity(pc);
					timer = 0;
					for (PlayerClone e : entityManager.getClones()) {
						e.changeId();
					}
				}
			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
			for (PlayerClone e : entityManager.getClones()) {
				e.changeId();
			}
		}
	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, (int) (handler.getGameCamera().getxOffset() / Tile.TILEWIDTH));
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, (int) (handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT));
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				if (getTile(x, y).hasAnimation()) {
					getTile(x, y).tick(x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, handler);
				}
			}
		}

		entityManager.render(g);
		uiManager.render(g);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.backgroundTile;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.backgroundTile;
		}
		return t;
	}

	private void loadWorld(String path) {
		entityManager.clearEntities();

		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]) * 64;
		spawnY = Utils.parseInt(tokens[3]) * 64;

		tiles = new int[width][height];

		//  Rock fix and Floating Rocks

		boolean right, up, left, down;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				// Floating rocks

				if (Utils.parseInt(tokens[(x + y * width) + 4]) == 30) {
					tiles[x][y] = 0;
					entityManager.addEntity(new HalfRockTile(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT,
							Tile.TILEWIDTH, Tile.TILEHEIGHT));
					// Lava
				} else if (Utils.parseInt(tokens[(x + y * width) + 4]) == 40) {
					tiles[x][y] = 40;
				} else {
					tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				}
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// Rock

				if (tiles[x][y] == 10) {
					right = true;
					up = true;
					left = true;
					down = true;
					if (getTile(x - 1, y).getType() == "rock") {
						right = false;
					}
					if (getTile(x, y - 1).getType() == "rock") {
						up = false;
					}
					if (getTile(x + 1, y).getType() == "rock") {
						left = false;
					}
					if (getTile(x, y + 1).getType() == "rock") {
						down = false;
					}
					tiles[x][y] = Utils.getRockID(right, up, left, down);
				}
			}
		}

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);

		// entityManager.addEntity(new Ghost(handler, spawnX, spawnY - 300));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void changeWorld(String path) {
		entityManager.getPlayer().resetForce();
		loadWorld(path);
	}

	public UIManager getUIManager() {
		return uiManager;
	}
}
