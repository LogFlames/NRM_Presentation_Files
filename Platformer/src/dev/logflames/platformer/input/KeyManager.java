package dev.logflames.platformer.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {
	
	private boolean[] keys, justPressed, cantPress;
	public boolean left, right, jump;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			} else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		jump = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
	}
	
	public boolean keyJustPressed(int e) {
		if (e < 0 || e > keys.length) {
			return false;
		}
		return justPressed[e];
	}
	
	public boolean getKeys(int e) {
		return keys[e];
	}
}
