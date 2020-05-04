package dev.logflames.platformer.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

import dev.logflames.platformer.Launcher;

public class Sound {
	
	private static ArrayList<SoundClip> clips;
	
	public static void playSound(String name) {
		for (SoundClip sc : clips) {
			if (sc.getName() == name) {
				sc.getClip().play();
				return;
			}
		}
		System.out.println("Sound not found: " + name + ", please create the sound first.");
	}
	
	public static void loopSound(String name) {
		for (SoundClip sc : clips) {
			if (sc.getName() == name) {
				sc.getClip().loop();
				return;
			}
		}
		System.out.println("Sound not found: " + name + ", please create the sound first.");
	}
	
	public static void stopAll() {
		for (SoundClip sc : clips) {
			sc.getClip().stop();
		}
	}
	
	public static void init() {
		
	}
	
	public static void addSound(String name, String soundPath) {
		URL url = Launcher.class.getResource(soundPath);
		AudioClip clip = Applet.newAudioClip(url);
		clips.add(new SoundClip(name, clip));
	}
}
