package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import display.Display;
import gfx.Assets;
import gfx.Fonts;
import input.KeyboardInput;
import input.MouseInput;
import scene.SceneManager;
import settings.Settings;

public class Game implements Runnable {
	
	// Version String for YOUR game, not MAGE!
	private final String version = "Pre-Alpha v0.0.1";
	
	private boolean running = false;
	private Thread thread;
	
	private Settings settings;
	
	private Display display;
	
	private Graphics g;
	private BufferStrategy bs;
	
	private final int tps = 60;
	private int currentTPS;
	
	private int fps;
	private int currentFPS;
	
	private SceneManager sceneManager;
	
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	
	private Assets assets;
	private Fonts fonts;
	
	private void init() {
		settings = new Settings();
		settings.readSettingsFile();
		
		display = new Display(settings);
		
		if (settings.vsync) {
			fps = display.getDisplayRefreshRate();
		} else {
			fps = settings.fps;
		}
		
		assets = new Assets();
		fonts = new Fonts(this);
		
		keyboardInput = new KeyboardInput(this);
		mouseInput = new MouseInput(this);
		
		sceneManager = new SceneManager(this);
		
		display.getFrame().addKeyListener(keyboardInput);
		display.getCanvas().addMouseListener(mouseInput);
		display.getCanvas().addMouseMotionListener(mouseInput);
	}
	
	public void run() {
		init();
		
		// Render (fps, variable)
        double timePerTickRender = 1000000000 / fps;
        double deltaRender = 0;
        long nowRender;
        long lastTimeRender = System.nanoTime();
        long timerRender = 0;
        int ticksRender = 0;
        
        // Tick (tps, locked)
        double timePerTickTick = 1000000000 / tps;
        double deltaTick = 0;
        long nowTick;
        long lastTimeTick = System.nanoTime();
        long timerTick = 0;
        int ticksTick = 0;
		
		while (running) {
            Toolkit.getDefaultToolkit().sync();

            nowRender = System.nanoTime();
            deltaRender += (nowRender - lastTimeRender) / timePerTickRender;
            timerRender += nowRender - lastTimeRender;
            lastTimeRender = nowRender;

            nowTick = System.nanoTime();
            deltaTick += (nowTick - lastTimeTick) / timePerTickTick;
            timerTick += nowTick - lastTimeTick;
            lastTimeTick = nowTick;

            if (deltaRender >= 1) {
                render();
                ticksRender++;
                deltaRender--;
            }
            if (deltaTick >= 1) {
                tick();
                ticksTick++;
                deltaTick--;
            }

            if (timerRender >= 1000000000) {
                //System.out.println("FPS: " + ticksRender);
            	currentFPS = ticksRender;
                ticksRender = 0;
                timerRender = 0;
            }
            if (timerTick >= 1000000000) {
            	//System.out.println("TPS: " + ticksTick);
            	currentTPS = ticksTick;
                timerTick = 0;
                ticksTick = 0;
            }
		}
		
		stop();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, display.getWidth(), display.getHeight());
		// Draw
		
		g.setColor(Color.black);
		g.fillRect(0, 0, display.getWidth(), display.getHeight());
		
		g.setColor(Color.white);
		g.drawString(version, 3, 14);
		if (settings.fpsDisplay) g.drawString("FPS: " + currentFPS, 3, 29);
		
		sceneManager.render(g);
		
		// End
		bs.show();
		g.dispose();
	}
	
	private void tick() {
		sceneManager.tick();
		
		keyboardInput.tick();
		mouseInput.tick();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running) return;
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getVersionString() {
		return version;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public int getTpsCap() {
		return tps;
	}
	
	public int getCurrentTps() {
		return currentTPS;
	}
	
	public int getFpsCap() {
		return fps;
	}
	
	public void setFpsCap(int fps) {
		this.fps = fps;
	}
	
	public int getCurrentFps() {
		return currentFPS;
	}
	
	public KeyboardInput getKeyboard() {
		return keyboardInput;
	}
	
	public MouseInput getMouse() {
		return mouseInput;
	}
	
	public Assets getAssets() {
		return assets;
	}
	
	public Fonts getFonts() {
		return fonts;
	}

}
