package tyler.bounce.objects;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public LinkedList<GameObject> getObjects(){
		return object;
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public int countObjects(ID id) {
		int count = 0;
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			if (temp.getID() == id)
				count++;
		}
		return count;
	}
}


