package dev.logflames.platformer.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.creatures.PlayerClone;
import dev.logflames.platformer.worlds.World;

public class GameState extends State {
	
	private World world;
	
	public static int currentLevel = 0;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "/world/" + handler.getLevels()[currentLevel]);
		handler.setWorld(world);
	}

	@Override
	public void tick() {
		world.tick();
		
		if (handler.getWorld().getEntityManager().getPlayer().getInGoal() || handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
			currentLevel++;
			if (currentLevel >= handler.getLevels().length) {
				// End screen
				
				currentLevel = 0;
			}
			handler.getWorld().changeWorld("/world/" + handler.getLevels()[currentLevel]);
			for (PlayerClone e : handler.getWorld().getEntityManager().getClones()) {
				e.setActive(false);
			}
			world.getEntityManager().getParticleManager().clearPlarticles();
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
			handler.getWorld().changeWorld("./world.txt");
			for (PlayerClone e : handler.getWorld().getEntityManager().getClones()) {
				e.setActive(false);
			}
			world.getEntityManager().getParticleManager().clearPlarticles();
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(handler.getGame().pauseState);
		}
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	@Override
	public void start() {
		handler.getMouseManager().setUIManager(world.getUIManager());
	}
}
