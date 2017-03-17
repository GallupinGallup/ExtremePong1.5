package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;
	
	@Override
	public void keyTyped(KeyEvent e) { } // unused

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			wPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			sPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			upPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			wPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
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

}
