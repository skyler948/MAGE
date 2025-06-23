package obj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;

public class ExampleObject extends TexturedObject {

	public ExampleObject(Game game, int x, int y, int width, int height, BufferedImage texture) {
		super(game, x, y, width, height, texture);
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, x, y, null);
	}
	
	public void tick() {
		x++;
	}

}
