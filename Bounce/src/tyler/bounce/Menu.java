package tyler.bounce;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import tyler.bounce.Core.STATE;
import tyler.bounce.map.Cannon;
import tyler.bounce.map.Wall;
import tyler.bounce.objects.Ball;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;
import tyler.bounce.objects.Player;


public class Menu extends MouseAdapter {

	private Core game;
	private Handler handler;
	private Cannon cannon;
	private HUD hud;
	
	public Menu (Core game, Handler handler, Cannon cannon, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.cannon = cannon;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int my = e.getY();
		int mx = e.getX();
		
		//play
		if (mouseOver(mx, my, Core.WIDTH/2 - 100, Core.HEIGHT/2 + 28, 200, 72)) {
			game.gameState = STATE.Game;
			handler.getObjects().clear();
			for (int i = 0; i < 100; i++) {
				cannon.addBall(new Ball(Core.WIDTH/2,Core.HEIGHT/2, ID.BALL, 1, false, handler, 0.5f));
			}
			handler.addObject(new Player(Core.WIDTH/2,Core.HEIGHT/2 + 100, ID.PLAYER, hud));
			handler.addObject(new Wall(Core.WIDTH - 155, 300, ID.WALL, 5, 200, handler));
			handler.addObject(new Wall(Core.WIDTH - 50, 0, ID.WALL, 5, 335, handler));
			handler.addObject(new Wall(90, 305, ID.WALL, 5, 200, handler));
			handler.addObject(new Wall(45, 0, ID.WALL, 5, 320, handler));
		}
		
		//quit
		if (mouseOver(mx, my, Core.WIDTH - 164, 20, 150, 56))
			System.exit(1);
		
		//help
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width)
			if (my > y && my < y + height)
				return true;
		return false;
		
	}
	
	public void tick() {
		Random r = new Random();
		if (r.nextInt(100) < 10) {
			handler.addObject(new Ball(r.nextInt(Core.WIDTH), -20, ID.BALL, r.nextInt(7), r.nextBoolean(), handler, r.nextFloat()));
		}
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1, 90);
		Font fnt1 = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("BOUNCE", Core.WIDTH/2 - 195, Core.HEIGHT/2 - 25);
		
		g.setFont(fnt1);
		
		g.drawRect(Core.WIDTH/2 - 100, Core.HEIGHT/2 + 28, 200, 72);
		g.drawString("PLAY", Core.WIDTH/2 - 66, Core.HEIGHT/2 + 80);
		
		g.setFont(fnt2);
		
		g.drawRect(Core.WIDTH - 164, 20, 150, 56);
		g.drawString("QUIT", Core.WIDTH - 124, 58);
	}
	
}

