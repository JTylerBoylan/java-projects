package tyler.bounce.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.HUD;
import tyler.bounce.objects.Ball;
import tyler.bounce.objects.GameObject;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;

public class Target {

	int x,y;
	Handler handler;
	Cannon cannon;
	HUD hud;
	
	public Target (int x, int y, Handler handler, Cannon cannon, HUD hud) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.cannon = cannon;
		this.hud = hud;
	}
	
	public void tick() {
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject gm = handler.getObjects().get(i);
			if (gm.getBounds().intersects(getBounds())) {
				if (gm.getID() == ID.BALL) {
					cannon.addBall((Ball) gm);
					handler.removeObject(gm);
					Ball ball = (Ball) gm;
					hud.setScore(hud.getScore() + ball.getPower());
				}
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 100, 5);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 100, 5);
	}
	
}
