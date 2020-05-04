package dev.logflames.platformer.states;

import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void start() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
