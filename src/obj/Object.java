package obj;

import java.awt.Rectangle;

import game.Game;

public abstract class Object {
	
	protected Game game;
	
	protected int x, y;
	protected int width, height;
	protected Rectangle hitbox;
	
	public Object(Game game, int x, int y, int width, int height) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setHitbox(x, y, width, height);
	}
	
	public abstract void tick();
	
	public void updateHitbox() {
		setHitboxPosition(x, y);
	}
	
	public boolean isOnscreen() {
		if (x > 0 && x < game.getDisplay().getWidth() + width && y > 0 && y < game.getDisplay().getHeight() + height) {
			return true;
		}
		return false;
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
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		setHitboxSize(width, height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		setHitboxSize(width, height);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		setHitboxSize(width, height);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public Rectangle getBounds() {
		return hitbox.getBounds();
	}
	
	public void setHitbox(int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public void setHitboxPosition(int x, int y) {
		hitbox.x = x;
		hitbox.y = y;
	}
	
	public void setHitboxSize(int width, int height) {
		hitbox.width = width;
		hitbox.height = height;
	}

}
