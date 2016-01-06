package com.blueskyarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 5;

	private int centerPos;
	private int vx;

	public Racket() {
		centerPos = MainPanel.WIDTH / 2;
		vx = 5;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT, WIDTH, HEIGHT);
	}

	public void move(int moveValue) {
		centerPos += moveValue;

		if (centerPos - WIDTH / 2 < 0) {
			centerPos = WIDTH / 2;
		}

		if (centerPos + WIDTH / 2 > MainPanel.WIDTH) {
			centerPos = MainPanel.WIDTH - WIDTH / 2;
		}
	}

	public boolean collideWith(Ball ball) {
		Rectangle racketRect = new Rectangle(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
				WIDTH, HEIGHT);

		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());

		if (racketRect.intersects(ballRect)) {
			return true;
		}

		return false;
	}

	public int getVx() {
		return vx;
	}
}
