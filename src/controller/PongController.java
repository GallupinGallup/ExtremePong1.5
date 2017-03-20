package controller;

import model.GameLoop;
import model.KeyboardListener;
import view.PongFrame;

public class PongController
{
	private KeyboardListener keyboardListener;
	private GameLoop gameLoop;
	private PongFrame pongFrame;
	
	public PongController()
	{
		this.keyboardListener = new KeyboardListener();
		this.gameLoop = new GameLoop(this);
		this.pongFrame = new PongFrame(this);
	}
	
	public void start()
	{
		this.gameLoop.start();
	}
	
	public KeyboardListener getKeyboardListeners(){
		return keyboardListener;
	}
	
	public PongFrame getPongFrame(){
		
		
		
		
		return pongFrame;
		
	}
	
	public GameLoop getGameLoop(){
		return gameLoop;
	}
}
