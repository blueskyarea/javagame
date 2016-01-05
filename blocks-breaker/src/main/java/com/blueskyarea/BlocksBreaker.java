package com.blueskyarea;

import javax.swing.JFrame;

public class BlocksBreaker extends JFrame {
	
	public BlocksBreaker() {
		setTitle("BlocksBreaker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel panel = new MainPanel();
		getContentPane().add(panel);
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BlocksBreaker();
	}
}
