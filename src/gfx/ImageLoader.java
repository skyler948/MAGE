package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	/**
	 * @param path for image, "/" directs to "res" as a root folder
	 * @return BufferedImage
	 */
	public static BufferedImage loadImg(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.out.println("Missing Image at path: " + path);
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

}
