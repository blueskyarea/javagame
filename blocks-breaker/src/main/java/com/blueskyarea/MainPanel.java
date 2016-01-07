package com.blueskyarea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 3437002139017470077L;
	public static final int WIDTH = 360;
	public static final int HEIGHT = 480;

	private static final int NUM_BLOCK_ROW = 10;
	private static final int NUM_BLOCK_COL = 7;
	private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;

	private Thread mainThread;
	private Racket racket;
	private Ball ball;
	private Block[] block;

	private boolean leftPressed;
	private boolean rightPressed;

	// ダブルバッファリング（db）用
	private Graphics dbg;
	private Image dbImage = null;

	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);

		addKeyListener(this);

		racket = new Racket();
		ball = new Ball();
		block = new Block[NUM_BLOCK];

		// Create blocks
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			for (int j = 0; j < NUM_BLOCK_COL; j++) {
				int x = j * Block.WIDTH + Block.WIDTH;
				int y = i * Block.HEIGHT + Block.HEIGHT;
				block[i * NUM_BLOCK_COL + j] = new Block(x, y);
			}
		}

		mainThread = new Thread(this);
		mainThread.start();
	}

	public void run() {
		while (true) {
			gameUpdate();

			gameRender();

			paintScreen();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void gameUpdate() {
		// move racket
		if (leftPressed) {
			racket.move(-racket.getVx());
		} else if (rightPressed) {
			racket.move(racket.getVx());
		}

		// move ball
		ball.move();

		// racket collide with ball
		if (racket.collideWith(ball)) {
			ball.boundY();
		}

		// block collide with ball
		for (int i = 0; i < NUM_BLOCK; i++) {
			if (!block[i].isActive()) {
				continue;
			}
			int collidePos = block[i].collideWith(ball);
			if (collidePos != Block.NO_COLLISION) {
				block[i].delete();
				switch (collidePos) {
				case Block.DOWN:
				case Block.UP:
					ball.boundY();
					break;
				case Block.LEFT:
				case Block.RIGHT:
					ball.boundX();
					break;
				case Block.UP_LEFT:
				case Block.UP_RIGHT:
				case Block.DOWN_LEFT:
				case Block.DOWN_RIGHT:
					ball.boundXY();
					break;
				}
				break; // break block one by one.
			}
		}

	}

	private void gameRender() {
		if (dbImage == null) {
			dbImage = createImage(WIDTH, HEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				dbg = dbImage.getGraphics();
			}
		}

		// buffer clear
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);

		racket.draw(dbg);
		ball.draw(dbg);
		for (int i = 0; i < NUM_BLOCK; i++) {
			if (block[i].isActive()) {
				block[i].draw(dbg);
			}
		}
	}

	private void paintScreen() {
		try {
			Graphics g = getGraphics();
			if ((g != null) && (dbImage != null)) {
				g.drawImage(dbImage, 0, 0, null);
			}
			Toolkit.getDefaultToolkit().sync();
			if (g != null) {
				g.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
	}

}
