package model;

import javax.swing.SwingUtilities;

import controller.PongController;

public class GameLoop extends Thread
{
	private PongController pongController;
	
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Ball ball;
	
	
	public GameLoop(PongController pongController) {
		this.pongController = pongController;
	}
	
	@Override
	public void run() {
		setupGame();
		repaintScreen();
		
		while(true){
			doGameLoop();
			repaintScreen();
			
			try {
				Thread.sleep(16); // 60 fps
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	private void setupGame(){
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
	
	private void doGameLoop(){
		KeyboardListener keyboard = pongController.getKeyboardListeners();
		int gameWidth = pongController.getPongFrame().getWidth();
		int gameHeight = pongController.getPongFrame().getHeight();
		
		if(keyboard.isUpPressed() && rightPaddle.getY() >= 3.3)
		{
			rightPaddle.setY(rightPaddle.getY() - 3);
		}
		if(keyboard.isDownPressed() && rightPaddle.getY() <= gameHeight - 75)
		{
			rightPaddle.setY(rightPaddle.getY() + 3);
		}
		if(keyboard.isWPressed())
		{
			leftPaddle.setY(leftPaddle.getY() - 3);
		}
		if(keyboard.isSPressed())
		{
			leftPaddle.setY(leftPaddle.getY() + 3);
		}
	}
	
	private void repaintScreen(){
		SwingUtilities.invokeLater(new Runnable() { // repaints the screen on the EDT
			@Override
			public void run() {
				pongController.getPongFrame().repaint(); // repaint to entire frame
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
