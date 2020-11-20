package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tyler.bounce.Core;
import tyler.bounce.HUD;

public class Player extends GameObject {

	private HUD hud;
	
	public Player(float x, float y, ID id, HUD hud) {
		super(x, y, id);
		this.hud = hud;
	}

	public void tick() {
		
		x += velX;
		
		x = Core.clamp(x, 95, Core.WIDTH - (215 - (60 - (getZ1()))));	
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRoundRect((int) x, (int) y, getZ1(), 10, 4, 4);
		g.setColor(Color.cyan);
		g.fillRoundRect((int) x, (int) getBounds().getCenterY() - 5, getZ1(), 5, 4, 4);
	}
	
	private int getZ1() {
		int y1 = hud.getBallsLeft();
		float zl = 20;
		float zh = 60;
		float yl = 0;
		float yh = 100;
		return (int) ((((zh-zl)/(yh-yl))*(y1-yl))+zl);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, getZ1(), 10);
	}

}
