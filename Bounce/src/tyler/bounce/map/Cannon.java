package tyler.bounce.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import tyler.bounce.Core;
import tyler.bounce.objects.Ball;
import tyler.bounce.objects.CannonBall;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;

public class Cannon {

	Handler handler;
	int fireTimer;
	boolean doFire;
	int difficulty = 0;
	
	LinkedList<CannonBall> line = new LinkedList<CannonBall>();
	
	public Cannon (Handler handler) {
		this.handler = handler;
		fireTimer = 200;
		doFire = false;
	}
	
	public void tick() {
		if (fireTimer < 0)
			doFire = true;
		else
			doFire = false;
		trigger();
		fireTimer--;
		
	}
	
	public LinkedList<CannonBall> getCannonLine(){
		return line;
	}
	public void trigger() {
		try {
			if (line.getFirst() != null && doFire) {
				fire();
				difficulty++;
				fireTimer = 200 - difficulty;
				fireTimer = (int) Core.clamp(fireTimer, 50, 200);
				System.out.println(fireTimer);
			}
		} catch(Exception e) {
			
		}
	}
	
	public void addBall(Ball b) {
		/*int lineSize = 0;
		for (int i = 0; i < line.size(); i++) {
			CannonBall tempBall = line.get(i);
			if (tempBall.isLarge()) {
				lineSize += (int) (15 * 1.5);
			} else
				lineSize += 15;
		}
		int posX  = 50;
		int posY = 300 - lineSize;
		if (b.isLarge()) {
			posX = 47;
			posY = (300 - lineSize) - 7;
		}*/
		CannonBall cb = new CannonBall(Core.WIDTH, Core.HEIGHT, ID.CANNON_BALL, b.getPower(), b.isLarge());
		//handler.addObject(new CannonBall(posX, posY, ID.CANNON_BALL, b.getPower(), b.isLarge()));
		line.add(cb);
	}
	
	public void fire() {
		Random r = new Random();
		CannonBall cb = line.get(0);
		int velX = r.nextInt(1) + 2;
		int velY = r.nextInt(4) + 4;
		//handler.removeObject(line.get(0));
		handler.addObject(new Ball(50, 300, ID.BALL, cb.getPower(), cb.isLarge(), handler, 0.1f, velX, -velY));
		line.removeFirst();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(50, 300, 40, 20);
	}
	
}
