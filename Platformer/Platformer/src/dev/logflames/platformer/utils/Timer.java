package dev.logflames.platformer.utils;

public class Timer {
	
	private int speed;
	
	private long timer, lastTime, now;
	
	private boolean done;
	
	public Timer(int speed_in_miliseconds) {
		speed = speed_in_miliseconds;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}
	
	public void tick() {
		now = System.currentTimeMillis();
		timer += now - lastTime;
		lastTime = now;
		
		if (timer > speed) {
			done = true;
		}
	}
	
	public boolean done() {
		if (done) {
			timer = 0;
			done = false;
			return true;
		}
		return false;
	}
}
