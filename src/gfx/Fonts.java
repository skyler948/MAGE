package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import game.Game;

public class Fonts {
	
	private Game game;
	
	public Fonts(Game game) {
		this.game = game;
		
		init();
		/*
		String fonts[] = game.getDisplay().getGenv().getAvailableFontFamilyNames();
		for (String i : fonts) {
            System.out.println(i + " ");
        }*/
	}
	
	private void init() {
		try {
			// All fonts go here
			// Press Start 2P
			game.getDisplay().getGenv().registerFont(Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/fon/ComicSans.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Missing font!");
			System.exit(1);
		}
	}

}
