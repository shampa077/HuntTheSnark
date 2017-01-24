import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
class Gamewindow extends JFrame implements ActionListener,WindowListener{
	OptionGUI mainWindow;
	JPanel gridPanel;
	JButton gridButton[][];
	JButton okButton; 
	JButton cancelButton; 
	JPanel buttonPanel;
	JLabel actioncommand;
	JPanel actioncommandpanel;
	int dimension;
	int col;
	int row;
	boolean gameover=false;
	boolean bonushit=false;
	
	public Gamewindow(OptionGUI mainGui, int dim)
	{
		
		super ("Player Name:"+mainGui.playerone.name); 
		this.addWindowListener(this);
		
		this.mainWindow=mainGui;
		
		dimension=mainWindow.gridsize;
		gridPanel=new JPanel();
		gridPanel.setLayout(new GridLayout(dimension,dimension));
		
		gridButton= new JButton[dimension][dimension];
		URL s;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				//ImageIcon water = new ImageIcon("Image/tile_empty.png");
				s=getClass().getResource("Image/tile_empty.png");
					
				//System.out.println(s.toString());
				ImageIcon water = new ImageIcon(s);
				gridButton[i][j] = new JButton(water);
				//gridButton[i][j].setText("Button " + (i+j+1));
				gridButton[i][j].addActionListener(this);
				gridPanel.add(gridButton[i][j]);
			}
	
		}
		buttonPanel= new JPanel();
		actioncommandpanel= new JPanel();
		actioncommand=new JLabel("Game Statistics:");
		actioncommandpanel.add(actioncommand);
		okButton = new JButton ("Reset"); 
		cancelButton = new JButton ("Cancel"); 
		
		okButton.addActionListener(this);
		buttonPanel.add(okButton); 
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		this.setLayout(new BorderLayout());
		
		
		add(actioncommandpanel,BorderLayout.WEST);
		add(gridPanel,BorderLayout.NORTH);
		add(buttonPanel,BorderLayout.SOUTH);
		
		this.setSize(700+dimension*50, 500+dimension*50);
	    this.setVisible(true);
		
		
		
	}
	public void resetGame()
	{
		
		mainWindow.game.modifyGreed();
		mainWindow.game.printGrid();
		gameover=false;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				//ImageIcon water = new ImageIcon("Image/tile_empty.png");
				URL s=getClass().getResource("Image/tile_empty.png");
			    ImageIcon water = new ImageIcon(s);
				
				gridButton[i][j].setIcon(water);
				//gridButton[i][j].setText("Button " + (i+j+1));
				gridButton[i][j].setEnabled(true);
				gridPanel.add(gridButton[i][j]);
			}
	
		}
		actioncommand.setText("Game Statistics:");
	}
	
	public void overGame()
	{
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				//ImageIcon water = new ImageIcon("Image/tile_unsearched.png");
				URL s=getClass().getResource("Image/tile_unsearched.png");
				ImageIcon water = new ImageIcon(s);
		
				//gridButton[i][j].setText("Button " + (i+j+1));
				gridButton[i][j].setEnabled(false);
				gridButton[i][j].setDisabledIcon(water);
			}
	
		}
	}
	public void winGame()
	{
		ImageIcon water;
		URL s;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				
				
				Object o= mainWindow.game.grid[i][j];
				if (o instanceof Type2)
				{
					s=getClass().getResource("Image/tile_snark.png");
					water = new ImageIcon(s);
					//water = new ImageIcon("Image/tile_snark.png");
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setDisabledIcon(water);
					
				}
				else if (o instanceof Type1)
				{
					s=getClass().getResource("Image/tile_bonus_ammo.png");
					water = new ImageIcon(s);
					//water = new ImageIcon("Image/tile_bonus_ammo.png");
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setDisabledIcon(water);
					
					
				}
				else if (o instanceof Type3)
				{
					s=getClass().getResource("Image/tile_bonus_hint.png");
					water = new ImageIcon(s);
					//water = new ImageIcon("Image/tile_bonus_hint.png");
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setDisabledIcon(water);
					
					
				}
				else if (o instanceof Type4)
				{
					s=getClass().getResource("Image/tile_bonus_cannon.png");
					water = new ImageIcon(s);
					//water = new ImageIcon("Image/tile_bonus_cannon.png");
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setDisabledIcon(water);
					
				}
				else
				{
					s=getClass().getResource("Image/tile_empty.png");
					water = new ImageIcon(s);
					//water = new ImageIcon("Image/tile_empty.png");
				}
				
				
			}
	
		}
	}
	public boolean decisionGame()
	{
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				Object o1= mainWindow.game.grid[i][j];
				if (gridButton[i][j].isEnabled() && o1 instanceof Type2)
					return false;
			}
	
		}
		if (mainWindow.game.playerone.score>=0)
			return true;
		else
			return false;
	}
	public void bonusHit(int i,int j)
	{
		ImageIcon water;
		URL s;
		for (int i1=i; i1<(i+2);i1++)
		{
			for (int j1=j;j1<(j+2);j1++)
			{
				if (i1<dimension && j1<dimension)
				{
					Object o1= mainWindow.game.grid[i1][j1];
					if (o1 instanceof Type4)
					{
						s=getClass().getResource("Image/tile_bonus_cannon.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_bonus_cannon.png");
						gridButton[i1][j1].setEnabled(false);
						gridButton[i1][j1].setDisabledIcon(water);
						System.out.println("disbale a game over"+i1+" "+j1);
					
					}
					else if ( o1 instanceof Type1)
					{
						s=getClass().getResource("Image/tile_bonus_ammo.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_bonus_ammo.png");
						gridButton[i1][j1].setEnabled(false);
						gridButton[i1][j1].setDisabledIcon(water);
						System.out.println("disbale a danger"+i1+" "+j1);
					}
					else if ( o1 instanceof Type2)
					{
						//do nothing
						if (gridButton[i1][j1].isEnabled())
							actioncommand.setText(actioncommand.getText()+"\t"+" Hints: Snark is in position [col][row]: ["+ i1+ "] ["+ j1+" ]");
					}
					
					
				}
				
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		
		ImageIcon water;
		URL s;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) 
			{
				if (e.getSource()==gridButton[i][j])
				{
					System.out.println((i+j+1)+"button pressed");
					col=i;
					row=j;
					
					mainWindow.game.grid[col][row].printInfo();
					Object o= mainWindow.game.grid[col][row];
					if (o instanceof Type2)
					{
						System.out.print("Got Snark");
						mainWindow.game.playerone.score+=10;
						mainWindow.game.savegame.point.add(10);
						s=getClass().getResource("Image/tile_snark.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_snark.png");
						gridButton[i][j].setIcon(water);
						actioncommand.setText("Game Statistics:\t"+" A Snark has been found :) :) "+"\n"+" Current Score:"+mainWindow.game.playerone.score);
					}
					else if (o instanceof Type1)
					{
						System.out.print("Danger");
						mainWindow.game.playerone.score-=5;
						mainWindow.game.savegame.point.add(-5);
						s=getClass().getResource("Image/tile_bonus_ammo.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_bonus_ammo.png");
						gridButton[i][j].setIcon(water);
						actioncommand.setText("Game Statistics:\t"+" OOps a danger "+"\n"+" Current Score:"+mainWindow.game.playerone.score);
						
					}
					else if (o instanceof Type3)
					{
						System.out.print("Bonus");
						mainWindow.game.playerone.score+=0;
						mainWindow.game.savegame.point.add(0);
						s=getClass().getResource("Image/tile_bonus_hint.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_bonus_hint.png");
						gridButton[i][j].setIcon(water);
						actioncommand.setText("Game Statistics:\t"+" Got a Bonus Hints"+"\n"+" Current Score:"+mainWindow.game.playerone.score);
						bonushit=true;
						bonusHit(i,j);
						bonushit=false;
						
					}
					else if (o instanceof Type4)
					{
						System.out.print("Game Over");
						mainWindow.game.playerone.score=0;
						mainWindow.game.savegame.point.add(0);
						s=getClass().getResource("Image/tile_bonus_cannon.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_bonus_cannon.png");
						gridButton[i][j].setIcon(water);
						gameover=true;
					}
					else
					{
						System.out.print("Nothing");
						s=getClass().getResource("Image/tile_empty.png");
						water = new ImageIcon(s);
						//water = new ImageIcon("Image/tile_empty.png");
					}
				    
					
					mainWindow.game.playerone.printInfo();
					mainWindow.game.savegame.buttonpressed.add(row+col+1);
					mainWindow.game.savegame.type.add(mainWindow.game.grid[col][row]);
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setDisabledIcon(water);
					if (gameover)
					{
						actioncommand.setText("Game Statistics:\t"+" Game is Over!!!"+"\n"+" Current Score:"+mainWindow.game.playerone.score);
						JOptionPane.showMessageDialog(null, "Game over");
						overGame();
						int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Start the Game Again ?");
						System.out.println(dialogResult);
						if(dialogResult == 0){
							resetGame();
						}
						else
						{
							this.setVisible(false);
							mainWindow.setVisible(true);
							mainWindow.window=null;
							
						}
						

					}
					else if (decisionGame())
					{
						actioncommand.setText("Game Statistics:\t"+" You have won!!!"+"\n"+" Current Score:"+mainWindow.game.playerone.score);
						winGame();
						int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Start the Game Again ?");
						System.out.println(dialogResult);
						if(dialogResult == 0){
							resetGame();
						}
						else
						{
							this.setVisible(false);
							mainWindow.setVisible(true);
							mainWindow.window=null;
							
						}
						
					}
				}
				
			}
		}
			
		
		if (e.getSource()==okButton)
		{
			

			System.out.println("Reset button pressed");
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Really Like to Reset the Game?");
			System.out.println(dialogResult);
			if(dialogResult == 0){
				resetGame();
			}
			
			
		}
		else if (e.getSource()==cancelButton)
		{
			mainWindow.game.playerone.printInfo();
			System.out.println("Cancel button pressed");
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Really Like to Exit the Game?");
			System.out.println(dialogResult);
			if(dialogResult == 0){
				this.setVisible(false);
				mainWindow.setVisible(true);
				mainWindow.window=null;
				
			}
			
		}
		
		
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		mainWindow.setVisible(true);
		mainWindow.window=null;
		
	}
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}