package lemmings;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowsLemmings {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Lemmings");
		LemmingsView view = new LemmingsView(new LemmingsGame(),frame);
		frame.setSize(889,689);
		frame.setLocation(0,  0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(view);
		
		boolean fin = false;// deviens true lorsqu'un 
		long totalLemmings = view.getGame().getNbLem();
		
		/*Attends 2s avant de lancer le jeu */
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(view.getGame().enter == true) {
			view.getGame().moveInit(view.getGame().nbLemmings);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			view.repaint();
		}
		
		while (fin == false) {
			
			if(view.getGame().enter == true) {
				totalLemmings = view.getGame().getNbLem();
				view.getGame().moveInit(view.getGame().nbLemmings);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				view.repaint();
			}
			
			view.getGame().step();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			view.repaint();
			
			if(view.getGame().getNbLem() == 0) {
				fin = true;
				JOptionPane.showMessageDialog(frame,
			            "Vous avez perdu",
			            "Message",
			            JOptionPane.PLAIN_MESSAGE);
			} else if(view.getGame().score() == view.getGame().getNbLem() *10) {
				fin = true;
				if(view.getGame().score() < totalLemmings*10){
					JOptionPane.showMessageDialog(frame,
				            "Vous avez perdus. Des lemmings sont morts.)",
				            "Message",
				            JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame,
				            "Vous avez gagner",
				            "Message",
				            JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		
		System.exit(0);;
	}	
}

