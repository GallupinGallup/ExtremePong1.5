package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	
	private GameLoop gameLoop;
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;
	private boolean pPressed;
	private boolean running = true;
	public boolean playing = false;
	
	public KeyboardListener(GameLoop gameLoop) {
		this.gameLoop = gameLoop;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	} // unused

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			playing = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			running = false;
			System.exit(0);
		}else
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD0)
		{
			gameLoop.setRightPaddleHeight(gameLoop.getRightPaddle().getHeight() + 20);
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			gameLoop.setLeftPaddleHeight(gameLoop.getLeftPaddle().getHeight() + 20);
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			gameLoop.setRightPaddleHeight(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD1)
		{
			gameLoop.setLeftPaddleHeight(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isWPressed() {
		return wPressed;
	}

	public boolean isSPressed() {
		return sPressed;
	}

	public boolean isPPressed() {
		return pPressed;
	}

	public boolean isPlaying() {
		return playing;
	}

	public boolean isRunning() {
		return running;
	}
}