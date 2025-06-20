package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;

public class KeyboardInput implements KeyListener {
	
	private boolean keys[];
	private Game game;
	
	public boolean esc;
	public boolean right, up, down, left;
	
	public boolean playsong, stopsong;
	
	public KeyboardInput(Game game) {
		this.game = game;
		
		keys = new boolean[512];
	}
	
	public void tick() {
		esc = keys[KeyEvent.VK_ESCAPE];
		
		right = keys[game.getSettings().right];
		down = keys[game.getSettings().down];
		up = keys[game.getSettings().up];
		left = keys[game.getSettings().left];
		
		playsong = keys[KeyEvent.VK_SPACE];
		stopsong = keys[KeyEvent.VK_BACK_SPACE];
		
		if (esc) System.exit(0);
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		//System.out.println(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public Game getGame() {
		return game;
	}

}
