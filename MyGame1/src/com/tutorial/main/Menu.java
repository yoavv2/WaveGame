package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud=hud;
	}

	public void mousePressed(MouseEvent e) {// (mx ,my) are variables that will store the X and y value of the mouse
											// press position
		int mx = e.getX();
		int my = e.getY();

		if (game.GameState == STATE.Menu) {
			// Play button
			if (mousOver(mx, my, 210, 130, 200, 64)) {
				
				game.GameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
			
				handler.addObject(new Player(game.WIDTH / 2 - 32, game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));
				
			}
			// Help button
			if (mousOver(mx, my, 210, 230, 200, 64)) {
				game.GameState = STATE.Help;

			}
			// Quit button
			if (mousOver(mx, my, 210, 330, 200, 64)) {

				System.exit(1);
			}
		}
		// Back button for Help
		if (game.GameState == STATE.Help) {

			if (mousOver(mx, my, 210, 330, 200, 64)) {

				game.GameState = STATE.Menu;
				return;

			}
		}
		
		if(game.GameState == STATE.End){
			
			//Try Again button
			if (mousOver(mx, my, 210, 230, 200, 64)) {

				game.GameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
			
				handler.addObject(new Player(game.WIDTH / 2 - 32, game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));
				

			}
			}
			//Back to menu
			if (mousOver(mx, my, 210, 330, 200, 64)) {

				game.GameState = STATE.Menu;
				

			}
			}
		
			
		
		
	

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mousOver(int mx, int my, int x, int y, int width, int height) {// check if our mouse is over a
																					// selected target

		if (mx > x && mx < x + width) {

			if (my > y && my < y + height) {

				return true;
			} else
				return false;

		} else
			return false;

	}

	public void tick() {

	}

	public void render(Graphics g) {
		if(game.GameState==STATE.Menu) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 40);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave", 220, 70);

			g.setFont(fnt2);
			g.drawRect(210, 130, 200, 64);
			g.drawString("Play", 260, 170);

			g.drawRect(210, 230, 200, 64);
			g.drawString("Help", 260, 270);

			g.drawRect(210, 330, 200, 64);
			g.drawString("Quit", 260, 370);

			 
		}else if(game.GameState==STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 40);
			Font fnt3 = new Font("arial", 1, 20);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("use ADWS keys to move player and dodge enemies",50,150);
			
			g.setFont(fnt2);
			g.drawRect(210, 330, 200, 64);
			g.drawString("Back", 260, 370);

			
		}else if(game.GameState==STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 40);
			Font fnt3 = new Font("arial", 1, 20);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game over", 200, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of :"+ hud.getScore()+" !!! " ,180,150);
			
			g.setFont(fnt2);
			g.drawRect(210, 230, 200, 64);
			g.drawString("Try Again", 220, 270);

			g.setFont(fnt3);
			g.drawRect(210, 330, 200, 64);
			g.drawString("Back To Menu", 220, 370);

			
		}

	}

}
