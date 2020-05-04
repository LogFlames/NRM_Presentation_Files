package dev.logflames.platformer.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dev.logflames.platformer.Launcher;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br;

			try {
				br = new BufferedReader(new FileReader(path));
			} catch (IOException e) {
				InputStream in = Launcher.class.getResourceAsStream(path);
				br = new BufferedReader(new InputStreamReader(in));
			}
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static float Limit(float a, float b) {
		if (a > b) {
			return b;
		} else if (a < -b) {
			return -b;
		}
		return a;
	}

	public static float Limit(float a, String c, float b) {
		if (c == ">") {
			if (a > b) {
				return b;
			}
		} else if (c == "<") {
			if (a < b) {
				return b;
			}
		}
		return a;
	}

	public static int randomValue(int a, int b) {
		int[] randomList = new int[Math.abs(b - a)];
		int listAdd = 0;
		for (int i = a; i < b; i++) {
			randomList[listAdd] = i;
			listAdd++;
		}
		int index = (int) (Math.random() * randomList.length);
		return randomList[index];
	}

	public static float randomValue(float min, float max) {
		float range = (max - min) + 1;
		return (float) (Math.random() * range) + min;
	}

	public static Color randomColor(BufferedImage image) {
		Color[] pixels = new Color[image.getWidth() * image.getHeight()];

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int color = image.getRGB(x, y);

				int red = (color & 0x00ff0000) >> 16;
				int green = (color & 0x0000ff00) >> 8;
				int blue = color & 0x000000ff;
				
				pixels[x + y * image.getWidth()] = new Color(red, green, blue);
			}
		}

		int index = randomValue(0, pixels.length);
		Color returnColor = pixels[index];
		while (returnColor.getRed() == 0 && returnColor.getGreen() == 0 && returnColor.getBlue() == 0) {
			index = randomValue(0, pixels.length);
			returnColor = pixels[index];
		}
		return returnColor;
	}

	public static Color randomLavaColor() {
		Color[] colors = new Color[3];
		colors[0] = new Color(233, 55, 3);
		colors[1] = new Color(231, 79, 5);
		colors[2] = new Color(247, 132, 17);

		int index = randomValue(0, colors.length);
		return colors[index];
	}

	public static Color randomPortalColor() {
		Color[] colors = new Color[4];
		colors[0] = new Color(60, 170, 155);
		colors[1] = new Color(84, 111, 170);
		colors[2] = new Color(90, 255, 235);
		colors[3] = new Color(53, 255, 170);

		int index = randomValue(0, colors.length);
		return colors[index];
	}

	public static int getRockID(boolean right, boolean up, boolean left, boolean down) {
		if (right) {
			if (up) {
				if (left) {
					if (down) {
						return 205;
					} else {
						return 204;
					}
				} else {
					if (down) {
						return 203;
					} else {
						return 202;
					}
				}
			} else {
				if (left) {
					if (down) {
						return 201;
					} else {
						return 200;
					}
				} else {
					if (down) {
						return 109;
					} else {
						return 108;
					}
				}
			}
		} else {
			if (up) {
				if (left) {
					if (down) {
						return 107;
					} else {
						return 106;
					}
				} else {
					if (down) {
						return 105;
					} else {
						return 104;
					}
				}
			} else {
				if (left) {
					if (down) {
						return 103;
					} else {
						return 102;
					}
				} else {
					if (down) {
						return 101;
					} else {
						return 10;
					}
				}
			}
		}
	}
	
	public static float[] getDir(int dir) {
		
		// (I calculated them earlier to save power)
		
		float value = 0.707106781186548f;
		
		float[] vel = new float[2];
		if (dir == 0) {
			vel[0] = 0;
			vel[1] = -1;
			return vel;
		} else if(dir == 1) {
			vel[0] = value;
			vel[1] = -value;
			return vel;
		} else if (dir == 2) {
			vel[0] = 1;
			vel[1] = 0;
			return vel;
		} else if (dir == 3) {
			vel[0] = value;
			vel[1] = value;
			return vel;
		} else if (dir == 4) {
			vel[0] = 0;
			vel[1] = 1;
			return vel;
		} else if (dir == 5) {
			vel[0] = -value;
			vel[1] = value;
			return vel;
		} else if (dir == 6) {
			vel[0] = -1;
			vel[1] = 0;
			return vel;
		} else if (dir == 7) {
			vel[0] = -value;
			vel[1] = -value;
			return vel;
		}
		System.out.println("The 'dir' is out of range (0 - 7): " + dir + ".");
		return null;
	}
}
