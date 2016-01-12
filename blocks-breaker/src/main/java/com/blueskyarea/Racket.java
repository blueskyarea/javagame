package com.blueskyarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 5;
	public static final int NO_COLLISION = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;

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

	public int collideWith(Ball ball) {
		// racket
		Rectangle racketRectLeft = new Rectangle(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
				WIDTH / 2, HEIGHT);

		Rectangle racketRectRight = new Rectangle(centerPos, MainPanel.HEIGHT - HEIGHT, WIDTH / 2,
				HEIGHT);

		// ball
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());

		if (racketRectLeft.intersects(ballRect)) {
			return LEFT;
		}
		if (racketRectRight.intersects(ballRect)) {
			return RIGHT;
		}

		return NO_COLLISION;
	}

	public int getVx() {
		return vx;
	}
}
