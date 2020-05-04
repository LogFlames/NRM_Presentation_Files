package dev.logflames.platformer.playerCusomization;

import java.awt.Graphics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.gfx.Assets;
import dev.logflames.platformer.ui.ClickListener;
import dev.logflames.platformer.ui.MenuLayerManager;
import dev.logflames.platformer.ui.UIImageButton;
import dev.logflames.platformer.ui.UIManager;

public class ChoosePlayer {

	private int currentPlayer;
	private int spacing = 140;
	private int playerSize = 128;
	
	private MenuLayerManager menuLayerManager;

	private int offset;

	private boolean active = false;

	private Handler handler;

	private UIManager uiManager;

	public ChoosePlayer(Handler handler) {
		this.handler = handler;
		uiManager = new UIManager(handler);
		uiManager.setPrio(3);
		menuLayerManager = new MenuLayerManager(uiManager);

		uiManager.addObject(new UIImageButton(100 - 64, 300, 128, 128, Assets.arrow_btn, new ClickListener() {

			@Override
			public void onClick() {
				currentPlayer--;
				if (currentPlayer >= 0 && currentPlayer < Assets.player.length) {
					offset -= spacing;
				}
			}
		}));

		uiManager.addObject(new UIImageButton(700 - 64, 300, 128, 128, Assets.arrow_btn2, new ClickListener() {

			@Override
			public void onClick() {
				currentPlayer++;
				if (currentPlayer >= 0 && currentPlayer < Assets.player.length) {
					offset += spacing;
				}
			}
		}));

		uiManager.addObject(new UIImageButton(0, 10, 128, 128, Assets.arrow_btn, new ClickListener() {

			@Override
			public void onClick() {
				active = false;
				offset = 0;
			}
		}));
	}

	public void tick() {
		if (!active) {
			return;
		} else {
			handler.getMouseManager().setUIManager(uiManager);
		}

		if (currentPlayer < 0) {
			currentPlayer = 0;
		}
		if (currentPlayer >= Assets.player.length) {
			currentPlayer = Assets.player.length - 1;
		}

		Assets.setCurrentPlayer(Assets.player[currentPlayer]);

		uiManager.tick();

		if (offset != 0) {
			offset *= 0.9f;
		}
		if (offset < 0.1f && offset > -0.1f) {
			offset = 0;
		}

	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		
		menuLayerManager.render(g, 0);

		int len = Assets.player.length;

		for (int i = -2; i < 3; i++) {
			if (currentPlayer + i < 0 || currentPlayer + i >= len) {
				continue;
			}
			if (i == 0) {
				g.drawImage(Assets.player[currentPlayer + i],
						(handler.getWidth() / 2 - playerSize / 2) + i * spacing + offset,
						300 - playerSize / 2 + playerSize / 4, playerSize, playerSize, null);
			} else {
				g.drawImage(Assets.player[currentPlayer + i],
						(handler.getWidth() / 2 - playerSize / 2) + i * spacing + offset,
						300 - playerSize / 2 + playerSize / 4, playerSize, playerSize, null);
			}
		}
		
		menuLayerManager.render(g, 1);
		menuLayerManager.render(g, 2);
		menuLayerManager.render(g, 3);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return active;
	}

}
