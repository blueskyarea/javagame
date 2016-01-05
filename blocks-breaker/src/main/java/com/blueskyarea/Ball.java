package com.blueskyarea;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	private static final int SIZE = 8;

	private int x, y;
	private int vx, vy;

	private Random rand;

	public Ball() {
		rand = new Random(System.currentTimeMillis());

		x = rand.nextInt(MainPanel.WIDTH - SIZE);
		y = 0;

		vx = 5;
		vy = 5;
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, SIZE, SIZE);
	}

	public void move() {
		x += vx;
		y += vy;

		if (x < 0) {
			x = 0;
			vx = -vx;
		} else if (x > MainPanel.WIDTH) {
			x = MainPanel.WIDTH;
			vx = -vx;
		}

		if (y < 0) {
			y = 0;
			vy = -vy;
		} else if (y > MainPanel.HEIGHT) {
			y = MainPanel.HEIGHT;
			vy = -vy;
		}
	}
}
