package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.Core;

public class FakePlayer extends GameObject {

	public FakePlayer(float x, float y, ID id) {
		super(x, y, id);
	}

	boolean right = true;;
	
	public void tick() {
		if (x < 0)
			right = true;
		if (x > Core.WIDTH - 60)
			right = false;
		if (right)
			x += 5;
		else
			x -= 5;
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRoundRect((int) x, (int) y, 60, 10, 4, 4);
		g.setColor(Color.cyan);
		g.fillRoundRect((int) x, (int) getBounds().getCenterY() - 5, 60, 5, 4, 4);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 60, 10);
	}

}
