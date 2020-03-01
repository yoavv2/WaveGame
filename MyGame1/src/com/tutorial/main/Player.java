package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
//		velX=r.nextInt(5)+1;
//		velY=-r.nextInt(5);

//		velX = 1;
//		velY=-2;

	}

	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 30);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		collision();
		
		handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.white,32,32,0.08f,handler));
		
	}
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.BasicEnemy ||tempObject.getId()==ID.FastEnemy|| tempObject.getId()==ID.SmartEnemy) {
				// collision code 
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					HUD.HELTH-=2;
					
					
				}
				
			}
			
		}
	}

	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		if (id == ID.Player)
			g.setColor(Color.white);
		// else if(id==ID.Player2) g.setColor(Color.blue);
		g.fillRect((int)x,(int) y, 32, 32);
	}

}
