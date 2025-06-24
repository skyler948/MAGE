package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import audio.Song;
import game.Game;
import gfx.Animation;
import obj.ExampleObject;

public class GameScene extends Scene {
	
	private Animation anim;
	private ExampleObject obj;
	
	public GameScene(Game game, int id) {
		super(game, id);
		
		anim = new Animation(game, 600, 400, 2, true, game.getAssets().anim1);
		
		anim.play();
		
		game.getKeyboard().startRecordingInputs();
		
		obj = new ExampleObject(game, 100, 80, game.getAssets().defaultWidth, game.getAssets().defaultHeight, game.getAssets().img1);
		addTexturedObject(obj);
		
		// Shows hitboxes
		setDebugMode(true);
	}
	
	public void render(Graphics g) {
		renderAllObjects(g);
		
		g.setColor(Color.white);
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		g.drawString("Press HOME to play a song :3", 300, 300);
		
		if (Song.isPlaying()) {
			g.drawString("Now Playing: " + Song.getFilename(), 400, 500);
			g.drawString("Press END to stop.", 400, 530);
		}
		
		g.drawString("Typed String (Enter to stop): " + game.getKeyboard().getInputs(), 20, 600);
		
		anim.render(g);
	}
	
	public void tick() {
		tickAllObjects();
		
		if (game.getMouse().leftMouse) {
			System.out.println("Pressing the left mouse?!?");
		}
		
		if (game.getKeyboard().playsong && !Song.isStarted()) {
			Song.playSong("/songs/retrofuture.wav");
		}
		if (game.getKeyboard().stopsong && Song.isPlaying()) {
			Song.stopCurrentSong();
		}
		
		anim.tick();
	}

}
