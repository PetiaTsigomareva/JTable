package main;

import javax.swing.JFrame;

import presentation.GoodsFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GoodsFrame frame = new GoodsFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

}