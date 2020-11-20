package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import tyler.bounce.Core;

public class PowerUp extends GameObject {

	Handler handler;
	
	private int time = 0;
	
	public PowerUp(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		time++;
		if (time > 1000)
			handler.removeObject(this);
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject gm = handler.getObjects().get(i);
			if (gm.getBounds().intersects(getBounds())) {
				if (gm.getID() == ID.BALL) {
					Ball ball = (Ball) gm;
					ball.setPower(ball.getPower()+1);
					handler.removeObject(this);
				}
			}
		}
	}

	int timer = 0;
	int i = 0;
	
	public void render(Graphics g) {
		if (timer > 500) {
			Random r = new Random();
			i = (int) Core.clamp(r.nextInt(7), 2, 7);
			timer = 0;
		} else {
			timer++;
		}
		Color c = Core.powerToColor(i);
		g.setColor(c);
		g.fillRoundRect((int) x,(int) y, 20, 20, 1, 1);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,20,20);
	}

}
