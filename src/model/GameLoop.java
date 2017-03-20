package model;

import javax.swing.SwingUtilities;

import controller.PongController;

public class GameLoop extends Thread {
	private PongController pongController;

	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Ball ball;
	int yMove = (int) (Math.random() * 10) - 5;
	int xMove = (int) (Math.random() * 10) - 5;

	public GameLoop(PongController pongController) {
		this.pongController = pongController;
	}

	@Override
	public void run() {
		setupGame();
		repaintScreen();

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
		yMove = 0;
		xMove = -4;
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
							xBounce();
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

}
