package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import game.Game;
import obj.Object;
import obj.ColoredObject;
import obj.TexturedObject;

public abstract class Scene {
	
	protected Game game;
	protected int id;
	
	protected DecimalFormat df;
	
	protected ArrayList<Object> objects;
	protected ArrayList<TexturedObject> texturedObjects;
	protected ArrayList<ColoredObject> coloredObjects;
	
	protected boolean debug = false;
	
	public Scene(Game game, int id) {
		this.game = game;
		this.id = id;
		
		df = new DecimalFormat("#.###");
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
		
		objects = new ArrayList<Object>();
		texturedObjects = new ArrayList<TexturedObject>();
		coloredObjects = new ArrayList<ColoredObject>();
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public void renderAllObjects(Graphics g) {
		for (TexturedObject obj : texturedObjects) {
			if (obj.isOnscreen()) {
				obj.render(g);
				
				// Debug (hitboxes)
				if (debug) {
					g.setColor(Color.red);
					g.drawRect(obj.getHitbox().x, obj.getHitbox().y, obj.getHitbox().width, obj.getHitbox().height);
				}
			}
		}
		for (ColoredObject obj : coloredObjects) {
			if (obj.isOnscreen()) {
				obj.render(g);
				
				// Debug (hitboxes)
				if (debug) {
					g.setColor(Color.red);
					g.drawRect(obj.getHitbox().x, obj.getHitbox().y, obj.getHitbox().width, obj.getHitbox().height);
				}
			}
		}
		
		// Since Objects aren't rendered, this needs to be done separately for debug mode!
		if (debug) {
			for (Object obj : objects) {
				if (obj.isOnscreen()) {
					g.setColor(Color.red);
					g.drawRect(obj.getHitbox().x, obj.getHitbox().y, obj.getHitbox().width, obj.getHitbox().height);
				}
			}
		}
	}
	
	public void tickAllObjects() {
		for (Object obj : objects) {
			obj.tick();
		}
		for (TexturedObject obj : texturedObjects) {
			obj.tick();
		}
		for (ColoredObject obj : coloredObjects) {
			obj.tick();
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	public int getID() {
		return id;
	}
	
	public DecimalFormat getDecimalFormat() {
		return df;
	}
	
	public void setDecimalFormat(DecimalFormat df, int fractionDigits) {
		this.df = df;
		this.df.setMaximumFractionDigits(fractionDigits);
		this.df.setMaximumFractionDigits(fractionDigits);
	}
	
	public void setDecimalFormat(int fractionDigits) {
		df.setMaximumFractionDigits(fractionDigits);
		df.setMaximumFractionDigits(fractionDigits);
	}

	public ArrayList<Object> getObjects() {
		return objects;
	}

	public ArrayList<TexturedObject> getTexturedObjects() {
		return texturedObjects;
	}

	public ArrayList<ColoredObject> getColoredObjects() {
		return coloredObjects;
	}
	
	/**For TexturedObjects and ColoredObjects, use their respective functions
	 * @param obj - Object
	 */
	public void addObject(Object obj) {
		objects.add(obj);
	}
	
	public void addTexturedObject(TexturedObject obj) {
		texturedObjects.add(obj);
	}
	
	public void addColoredObject(ColoredObject obj) {
		coloredObjects.add(obj);
	}
	
	/**For TexturedObjects and ColoredObjects, use their respective functions
	 * @param obj - Object
	 */
	public void removeObject(Object obj) {
		objects.remove(obj);
	}
	
	/**For TexturedObjects and ColoredObjects, use their respective functions
	 * @param obj - Object index
	 */
	public void removeObject(int obj) {
		objects.remove(obj);
	}
	
	public void removeTexturedObject(TexturedObject obj) {
		texturedObjects.remove(obj);
	}
	
	public void removeTexuredObject(int obj) {
		texturedObjects.remove(obj);
	}
	
	public void removeColoredObject(ColoredObject obj) {
		coloredObjects.remove(obj);
	}
	
	public void removeColoredObject(int obj) {
		coloredObjects.remove(obj);
	}
	
	/**For TexturedObjects and ColoredObjects, use their respective functions.
	 * use clearAllObjects(); to clear *all* objects
	 */
	public void clearObjects() {
		objects.clear();
	}
	
	public void clearTexturedObjects() {
		texturedObjects.clear();
	}
	
	public void clearColoredObjects() {
		coloredObjects.clear();
	}
	
	public void clearAllObjects() {
		clearObjects();
		clearTexturedObjects();
		clearColoredObjects();
	}
	
	public void setDebugMode(boolean debug) {
		this.debug = debug;
	}

}
