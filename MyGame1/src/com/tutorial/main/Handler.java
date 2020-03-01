package com.tutorial.main;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick();
		}

	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);

	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemys() {
//		Iterator<GameObject> iter = object.iterator();
//		while (iter.hasNext()) {
//			GameObject tempObject = iter.next();
//			if (tempObject.getId() != ID.Player) {
//				iter.remove();
//			}
//	  }	
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (tempObject.getId() == ID.Player) {

				object.clear();

				if (Game.GameState != Game.STATE.End) {

					addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
				}

			}

		}

	}
}
