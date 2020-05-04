package dev.logflames.platformer.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;

	public static BufferedImage background, playerClone, halfRock;
	public static BufferedImage currentPlayer;
	public static BufferedImage spikes, spikes2, spikes3, spikes4, big_spikes, big_spikes2;
	public static BufferedImage rock0000, rock0001, rock0010, rock0011, rock0100, rock0101, rock0110, rock0111,
			rock1000, rock1001, rock1010, rock1011, rock1100, rock1101, rock1110, rock1111;

	public static BufferedImage[] pause_btn, pause_btn2, arrow_btn, arrow_btn2, character_btn;

	public static BufferedImage[] player;

	public static BufferedImage eye;

	public static BufferedImage lavaFull;

	public static BufferedImage[] lava, goal, sawblade;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet lavaSheet = new SpriteSheet(ImageLoader.loadImage("/textures/lava.png"));
		SpriteSheet goalSheet = new SpriteSheet(ImageLoader.loadImage("/textures/goal.png"));
		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/buttons.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/character.png"));
		SpriteSheet sawbladeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sawblade.png"));

		lava = new BufferedImage[8];
		goal = new BufferedImage[2];
		sawblade = new BufferedImage[3];
		pause_btn = new BufferedImage[2];
		pause_btn2 = new BufferedImage[2];
		arrow_btn = new BufferedImage[2];
		arrow_btn2 = new BufferedImage[2];
		player = new BufferedImage[9];
		character_btn = new BufferedImage[2];

		player[0] = playerSheet.crop(0, 0, width, height);
		player[1] = playerSheet.crop(width, 0, width, height);
		player[2] = playerSheet.crop(width * 2, 0, width, height);
		player[3] = playerSheet.crop(width * 3, 0, width, height);
		player[4] = playerSheet.crop(width * 4, 0, width, height);
		player[5] = playerSheet.crop(width * 5, 0, width, height);
		player[6] = playerSheet.crop(width * 6, 0, width, height);
		player[7] = playerSheet.crop(width * 7, 0, width, height);
		player[8] = playerSheet.crop(0, height, width, height);

		background = sheet.crop(width, 0, width, height);
		playerClone = sheet.crop(width * 3, 0, width, height);
		spikes = sheet.crop(width * 5, 0, width, height);
		spikes2 = sheet.crop(width * 5, height, width, height);
		spikes3 = sheet.crop(width * 2, height * 2, width, height);
		spikes4 = sheet.crop(width * 3, height * 2, width, height);

		big_spikes = sheet.crop(width * 6, height, width, height);
		big_spikes2 = sheet.crop(width * 7, height, width, height);

		halfRock = sheet.crop(width * 4, 0, width, height);

		rock0000 = sheet.crop(width * 2, height, width, height);
		rock0001 = sheet.crop(width, height * 2, width, height);
		rock0010 = sheet.crop(width * 4, height, width, height);
		rock0011 = sheet.crop(0, height * 3, width, height);
		rock0100 = sheet.crop(width * 3, height, width, height);
		rock0101 = sheet.crop(0, 0, width, height);
		rock0110 = sheet.crop(width * 7, height * 2, width, height);
		rock0111 = sheet.crop(width * 7, 0, width, height);
		rock1000 = sheet.crop(0, height * 2, width, height);
		rock1001 = sheet.crop(width, height * 3, width, height);
		rock1010 = sheet.crop(width, height, width, height);
		rock1011 = sheet.crop(width * 5, height * 2, width, height);
		rock1100 = sheet.crop(width * 6, height * 2, width, height);
		rock1101 = sheet.crop(width * 6, 0, width, height);
		rock1110 = sheet.crop(width * 4, height * 2, width, height);
		rock1111 = sheet.crop(0, height, width, height);

		eye = sheet.crop(width * 7 + 13, height + 12, 4, 4);

		lava[0] = lavaSheet.crop(0, 0, width, height);
		lava[1] = lavaSheet.crop(width, 0, width, height);
		lava[2] = lavaSheet.crop(width * 2, 0, width, height);
		lava[3] = lavaSheet.crop(width * 3, 0, width, height);
		lava[4] = lavaSheet.crop(width * 4, 0, width, height);
		lava[5] = lavaSheet.crop(width * 5, 0, width, height);
		lava[6] = lavaSheet.crop(width * 6, 0, width, height);
		lava[7] = lavaSheet.crop(width * 7, 0, width, height);

		lavaFull = lavaSheet.crop(0, height, width, height);

		goal[0] = goalSheet.crop(0, 0, width, height);
		goal[1] = goalSheet.crop(width, 0, width, height);
		
		sawblade[0] = sawbladeSheet.crop(0, 0, width, height);
		sawblade[1] = sawbladeSheet.crop(width, 0, width, height);
		sawblade[2] = sawbladeSheet.crop(width * 2, 0, width, height);

		pause_btn[0] = buttonSheet.crop(0, 0, width, height);
		pause_btn[1] = buttonSheet.crop(0, 0, width, height);
		pause_btn2[0] = buttonSheet.crop(width * 2, 0, width, height);
		pause_btn2[1] = buttonSheet.crop(width * 3, 0, width, height);
		arrow_btn[0] = buttonSheet.crop(width * 4, 0, width, height);
		arrow_btn[1] = buttonSheet.crop(width * 4, 0, width, height);
		arrow_btn2[0] = buttonSheet.crop(width * 5, 0, width, height);
		arrow_btn2[1] = buttonSheet.crop(width * 5, 0, width, height);

		character_btn[0] = buttonSheet.crop(width, 0, width, height);
		character_btn[1] = buttonSheet.crop(width, 0, width, height);

		currentPlayer = player[0];
	}

	public int getImageWidth() {
		return width;
	}

	public int getImageHeight() {
		return height;
	}

	public static void setCurrentPlayer(BufferedImage image) {
		currentPlayer = image;
	}
}
