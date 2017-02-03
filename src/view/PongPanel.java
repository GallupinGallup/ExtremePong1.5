package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.PongController;
import model.KeyboardListener;

public class PongPanel extends JPanel 
{
	private PongController baseController;
	

	public PongPanel(PongController baseController)
	{
		super();
		this.baseController = baseController;
		
		
		
		setupPanel();
		setupListeners();
	}

	private void setupPanel() 
	{
	}

	private void setupListeners() 
	{
		this.addKeyListener(new KeyboardListener());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
