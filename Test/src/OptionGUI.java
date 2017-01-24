import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
public class OptionGUI extends JFrame implements ActionListener,WindowListener{ 
			
	
			JTextField displayText; 
			JComboBox menus;
			JButton okButton; 
			JButton cancelButton; 
			JPanel buttonPanel;
			JPanel inputPanel;
			Player playerone;
			GameController game;
			int gridsize=4;
			Gamewindow window;
			JLabel namepanel,gridpanelsize;
			JLabel imageback;


			public OptionGUI  () { 
				super ("Hunt The Snark"); 
				 
				ImageIcon imageIcon = new ImageIcon("Image\\images.jpg");
			
				this.addWindowListener(this);
				this.getContentPane().setBackground(Color.RED);
				buttonPanel= new JPanel();
				inputPanel =new JPanel();
				namepanel=new JLabel("Enter Name");
				gridpanelsize=new JLabel("Enter Grid Size");
				// create a new LayoutManager and set it for the frame
				buttonPanel.setLayout(new FlowLayout()); 
				inputPanel.setLayout(new FlowLayout()); 
				
				displayText= new JTextField("",10);
				/// add them
				inputPanel.add(namepanel);
				inputPanel.add(displayText);

				String[] sizes = { "3", "4", "5", "6", "7" };
				menus= new JComboBox(sizes);
				menus.setSelectedIndex(1);
				menus.addActionListener(this);
				inputPanel.add(gridpanelsize);
				inputPanel.add(menus);
				buttonPanel.setBackground(Color.RED);
				inputPanel.setBackground(Color.RED);
				
				//URL s=getClass().getResource("tile_empty.png");
				
				//ImageIcon water = new ImageIcon(s);
				okButton = new JButton ("OK"); 
				//okButton = new JButton (water);
				cancelButton = new JButton ("Cancel"); 
				
				okButton.addActionListener(this);
				buttonPanel.add(okButton); 
				cancelButton.addActionListener(this);
				buttonPanel.add(cancelButton);
				
				// add the components to the container
				//buttonPanel.add(displayText); 
				 
				setLayout(new BorderLayout());
				//add(displayText);
				add(inputPanel,BorderLayout.NORTH);
				add(buttonPanel,BorderLayout.SOUTH);
				setBackground(Color.RED);
				
				setSize(500,150);
				
				setVisible(true);
				repaint();
				

			} 
			public void windowClosing(WindowEvent e)
			{
				    System.out.println("Window Main game is closing");
					System.exit(0); // exit JVM
			}
			public void windowOpened(WindowEvent e)
			{
				
			}
			
			public void windowClosed(WindowEvent e)
			{
				
			}
			public void windowIconified(WindowEvent e)
			{
				
			}
			public void windowDeiconified(WindowEvent e)
			{
				
			}
			public void windowActivated(WindowEvent e)
			{
				
			}
			public void windowDeactivated(WindowEvent e)
			{
				
			}
			public void windowResized(WindowEvent evt)
            {
                
            }
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==okButton)
				{
					System.out.println("Ok button pressed");
					if(displayText.getText().equals(""))
						playerone =new Player("Guest");
					else
						playerone =new Player(displayText.getText());
					playerone.printInfo();
					game= new GameController(playerone,gridsize);
					game.printGrid();
					
					window=new Gamewindow(this,game.grids);
					this.setVisible(false);
					//window.setVisible(true);
				}
				else if (e.getSource()==cancelButton)
				{
					System.out.println("Cancel button pressed");
					System.exit(1);
				}
				else if (e.getSource()==menus)
				{
					 int size = Integer.parseInt((String)menus.getSelectedItem());
				     
					 System.out.println("Selected panel size"+size+"X"+size);
					 gridsize=size;
				}
				
			}
			
			
		}

