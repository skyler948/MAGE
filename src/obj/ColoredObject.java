package obj;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;

public abstract class ColoredObject extends Object {
	
	protected Color inColor, outColor;

	public ColoredObject(Game game, int x, int y, int width, int height, Color inColor) {
		super(game, x, y, width, height);
		this.inColor = inColor;
	}
	
	public ColoredObject(Game game, int x, int y, int width, int height, Color inColor, Color outColor) {
		super(game, x, y, width, height);
		this.inColor = inColor;
		this.outColor = outColor;
	}
	
	public abstract void render(Graphics g);

	public Color getInColor() {
		return inColor;
	}

	public void setInColor(Color inColor) {
		this.inColor = inColor;
	}

	public Color getOutColor() {
		return outColor;
	}

	public void setOutColor(Color outColor) {
		this.outColor = outColor;
	}
	
	public void setColors(Color inColor, Color outColor) {
		this.inColor = inColor;
		this.outColor = outColor;
	}

}
