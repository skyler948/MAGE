package scene;

import java.awt.Font;
import java.awt.Graphics;

import audio.Song;
import game.Game;
import gfx.Animation;

public class GameScene extends Scene {
	
	private Animation anim;
	
	public GameScene(Game game, int id) {
		super(game, id);
		
		anim = new Animation(game, 600, 400, 2, true, game.getAssets().anim1);
		
		anim.play();
		
		game.getKeyboard().startRecordingInputs();
	}
	
	public void render(Graphics g) {
		g.drawImage(game.getAssets().img1, 100, 80, null);
		
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
