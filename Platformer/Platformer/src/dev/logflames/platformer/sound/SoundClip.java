package dev.logflames.platformer.sound;

import java.applet.AudioClip;

public class SoundClip {

	private String name;
	private AudioClip clip;

	public SoundClip(String name, AudioClip clip) {
		this.name = name;
		this.clip = clip;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setClip(AudioClip clip) {
		this.clip = clip;
	}

	public AudioClip getClip() {
		return clip;
	}
}
