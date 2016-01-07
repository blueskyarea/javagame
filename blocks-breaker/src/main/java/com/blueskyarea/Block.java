package com.blueskyarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block {
	public static final int WIDTH = 40;
	public static final int HEIGHT = 16;

	public static final int NO_COLLISION = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int UP = 4;
	public static final int DOWN_LEFT = 5;
	public static final int DOWN_RIGHT = 6;
	public static final int UP_LEFT = 7;
	public static final int UP_RIGHT = 8;

	private int x, y;
	private boolean isActive;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		isActive = true;
	}

	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, WIDTH, HEIGHT);

		// draw frame
		g.setColor(Color.BLACK);
		g.drawRect(x, y, WIDTH, HEIGHT);
	}

	public boolean isActive() {
		return isActive;
	}

	public int collideWith(Ball ball) {
		Rectangle blockRect = new Rectangle(x, y, WIDTH, HEIGHT);

		int ballX = ball.getX();
		int ballY = ball.getY();
		int ballSize = ball.getSize();
		// collide from DOWN
		if (blockRect.contains(ballX, ballY) && blockRect.contains(ballX + ballSize, ballY)) {
			return DOWN;
		}
		// collide from LEFT
		if (blockRect.contains(ballX + ballSize, ballY)
				&& blockRect.contains(ballX + ballSize, ballY + ballSize)) {
			return LEFT;
		}
		// collide from RIGHT
		if (blockRect.contains(ballX, ballY) && blockRect.contains(ballX, ballY + ballSize)) {
			return RIGHT;
		}
		// collide from UP
		if (blockRect.contains(ballX, ballY + ballSize)
				&& blockRect.contains(ballX + ballSize, ballY + ballSize)) {
			return UP;
		}
		// collide from DOWN_LEFT
		if (blockRect.contains(ballX + ballSize, ballY)) {
			return DOWN_LEFT;
		}
		// collide from DOWN_RIGHT
		if (blockRect.contains(ballX, ballY)) {
			return DOWN_RIGHT;
		}
		// collide from UP_LEFT
		if (blockRect.contains(ballX + ballSize, ballY + ballSize)) {
			return UP_LEFT;
		}
		// collide from UP_RIGHT
		if (blockRect.contains(ballX, ballY + ballSize)) {
			return UP_RIGHT;
		}

		return NO_COLLISION;
	}

	public void delete() {
		isActive = false;
	}
}
