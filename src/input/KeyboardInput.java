package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;

public class KeyboardInput implements KeyListener {
	
	private boolean keys[];
	private Game game;
	
	public boolean esc;
	private boolean endrec;
	
	public boolean right, up, down, left;
	
	public boolean playsong, stopsong;
	
	private String input = "";
	private boolean recording = false;
	
	public KeyboardInput(Game game) {
		this.game = game;
		
		keys = new boolean[512];
	}
	
	public void tick() {
		esc = keys[KeyEvent.VK_ESCAPE];
		endrec = keys[KeyEvent.VK_ENTER];
		
		right = keys[game.getSettings().right];
		down = keys[game.getSettings().down];
		up = keys[game.getSettings().up];
		left = keys[game.getSettings().left];
		
		playsong = keys[KeyEvent.VK_HOME];
		stopsong = keys[KeyEvent.VK_END];
		
		if (esc) System.exit(0);
		
		if (endrec) stopRecordingInputs();
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		//System.out.println(e.getKeyCode());
		
		if (recording) {
			// Letters (+ others)
			if (e.getKeyCode() >= 65 && e.getKeyCode() <= 92) {
				input = input + (e.getKeyChar() + "");
			}
			// Numbers (+ others)
			if (e.getKeyCode() >= 44 && e.getKeyCode() <= 59) {
				input = input + (e.getKeyChar() + "");
			}
			// More other characters
			if (e.getKeyCode() == 222 || e.getKeyCode() == 61) {
                input = input + (e.getKeyChar() + "");
			}
			// Space
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				input = input + " ";
			}
			// Backspace
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && input.length() > 0) {
				input = input.substring(0, input.length() - 1);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public Game getGame() {
		return game;
	}
	
	public void startRecordingInputs() {
		recording = true;
	}
	
	public void stopRecordingInputs() {
		recording = false;
	}
	
	public String getInputs() {
		return input;
	}
	
	public void resetInputs() {
		input = "";
	}

}
