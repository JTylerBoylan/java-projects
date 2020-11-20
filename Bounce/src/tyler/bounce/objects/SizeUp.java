package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SizeUp extends GameObject {

	Handler handler;
	
	public SizeUp(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject gm = handler.getObjects().get(i);
			if (gm.getBounds().intersects(getBounds())) {
				if (gm.getID() == ID.BALL) {
					Ball ball = (Ball) gm;
					if (ball.isLarge()) {
						ball.setLarge(false);
						//handler.addObject(new Ball((int) ball.getX(),(int) ball.getY(), ball.getID(), ball.getPower(), ball.isLarge(),ball.getHandler(),ball.getGravity()));
						//handler.addObject(new Ball((int) ball.getX(),(int) ball.getY(), ball.getID(), ball.getPower(), ball.isLarge(),ball.getHandler(),ball.getGravity()));
					} else
							ball.setLarge(true);
					handler.removeObject(this);
				}
			}
		}
	}
	int timer = 0;
	boolean up = true;
	int size = 20;
	public void render(Graphics g) {
		g.setColor(Color.white);
		if (timer > 500)
			up = false;
		if (timer < 0)
			up = true;
		
		if (up)
			timer++;
		else
			timer--;
		
		size = 20 + (timer/50);
			
		g.fillRoundRect((int) x,(int) y, size, size, 3, 3);
		g.setColor(Color.darkGray);
		Rectangle r = new Rectangle((int) x, (int) y, size, size);
		int z;
		float y = size;
		float zh = 6;
		float zl = 4;
		float yh = 30;
		float yl = 20;
		z = (int)((((zh-zl)/(yh-yl))*(y-yl))+zl);
		g.drawString("+", (int) r.getCenterX() - z,(int) r.getCenterY() + z);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,30,30);
	}

}
