package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private final String path = "/tex/";
	private final int width = 32, height = 32;
	private final double scale = 2.0;
	
	// Since we are scaling the images, I like to define the width and height of such images
	public int defaultWidth = (int) (width * scale);
	public int defaultHeight = (int) (height * scale);
	
	// Uses a spreadsheet for image assets
	private BufferedImage sheet;
	
	// Images
	public BufferedImage img1, img2;
	public BufferedImage[] anim1;
	
	public Assets() {
		sheet = ImageLoader.loadImg(path + "sheet.png");
		
		// Init anim size
		anim1 = new BufferedImage[7];
		
		// Crop images
		img1 = ImageManipulation.crop(sheet, 0, 0, width, height);
		img2 = ImageManipulation.crop(sheet, width, 0, width, height);
		
		for (int i = 0; i < anim1.length; i++) {
			anim1[i] = ImageManipulation.crop(sheet, width * i, height * 2, width, height);
		}
		
		// Scale images
		img1 = ImageManipulation.scaleNearest(img1, scale);
		img2 = ImageManipulation.scaleNearest(img2, scale);
		
		for (int i = 0; i < anim1.length; i++) {
			anim1[i] = ImageManipulation.scaleNearest(anim1[i], scale);
		}
		
		// Rotate images
		img1 = ImageManipulation.rotateImageByDegrees(img1, 90);
	}

}
