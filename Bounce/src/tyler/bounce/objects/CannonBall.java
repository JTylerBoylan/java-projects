package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.Core;


public class CannonBall extends GameObject {

	private boolean isLarge;
	private float size = 1;
	private Color c;
	private int power;
	
	public CannonBall(float x, float y, ID id, int power, boolean isLarge) {
		super(x, y, id);
		this.power = power;
		this.c = Core.powerToColor(power);
		this.isLarge = isLarge;
		if (isLarge)
			size = 1.5f;
	}

	public void tick() {
		
	}
	
	/*public void fall(int length) {
		for (int i = 0; i < length; i++) {
			y--;
		}
	}*/

	public void render(Graphics g) {
		g.setColor(c);
		g.fillOval((int) x,(int) y, (int)(size * 15), (int)(size * 15));
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int)(size * 15), (int)(size * 15));
	}
	
	public boolean isLarge() {
		return isLarge;
	}
	
	public int getPower() {
		return power;
	}
}
