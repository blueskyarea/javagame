package com.blueskyarea;

import javax.swing.JFrame;

public class BlocksBreaker extends JFrame {
	private static final long serialVersionUID = 7868393402903695328L;

	public BlocksBreaker() {
		setTitle("BlocksBreaker");
		setResizable(false);

		MainPanel panel = new MainPanel();
		getContentPane().add(panel);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BlocksBreaker();
	}
}
