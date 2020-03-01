package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	private static final long serialVersionUID = -7259108873705494293L;

	public Window(int width, int hieght, String title, Game game) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, hieght));
		frame.setMaximumSize(new Dimension(width, hieght));
		frame.setMinimumSize(new Dimension(width, hieght));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}
}
