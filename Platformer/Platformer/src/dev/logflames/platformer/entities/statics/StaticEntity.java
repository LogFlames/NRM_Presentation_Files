package dev.logflames.platformer.entities.statics;

import dev.logflames.platformer.Handler;
import dev.logflames.platformer.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
}
