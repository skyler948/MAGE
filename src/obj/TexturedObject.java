package obj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;

public abstract class TexturedObject extends Object {
	
	protected BufferedImage texture;
	protected BufferedImage[] textures;

	public TexturedObject(Game game, int x, int y, int width, int height, BufferedImage texture) {
		super(game, x, y, width, height);
		this.texture = texture;
	}
	
	public TexturedObject(Game game, int x, int y, int width, int height, BufferedImage[] textures) {
		super(game, x, y, width, height);
		this.textures = textures;
	}
	
	public abstract void render(Graphics g);

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public BufferedImage[] getTextures() {
		return textures;
	}

	public void setTextures(BufferedImage[] textures) {
		this.textures = textures;
	}

}
