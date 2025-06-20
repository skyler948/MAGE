package display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import settings.Settings;

public class Display {
	
	private int width, height;
	private final String title = "MAGE R1";
	
	private GraphicsEnvironment genv;
	private GraphicsDevice gdev;
	private int displayRefreshRate;
	
	private JFrame frame;
	private Canvas canvas;
	
	private Dimension dimension;
	
	private Settings settings;
	
	public Display(Settings settings) {
		this.settings = settings;
		
		genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gdev = genv.getDefaultScreenDevice();
		displayRefreshRate = gdev.getDisplayMode().getRefreshRate();
		
		if (settings.fullscreen) {
			width = gdev.getDisplayMode().getWidth();
			height = gdev.getDisplayMode().getHeight();
		} else {
			width = settings.width;
			height = settings.height;
		}
		
		createDisplay();
	}
	
	private void createDisplay() {
		dimension = new Dimension(width, height);
		
		frame = new JFrame(title);
		frame.setSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		if (settings.fullscreen) {
			frame.setUndecorated(true);
			gdev.setFullScreenWindow(frame);
		}
		
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public GraphicsEnvironment getGenv() {
		return genv;
	}

	public GraphicsDevice getGdev() {
		return gdev;
	}

	public int getDisplayRefreshRate() {
		return displayRefreshRate;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Settings getSettings() {
		return settings;
	}

}
