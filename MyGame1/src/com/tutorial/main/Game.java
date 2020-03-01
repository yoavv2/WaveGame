package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1550691097823471818L;

	static final int WIDTH = 640;

	static final int HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public enum STATE {

		Menu, Game, Help,  End

	};

	public static STATE GameState = STATE.Menu;

	public Game() {

		r = new Random();
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		this.addKeyListener(new keyInput(handler));
		new Window(WIDTH, HEIGHT, "Wave Game!", this);

	

		spawner = new Spawn(handler, hud);
	
		if (GameState == STATE.Game) {

			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));

		}else {
			for(int i=0;i<10;i++) {
				
				handler.addObject(new MenuParticle(r.nextInt(WIDTH) , r.nextInt(HEIGHT) , ID.MenuParticle, handler));
				
			}
			
			
			
		}
		

//		for (int i = 0; i < 2; i++) {
//			handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
//
//		}

		// handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32, ID.Player2));
//		handler.addObject(new Player(200, 200, ID.Player));

		// handler.addObject(new BasicEnemy(WIDTH/2-32, HEIGHT/2-32, ID.BasicEnemy));

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
		} catch (Exception e) {

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

	public static float clamp(float x, float min, float max) {
		if (x > max)
			x = max;
		if (x < min)
			x = min;
		return x;
	}

	private void tick() {
		handler.tick();
		if (GameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			
			
			if(HUD.HELTH <= 0) {
				
				HUD.HELTH=100;
				GameState = STATE.End; 
				handler.clearEnemys();
				
				
				for(int i=0;i<10;i++) {
					
					handler.addObject(new MenuParticle(r.nextInt(WIDTH) , r.nextInt(HEIGHT) , ID.MenuParticle, handler));
					
				}
			}
			
		} else if (GameState == STATE.Menu || GameState == STATE.End) {

			menu.tick();

		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {

			this.createBufferStrategy(3);
			return;

		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (GameState == STATE.Game) {
			hud.render(g);
		} else if (GameState == STATE.Menu || GameState==STATE.Help || GameState == STATE.End) {

			menu.render(g);

		}
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		new Game();
	}

}
