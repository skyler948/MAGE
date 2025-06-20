package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Game;

public class MouseInput implements MouseListener, MouseMotionListener {

	private boolean keys[];
	private Game game;
	
	public int mouseX, mouseY;
	public boolean leftMouse, rightMouse;
	
	public MouseInput(Game game) {
		this.game = game;
		keys = new boolean[128];
	}
	
	public void tick() {
		leftMouse = keys[game.getSettings().leftMouse];
		rightMouse = keys[game.getSettings().rightMouse];
	}
	
	public void mousePressed(MouseEvent e) {
		keys[e.getButton()] = true;
		//System.out.println(e.getButton());
	}
	
	public void mouseReleased(MouseEvent e) {
		keys[e.getButton()] = false;
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public Game getGame() {
		return game;
	}

}
