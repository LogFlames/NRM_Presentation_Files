package dev.logflames.platformer.states;

import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.ui.UIManager;

public class SettingsState extends State {

	private UIManager uiManager;

	public SettingsState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

	@Override
	public void start() {
		handler.getMouseManager().setUIManager(uiManager);
	}
}
