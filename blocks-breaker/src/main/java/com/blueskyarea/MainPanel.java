package com.blueskyarea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable, KeyListener {
	public static final int WIDTH = 360;
	public static final int HEIGHT = 480;
	
	private Thread mainThread;
	private Racket racket;
	
	private boolean leftPressed;
	private boolean rightPressed;
	
	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		
		addKeyListener(this);
		
		racket = new Racket();
		
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void run() {
		while(true) {
			// move racket
			if (leftPressed) {
				racket.move(-racket.getVx());
			} else if(rightPressed) {
				racket.move(racket.getVx());
			}
			
			repaint();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		racket.draw(g);
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
