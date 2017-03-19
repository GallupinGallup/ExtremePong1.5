package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.PongController;

public class PongFrame extends JFrame {
	private PongController pongController;
	private PongPanel appPanel;

	public PongFrame(PongController pongController) {
		super();
		this.pongController = pongController;
		this.appPanel = new PongPanel(pongController);

		this.setupFrame();
		this.setupListeners();
	}

	private void setupFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(appPanel);
		this.setSize(new Dimension(800, 500));
		this.setTitle("Extreme Pong");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void setupListeners() {
		this.addKeyListener(pongController.getKeyboardListeners());
	}

	public PongPanel getPongPanel() {
		return appPanel;
	}
}
