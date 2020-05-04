package dev.logflames.platformer.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.playerCusomization.ChoosePlayer;
import dev.logflames.platformer.ui.ClickListener;
import dev.logflames.platformer.ui.UIImageButton;
import dev.logflames.platformer.ui.UIManager;

public class PauseState extends State {
	
	private UIManager uiManager;
	private ChoosePlayer choosePlayer;

	public PauseState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		choosePlayer = new ChoosePlayer(handler);
		
		uiManager.addObject(new UIImageButton(10, 10, 64, 64, Assets.pause_btn, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				choosePlayer.setActive(false);
				State.setState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(100, 100, 128, 128, Assets.character_btn, new ClickListener() {

			@Override
			public void onClick() {
				choosePlayer.setActive(true);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		choosePlayer.tick();
		
		if (choosePlayer.getActive() == false) {
			handler.getMouseManager().setUIManager(uiManager);
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			choosePlayer.setActive(false);
			State.setState(handler.getGame().gameState);
		}
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		choosePlayer.render(g);
	}

	@Override
	public void start() {
		handler.getMouseManager().setUIManager(uiManager);
	}
}
