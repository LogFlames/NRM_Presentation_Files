package dev.logflames.platformer.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.logflames.platformer.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean cantPressLeft, cantPressRight;
	private boolean justPressedLeft, justPressedRight;

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;

	public MouseManager() {

	}
	
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public void tick() {
		justPressedLeft = false;
		justPressedRight = false;
		if (leftPressed && !cantPressLeft) {
			justPressedLeft = true;
		}
		if (rightPressed && !cantPressRight) {
			justPressedRight = true;
		}

		if (leftPressed) {
			cantPressLeft = true;
		}
		if (rightPressed) {
			cantPressRight = true;
		}
		if (!leftPressed) {
			cantPressLeft = false;
		}
		if (!rightPressed) {
			cantPressRight = false;
		}
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}
		
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public boolean isJustPressedLeft() {
		return justPressedLeft;
	}

	public boolean isJustPressedRight() {
		return justPressedRight;
	}
}
