package gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;

public class Animation {
	
	private Game game;
	private int x, y;
	private int delay;
	private BufferedImage[] frames;
	
	private boolean loopable = false;
	
	private int frame;
	private int timer = 0;
	private boolean playing = false;
	
	public Animation(Game game, int x, int y, int delay, boolean loopable, BufferedImage[] frames) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.delay = delay;
		this.loopable = loopable;
		this.frames = frames;
	}
	
	public void render(Graphics g) {
		g.drawImage(frames[frame], x, y, null);
	}
	
	public void tick() {
		if (playing) {
			timer++;
			if (timer % (game.getTpsCap() / delay) == 0) {
				if (frame < frames.length - 1) {
					frame++;
				} else if (!loopable) {
					playing = false;
				} else if (loopable) {
					frame = 0;
				}
			}
		}
	}
	
	public void play() {
		if (playing) return;
		playing = true;
	}
	
	public void reset() {
		frame = 0;
	}
	
	public void stop() {
		if (!playing) return;
		playing = false;
	}

	public Game getGame() {
		return game;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public BufferedImage[] getFrames() {
		return frames;
	}

	public int getFrame() {
		return frame;
	}
	
	public boolean isLoopable() {
		return loopable;
	}

	public boolean isPlaying() {
		return playing;
	}

}
