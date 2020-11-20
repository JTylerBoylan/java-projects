package tyler.bounce;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import tyler.bounce.objects.GameObject;
import tyler.bounce.objects.Handler;
import tyler.bounce.objects.ID;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject tempObject = handler.getObjects().get(i);
			if (tempObject.getID() == ID.PLAYER) {
				if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject tempObject = handler.getObjects().get(i);
			if (tempObject.getID() == ID.PLAYER) {
				if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
			}
		}
	}
	
}
