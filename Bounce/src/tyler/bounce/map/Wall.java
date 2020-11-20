package tyler.bounce.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.objects.GameObject;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;

public class Wall extends GameObject {

	private int x,y,width,height;
	private Handler handler;
	
	public Wall(float x, float y, ID id, int width, int height, Handler handler) {
		super(x, y, id);
		this.x =  (int) x;
		this.y = (int) y;
		this.width = width;
		this.height = height;
		this.handler = handler;
	}
	
	public void tick() {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);
			if (temp.getBounds().intersects(getBounds())){
				if (temp.getY() < y + 1) {
					temp.setY((int) (temp.getY() - temp.getVelY()));
					temp.setVelY(temp.getVelY() * -1);
					if (Math.abs(temp.getVelX()) < 0.1){
						temp.setVelX((temp.getVelX() * 1.5f) + 0.1f);
					}
				}
				temp.setX((int) (temp.getX() - temp.getVelX()));
				temp.setVelX(temp.getVelX() * -0.1f);
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
}
