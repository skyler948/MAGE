package scene;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Game;

public class SceneManager {
	
	private Game game;
	
	private int currentScene = 1;
	private ArrayList<Scene> scenes;
	
	public SceneManager(Game game) {
		this.game = game;
		
		scenes = new ArrayList<Scene>();
		
		// Scenes
		scenes.add(new MainMenuScene(game, 0));
		scenes.add(new GameScene(game, 1));
	}
	
	public void render(Graphics g) {
		scenes.get(currentScene).render(g);
	}
	
	public void tick() {
		scenes.get(currentScene).tick();
	}
	
	public Game getGame() {
		return game;
	}
	
	public int getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(int currentScene) {
		this.currentScene = currentScene;
	}
	
	public ArrayList<Scene> getScenes() {
		return scenes;
	}

}
