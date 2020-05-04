package dev.logflames.platformer;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.logflames.platformer.display.Display;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.gfx.GameCamera;
import dev.logflames.platformer.input.KeyManager;
import dev.logflames.platformer.input.MouseManager;
import dev.logflames.platformer.sound.Sound;
import dev.logflames.platformer.states.GameState;
import dev.logflames.platformer.states.MenuState;
import dev.logflames.platformer.states.PauseState;
import dev.logflames.platformer.states.State;

public class Game implements Runnable {

	private Display display;
	public String title;
	private int width, height;

	private boolean running;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	public State gameState;
	public State menuState;
	public State pauseState;

	private Handler handler;

	private GameCamera gameCamera;

	private KeyManager keyManager;
	private MouseManager mouseManager;

	public long timer;
	private double deltaTime;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		Sound.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0, 2);

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		pauseState = new PauseState(handler);
		State.setState(gameState);
	}

	private void tick() {
		if (State.getState() != null) {
			State.getState().tick();
		}
		
		keyManager.tick();
		mouseManager.tick();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear screen
		g.clearRect(0, 0, width, height);

		if (State.getState() != null) {
			State.getState().render(g);
		}

		// End of drawing
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		deltaTime = 0;

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long lastTimeFrame = lastTime;
		timer = 0;
		int ticks = 0;

		while (running) {

			now = System.nanoTime();

			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {

				if (ticks <= fps) {
					tick();
					render();
					ticks++;
				}

				delta--;

				deltaTime = (now - lastTimeFrame) / timePerTick;
				lastTimeFrame = now;
			}

			if (timer >= 1000000000) {
				System.out.println("Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}

		}

		stop();

	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public double getDelta() {
		return deltaTime;
	}
}
