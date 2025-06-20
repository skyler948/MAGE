package settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Settings {
	
	private ArrayList<String> default_values;
	private ArrayList<String> values;
	private final String directory = "program/settings.txt";
	
	private boolean damaged = false;
	
	// Values
	public int width, height;
	public boolean vsync;
	public int fps;
	public boolean fpsDisplay;
	public boolean fullscreen;
	public int right, down, up, left;
	public int leftMouse, rightMouse;
	
	public Settings() {
		default_values = new ArrayList<String>();
		values = new ArrayList<String>();
		
		// Default values
		default_values.add("1280");		// WIDTH		(0)
		default_values.add("720");		// HEIGHT		(1)
		default_values.add("true");		// V-SYNC		(2)
		default_values.add("60");		// FPS			(3)
		default_values.add("true");		// DISPLAY FPS  (4)
		default_values.add("false");	// FULLSCREEN	(5)
		default_values.add("68");		// RIGHT		(6)
		default_values.add("83");		// DOWN			(7)
		default_values.add("87");		// UP			(8)
		default_values.add("65");		// LEFT			(9)
		default_values.add("1");		// LEFT MOUSE	(10)
		default_values.add("3");		// RIGHT MOUSE	(11)
	}
	
	public void readSettingsFile() {
		try {
			File settings = new File(directory);
			
			if (!settings.exists()) {
				settings.getParentFile().mkdir();
				settings.createNewFile();
				
				// Creates the default values if the settings file does not exist
				createDefaultValues();
			}
			
			Scanner scanner = new Scanner(settings);
			
			while (scanner.hasNextLine()) {
				values.add(scanner.nextLine());
			}
			
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		decodeSettingsValues();
		
		if (damaged) {
			createDefaultValues();
			decodeSettingsValues();
		}
	}
	
	private void createDefaultValues() {
		try {
			FileWriter settings = new FileWriter(directory, false);
			
			for (int i = 0; i < default_values.size(); i++) {
				settings.write(default_values.get(i)+"\n");
			}
			
			settings.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void decodeSettingsValues() {
		// Integers
		try {
			width = Integer.parseInt(values.get(0));
			height = Integer.parseInt(values.get(1));
			fps = Integer.parseInt(values.get(3));
			right = Integer.parseInt(values.get(6));
			down = Integer.parseInt(values.get(7));
			up = Integer.parseInt(values.get(8));
			left = Integer.parseInt(values.get(9));
			leftMouse = Integer.parseInt(values.get(10));
			rightMouse = Integer.parseInt(values.get(11));
		} catch (NumberFormatException e) {
			damaged = true;
			e.printStackTrace();
		}
		
		// Booleans
		vsync = Boolean.parseBoolean(values.get(2));
		fpsDisplay = Boolean.parseBoolean(values.get(4));
		fullscreen = Boolean.parseBoolean(values.get(5));
		
		// etc..
	}

}
