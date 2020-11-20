package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.Core;

public class Ball extends GameObject {

	private int power;
	private float size, gravity;
	private boolean large;
	private Handler handler;
	private Color col;
	public Ball(float x, float y, ID id, int power, boolean large, Handler handler, float gravity) {
		super(x, y, id);
		this.handler = handler;
		this.power = power;
		this.gravity = gravity;
		this.large = large;
		this.col = Core.powerToColor(power);
		if (large)
			size = 1.5f;
		else
			size = 1;
	}
	public Ball(float x, float y, ID id, int power, boolean large, Handler handler, float gravity, float velX, float velY) {
		super(x, y, id);
		this.velX = velX;
		this.velY = velY;
		this.handler = handler;
		this.power = power;
		this.gravity = gravity;
		this.large = large;
		this.col = Core.powerToColor(power);
		if (large)
			size = 1.5f;
		else
			size = 1;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		handler.addObject(new Trail(x ,y, ID.TRAIL, col, (int) (size * 15), (int) (size * 15), 0.08f, handler));
		
		x = Core.clamp(x, 49, Core.WIDTH - 64);
		
		velY = Core.clamp(velY, -10, 10);
		
		setVelY(getVelY() + gravity);
		bounce();
		
		if (y > Core.HEIGHT) {
			handler.removeObject(this);
		}
	}
	
	public void bounce() {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject tempObject = handler.getObjects().get(i);
			if (getBounds().intersects(tempObject.getBounds())/* && (x > tempObject.getBounds().getCenterX() - 20 && x < tempObject.getBounds().getCenterX() + 20)*/) {
				if (tempObject.getID() == ID.PLAYER) {
					velY = -velY;
					velX = sideBounce(tempObject, x, velX) + ( tempObject.velX/2 * (1 + (tempObject.getVelX() / 10)));
				}
			}
		}
	}
	
	public float sideBounce(GameObject gm, float x, float velX) {
		double range = (gm.getBounds().getCenterX() - 20) - x;
		return (float) (velY * (range/30));
	}

	public void render(Graphics g) {		
		g.setColor(col);
		g.fillOval((int) x, (int) y, (int) (size * 15), (int) (size * 15));
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) (size * 15), (int) (size * 15));
	}
	
	public void setPower(int i) {
		power = i;
		col = Core.powerToColor(power);
	}
	
	public void setLarge(boolean b) {
		large = b;
		if (large)
			size = 1.5f;
		else
			size = 1;
	}
	
	public int getPower() {
		return power;
	}
	public boolean isLarge() {
		return large;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public float getGravity() {
		return gravity;
	}

}
