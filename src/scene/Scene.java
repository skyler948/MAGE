package scene;

import java.awt.Graphics;
import java.text.DecimalFormat;

import game.Game;

public abstract class Scene {
	
	protected Game game;
	protected int id;
	
	protected DecimalFormat df;
	
	public Scene(Game game, int id) {
		this.game = game;
		this.id = id;
		
		df = new DecimalFormat("#.###");
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public Game getGame() {
		return game;
	}
	
	public int getID() {
		return id;
	}
	
	public DecimalFormat getDecimalFormat() {
		return df;
	}
	
	public void setDecimalFormat(DecimalFormat df, int fractionDigits) {
		this.df = df;
		this.df.setMaximumFractionDigits(fractionDigits);
		this.df.setMaximumFractionDigits(fractionDigits);
	}
	
	public void setDecimalFormat(int fractionDigits) {
		df.setMaximumFractionDigits(fractionDigits);
		df.setMaximumFractionDigits(fractionDigits);
	}

}
