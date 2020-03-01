package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static float HELTH = 100;
	private float GreenValue = 255;

	private int score = 0;
	private int level = 1;

	public void tick() {

		// HELTH--;
		HELTH = (int) Game.clamp(HELTH, 0, 100);
		
		GreenValue = (int) Game.clamp(GreenValue, 0, 255);
		GreenValue = HELTH * 2;
		score++;
	}


	public void render(Graphics g) {

		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		// g.setColor(Color.GREEN);
		g.setColor(new Color(75,(int) GreenValue, 0));
		g.fillRect(15, 15, (int)HELTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);

		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}

	public void setScore(int score) {

		this.score = score;
	}

	public int getScore() {
	// if the Player is not moving the score will end up at 1200 + -
		return score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		
		this.level=level;
	}
}
