package tyler.bounce;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tyler.bounce.map.Cannon;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;

public class HUD {

	private int score = 0;
	private int ballsLeft = 100;
	private Cannon cannon;
	private Handler handler;
	boolean game = true;
	
	public HUD (Cannon cannon, Handler handler) {
		this.cannon = cannon;
		this.handler = handler;
		game = true;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getBallsLeft() {
		return ballsLeft;
	}
	
	public void setScore(int i) {
		score = i;
	}
	
	public void setBallsLeft(int i) {
		ballsLeft = i;
	}
	
	private int timer = 100;
	
	public void tick() {
		int count = 0;
		for (int i = 0; i < cannon.getCannonLine().size(); i++) {
			count++;
		}
		ballsLeft = count;
		if (ballsLeft == 0 && handler.countObjects(ID.BALL) == 0) {
			game = false;
			if (timer < 0)
				new Core();
			else
				timer--;
		}
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", 1, 10));
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + ballsLeft, 65, 312);
		g.setFont(new Font("TimesRoman", 1, 15));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Score : " + score, 70, 50);
		if (!game) {
			g.setFont(new Font("TimesRoman", 1, 30));
			g.drawString("Game Over", Core.HEIGHT/2 - 50, Core.WIDTH/2 - 50);
			g.setFont(new Font("TimesRoman", 1, 25));
			g.drawString("Score: " + score, Core.HEIGHT/2 - 40, Core.WIDTH/2 - 40);
		}
	}
	
}
