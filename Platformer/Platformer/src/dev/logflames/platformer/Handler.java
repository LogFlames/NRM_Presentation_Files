package dev.logflames.platformer;

import dev.logflames.platformer.gfx.GameCamera;
import dev.logflames.platformer.input.KeyManager;
import dev.logflames.platformer.input.MouseManager;
import dev.logflames.platformer.states.GameState;
import dev.logflames.platformer.utils.Utils;
import dev.logflames.platformer.worlds.World;

public class Handler {
	
	private String[] tokens;

	private Game game;
	private World world;
	public static World staticWorld;

	public Handler(Game game) {
		this.game = game;
		
		String file = Utils.loadFileAsString("/settings/Settings.txt");
		String[] tokens_temp = file.split("\\s+");
		tokens = new String[tokens_temp.length];
		for (int i = 0; i < tokens_temp.length; i++) {
			tokens[i] = tokens_temp[i];
		}
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
		staticWorld = world;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getClonesAmount() {
		return 4;
	}
	
	public long getSeconds() {
		return game.timer;
	}
	
	public String[] getLevels() {
		String[] str = new String[5];
		str[0] = "testLevel.txt";
		str[1] = "TutorialLevel.txt";
		str[2] = "Level1.txt";
		str[3] = "Level2.txt";
		str[4] = "Level3.txt";
		return str;
	}
	
	public String getCurrentLevel() {
		return getLevels()[GameState.currentLevel];
	}
	
	public boolean Blod() {
		if (Utils.parseInt(tokens[0]) == 0) {
			return false;
		}
		return true;
	}
	
	public double deltaTime() {
		return game.getDelta();
	}
}
