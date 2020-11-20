package tyler.bounce;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import tyler.bounce.map.Cannon;
import tyler.bounce.map.Target;
import tyler.bounce.objects.FakePlayer;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;
import tyler.bounce.objects.Spawner;

public class Core extends Canvas implements Runnable{

	private static final long serialVersionUID = -6561259243770263995L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	Handler handler;
	Cannon cannon;
	Target target;
	HUD hud;
	Spawner spawner;
	Menu menu;
	
	public enum STATE {
		Menu,
		Game;
	}
	
	public STATE gameState = STATE.Menu;
	
	public Core() {
		handler = new Handler();
		cannon = new Cannon(handler);
		hud = new HUD(cannon, handler);
		target = new Target(WIDTH - 150, 330, handler, cannon,hud);
		spawner = new Spawner(handler);
		menu = new Menu(this, handler, cannon, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Bounce", this);
		
		Random r = new Random();
		
		handler.addObject(new FakePlayer(r.nextInt(WIDTH), HEIGHT - 70, ID.PLAYER));

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
			handler.tick();
		if (gameState == STATE.Game) {
			cannon.tick();
			target.tick();
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.Menu) {
			menu.tick();
		}
		
		int count = 0;
		for (int i = 0; i < handler.getObjects().size(); i++) {
			if (handler.getObjects().get(i).getID() == ID.BALL)
				count++;
		}
		if (hud.getBallsLeft() == 0 && count == 0) {
			//game end
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if (gameState == STATE.Game) {
			target.render(g);
			cannon.render(g); 
			hud.render(g);
			//spawner.render(g);
		} else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Core();
	}
	
	public static Color powerToColor(int i) {
		i = (int) Core.clamp(i, 1, 7);
		Color c;
		switch (i) {
		default:
			c = Color.white;
			break;
		case 1:
			c = Color.white;
			break;
		case 2:
			c = Color.yellow;
			break;
		case 3:
			c = Color.blue;
			break;
		case 4:
			c = Color.green;
			break;
		case 5:
			c = Color.cyan;
			break;
		case 6:
			c = Color.magenta;
			break;
		case 7:
			c = Color.pink;
			break;
		}
		return c;
	}
	
}
