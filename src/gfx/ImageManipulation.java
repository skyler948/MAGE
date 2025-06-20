package gfx;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageManipulation {
	
	// https://stackoverflow.com/a/37758533
	
	/** Rotates a BufferedImage
	 * @param img BufferedImage input
	 * @param angle rotate by x degrees
	 * @return BufferedImage
	 */
	public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
	    double rads = Math.toRadians(angle);
	    double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
	    int w = img.getWidth();
	    int h = img.getHeight();
	    int newWidth = (int) Math.floor(w * cos + h * sin);
	    int newHeight = (int) Math.floor(h * cos + w * sin);

	    BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = rotated.createGraphics();
	    AffineTransform at = new AffineTransform();
	    at.translate((newWidth - w) / 2, (newHeight - h) / 2);

	    int x = w / 2;
	    int y = h / 2;

	    at.rotate(rads, x, y);
	    g2d.setTransform(at);
	    g2d.drawImage(img, 0, 0, null);
	    g2d.dispose();

	    return rotated;
	}
	
	// https://stackoverflow.com/a/46211880
	
	/** Scales a BufferedImage by nearest
	 * @param input BufferedImage input
	 * @param scale by x multiplier
	 * @return BufferedImage
	 */
	public static BufferedImage scaleNearest(BufferedImage input, double scale) {
	    final int interpolation = AffineTransformOp.TYPE_NEAREST_NEIGHBOR;
	    return scale(input, scale, interpolation);
	}
	
	private static BufferedImage scale(final BufferedImage before, final double scale, final int type) {
	    int w = before.getWidth();
	    int h = before.getHeight();
	    int w2 = (int) (w * scale);
	    int h2 = (int) (h * scale);
	    BufferedImage after = new BufferedImage(w2, h2, before.getType());
	    AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
	    AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, type);
	    scaleOp.filter(before, after);
	    return after;
	}
	
	/** Crops a BufferedImage
	 * @param sheet BufferedImage input
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return BufferedImage
	 */
	public static BufferedImage crop(BufferedImage sheet, int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}


}
