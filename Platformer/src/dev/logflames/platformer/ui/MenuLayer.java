package dev.logflames.platformer.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.logflames.platformer.Handler;

public class MenuLayer {
	
	private int prio;
	
	private BufferedImage img;
	
	private int x, y;
	private int width, height;
	
	public MenuLayer(Handler handler, BufferedImage img, int prio) {
		this.img = img;
		x = 0;
		y = 0;
		width = handler.getWidth();
		height = handler.getHeight();
	}
	
	public MenuLayer(BufferedImage img, int x, int y, int width, int height, int prio) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public int getPrio() {
		return prio;
	}
	
	public void setPrio(int prio) {
		this.prio = prio;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
