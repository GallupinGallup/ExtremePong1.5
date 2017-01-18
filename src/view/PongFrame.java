package view;

import javax.swing.JFrame;

import controller.PongController;
import model.KeyboardListener;

import java.awt.Dimension;

public class PongFrame extends JFrame
{
	
	private PongController baseController;
	private PongPanel appPanel;
	public PongFrame(PongController baseController)
	{
		super();
		this.baseController = baseController;
		//this.appPanel = new PongPanel(baseController);
		
		this.setupFrame();
		this.setupListeners();
	}
	
	private void setupFrame()
	{
		//this.setContentPane(appPanel);
		this.setSize(new Dimension(600, 600));
		this.setTitle("Extreme Pong");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setupListeners(){
		this.addKeyListener(new KeyboardListener());
	}
}
