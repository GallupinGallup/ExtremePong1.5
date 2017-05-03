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
	private int gameSpeed = 20;
	private boolean running = true;

	public GameLoop(PongController pongController) {
		this.pongController = pongController;
	}

	@Override
	public void run() {
		KeyboardListener keyboard = pongController.getKeyboardListeners();
		setupGame();
		repaintScreen();
		
		while (true) {
			try {
				Thread.sleep(gameSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			paddleMove();
			if (keyboard.playing) {
				doGameLoop();
				
			}else{
				do {
					yMove = (int) (Math.random() * 10) - 6;
				}while (yMove < 2 && yMove > -2);
				do {
					xMove = (int) (Math.random() * 10) - 6;
				}while (xMove < 2 && xMove > -2);
			}
			repaintScreen();
		}
	}

	private void setupGame() {
		KeyboardListener keyboard = pongController.getKeyboardListeners();
		int gameWidth = pongController.getPongFrame().getWidth();
		int gameHeight = pongController.getPongFrame().getHeight();
		this.leftPaddle = new Paddle(10);
		this.leftPaddle.setY((int) (gameHeight / 2 - (leftPaddle.getHeight() / 2)));
		this.rightPaddle = new Paddle(gameWidth - 20);
		this.rightPaddle.setY((int) (gameHeight / 2 - (rightPaddle.getHeight() / 2)));
		this.ball = new Ball();
		this.ball.setX((int) (gameWidth / 2 - (ball.getWidth() / 2)));
		this.ball.setY((int) (gameHeight / 2 - (ball.getHeight() / 2)));
		keyboard.playing = false;
		gameSpeed = 16;
	}
//startComplexity
	//startAbstraction
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
	}
//endComplexity
	private void xBounce() {
		if (gameSpeed >= 2) {
			gameSpeed = gameSpeed - 2;
		}else if(gameSpeed !=0)
		{
			gameSpeed = gameSpeed-1;
		}
		if (gameSpeed <= 6) {
			setLeftPaddleHeight(getLeftPaddle().getHeight() + 10);
			setRightPaddleHeight(getRightPaddle().getHeight() + 10);
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
	//endAbstraction

	private void paddleMove() {
		int gameHeight = pongController.getPongFrame().getHeight();
		KeyboardListener keyboard = pongController.getKeyboardListeners();
		if (keyboard.isUpPressed() && rightPaddle.getY() >= 3.1) {
			rightPaddle.setY(rightPaddle.getY() - 4);
		}
		if (keyboard.isDownPressed() && rightPaddle.getY() <= gameHeight - 75) {
			rightPaddle.setY(rightPaddle.getY() + 4);
		}
		if (keyboard.isWPressed() && leftPaddle.getY() >= 3.1) {
			leftPaddle.setY(leftPaddle.getY() - 4);
		}
		if (keyboard.isSPressed() && leftPaddle.getY() <= gameHeight - 75) {
			leftPaddle.setY(leftPaddle.getY() + 4);
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

	private void points() {
		if (ball.getX() > -1) {
			player2Score = player2Score + 1;
		} else {
			player1Score = player1Score + 1;
		}
		score = "Player 1: " + player1Score + " Player 2: " + player2Score;
		if (player1Score >= 10 || player2Score >= 10) {
			player1Score = 0;
			player2Score = 0;
			gameSpeed = 16;
		}
		setupGame();
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
	
	public void setRightPaddleHeight(int height){
		this.rightPaddle.setHeight(height);
	}
	
	public void setLeftPaddleHeight(int height){
		this.leftPaddle.setHeight(height);
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