package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.PongController;
import model.Ball;
import model.GameLoop;
import model.Paddle;

public class PongPanel extends JPanel {
	private PongController pongController;
	private Color purpleColor;
	private Color redColor;

	public PongPanel(PongController pongController) {
		super();
		this.pongController = pongController;
		this.purpleColor = new Color(137, 0, 255);
		this.redColor = new Color(178, 34, 34);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int gameWidth = pongController.getPongFrame().getWidth();

		
		super.paintComponent(g);
		Paddle leftPaddle = pongController.getGameLoop().getLeftPaddle();
		Paddle rightPaddle = pongController.getGameLoop().getRightPaddle();
		Ball ball = pongController.getGameLoop().getBall();

		g.setColor(purpleColor);
		g.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
		g.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());
		g.setColor(redColor);
		g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		
	}
}
