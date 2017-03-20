package model;

import javax.swing.SwingUtilities;

import controller.PongController;

public class GameLoop extends Thread {
	private PongController pongController;

	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Ball ball;
	int yMove = (int) (Math.random() * 7) - 4;
	int xMove = (int) (Math.random() * 7) - 4;
	private static int player1Score = 0;
	private static int player2Score = 0;
	private static String score = "Player 1: " + player1Score + " Player 2: " + player2Score;

	public GameLoop(PongController pongController) {
		this.pongController = pongController;
	}

	@Override
	public void run() {
		setupGame();
		repaintScreen();
		while (yMove == 0)
		{
			double yMove = (int) (Math.random() * 10) - 5;
		}
		while (xMove == 0)
		{
			double xMove = (int) (Math.random() * 10) - 5;
		}
		while (true) {
			doGameLoop();
			repaintScreen();

			try {
				Thread.sleep(16); // 60 fps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void setupGame() {
		int gameWidth = pongController.getPongFrame().getWidth();
		int gameHeight = pongController.getPongFrame().getHeight();

		this.leftPaddle = new Paddle(10);
		this.leftPaddle.setY((int) (gameHeight / 2 - (leftPaddle.getHeight() / 2)));

		this.rightPaddle = new Paddle(gameWidth - 20);
		this.rightPaddle.setY((int) (gameHeight / 2 - (rightPaddle.getHeight() / 2)));

		this.ball = new Ball();
		this.ball.setX((int) (gameWidth / 2 - (ball.getWidth() / 2)));
		this.ball.setY((int) (gameHeight / 2 - (ball.getHeight() / 2)));
	}

	private void doGameLoop() {
		int gameWidth = pongController.getPongFrame().getWidth();
		int gameHeight = pongController.getPongFrame().getHeight();

		// If touching top test
		if (ball.getY() > 2) {

			// If touching bottom test
			if (ball.getY() < gameHeight - 40) {

				// If touching left paddle test
				if ((ball.getX() > leftPaddle.getX() + leftPaddle.getWidth())
						|| (ball.getY() > leftPaddle.getY() + leftPaddle.getHeight())
						|| (ball.getY() + ball.getHeight() < leftPaddle.getY())) {

					// If touching right paddle test
					if ((ball.getX() + ball.getWidth() < rightPaddle.getX())
							|| (ball.getY() > rightPaddle.getY() + rightPaddle.getHeight())
							|| (ball.getY() + ball.getHeight() < rightPaddle.getY())) {

						// If touching left or right side tests
						if ((ball.getX() < gameWidth - 17) && (ball.getX() > -1)) {
							ball.setX(ball.getX() + xMove);
							ball.setY(ball.getY() + yMove);
						} else {
							points();
						}
					} else {
						xBounce();
					}
				} else {
					xBounce();
				}
			} else {
				yBounce();
			}
		} else {
			yBounce();
		}
		paddleMove();
	}

	private void xBounce() {
		if(xMove < 0 && xMove > -5){
			xMove = xMove - 1;
		}else if (xMove < 5){
			yMove = xMove + 1;
		}
		xMove = xMove * -1;
		ball.setX(ball.getX() + xMove);
		ball.setY(ball.getY() + yMove);
	}

	private void yBounce() {
		yMove = yMove * -1;
		ball.setX(ball.getX() + xMove);
		ball.setY(ball.getY() + yMove);
	}

	private void paddleMove() {
		int gameHeight = pongController.getPongFrame().getHeight();
		KeyboardListener keyboard = pongController.getKeyboardListeners();
		if (keyboard.isUpPressed() && rightPaddle.getY() >= 3.1) {
			rightPaddle.setY(rightPaddle.getY() - 3);
		}
		if (keyboard.isDownPressed() && rightPaddle.getY() <= gameHeight - 75) {
			rightPaddle.setY(rightPaddle.getY() + 3);
		}
		if (keyboard.isWPressed() && leftPaddle.getY() >= 3.1) {
			leftPaddle.setY(leftPaddle.getY() - 3);
		}
		if (keyboard.isSPressed() && leftPaddle.getY() <= gameHeight - 75) {
			leftPaddle.setY(leftPaddle.getY() + 3);
		}
	}

	private void repaintScreen() {
		SwingUtilities.invokeLater(new Runnable() { // repaints the screen on
													// the EDT
			@Override
			public void run() {
				pongController.getPongFrame().repaint(); // repaint to entire
															// frame
			}
		});
	}
	private void points()
	{
		if(ball.getX() > -1)
		{
			player2Score = player2Score + 1;
		}else{
			player1Score = player1Score + 1;
		}
		score = "Player 1: " + player1Score + " Player 2: " + player2Score;
	}

	public Paddle getLeftPaddle() {
		return leftPaddle;
	}

	public void setLeftPaddle(Paddle leftPaddle) {
		this.leftPaddle = leftPaddle;
	}

	public Paddle getRightPaddle() {
		return rightPaddle;
	}

	public void setRightPaddle(Paddle rightPaddle) {
		this.rightPaddle = rightPaddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	public static String getScore() {
		return score;
	}
}
