package tyler.bounce.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import tyler.bounce.Core;

public class Spawner {

	private Handler handler;
	int time = 0;
	
	public Spawner(Handler handler) {
		this.handler = handler;
	}
	
	public void newRandomSpawn() {
		Random r = new Random();
		boolean b = r.nextBoolean();
		if (b)
			handler.addObject(new PowerUp(r.nextInt(200) + (Core.WIDTH/2),(Core.HEIGHT/2) -  r.nextInt(200), ID.POWERUP, handler));
		else
			handler.addObject(new SizeUp(r.nextInt(200) + (Core.WIDTH/2),(Core.HEIGHT/2) -  r.nextInt(200), ID.POWERUP, handler));
	}
	
	public void tick() {
		time++;
		if (time > 1500) {
			newRandomSpawn();
			time = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(Core.WIDTH/2, Core.HEIGHT/2 - 200, 200, 200);
	}
	
}
