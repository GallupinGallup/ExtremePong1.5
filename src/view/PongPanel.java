package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.PongController;
import model.KeyboardListener;

public class PongPanel extends JPanel 
{
	private PongController baseController;
	private SpringLayout baseLayout;
	private JLabel paddel1;
	private JLabel paddel2;
	private JLabel paddel3;
	private JLabel paddel4;
	private JLabel paddel5;

	public PongPanel(PongController baseController)
	{
		super();
		this.baseController = baseController;
		this.baseLayout = new SpringLayout();
		
		this.paddel1 = new JLabel("1");

		
		this.paddel1.setBackground(Color.BLACK);
		this.paddel1.setOpaque(true);
		this.paddel1.setVisible(true);
		
		this.paddel2 = new JLabel("2");
		
		this.paddel2.setOpaque(true);
		this.paddel2.setBackground(Color.BLACK);
		this.paddel2.setVisible(true);
		
		this.paddel3 = new JLabel("3");
		
		this.paddel3.setOpaque(true);
		this.paddel3.setBackground(Color.BLACK);
		this.paddel3.setVisible(true);
		
		this.paddel4 = new JLabel("4");
		
		this.paddel4.setOpaque(true);
		this.paddel4.setBackground(Color.BLACK);
		this.paddel4.setVisible(true);
		
		this.paddel5 = new JLabel("4");
		
		this.paddel5.setOpaque(true);
		this.paddel5.setBackground(Color.BLACK);
		this.paddel5.setVisible(true);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel() 
	{
		this.setPreferredSize(new Dimension(900, 600));
		this.setLayout(baseLayout);
		this.add(paddel1);
		this.add(paddel2);
		this.add(paddel3);
		this.add(paddel4);
		this.add(paddel5);
	}

	private void setupLayout() 
	{
		baseLayout.putConstraint(SpringLayout.NORTH, paddel1, 267, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, paddel1, -183, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, paddel1, -312, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, paddel2, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, paddel2, 216, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, paddel2, -534, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, paddel3, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, paddel4, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, paddel1, 580, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, paddel5, 283, SpringLayout.SOUTH, paddel2);
		baseLayout.putConstraint(SpringLayout.WEST, paddel5, 198, SpringLayout.EAST, paddel4);
		baseLayout.putConstraint(SpringLayout.SOUTH, paddel5, -261, SpringLayout.NORTH, paddel3);
		baseLayout.putConstraint(SpringLayout.EAST, paddel5, -214, SpringLayout.WEST, paddel1);
		baseLayout.putConstraint(SpringLayout.NORTH, paddel4, 267, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, paddel4, -183, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, paddel4, 18, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, paddel3, 216, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, paddel3, -534, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, paddel2, 20, SpringLayout.NORTH, this);
	}

	private void setupListeners() 
	{
		this.addKeyListener(new KeyboardListener());
	}
}
