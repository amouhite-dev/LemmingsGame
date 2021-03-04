package lemmings;

//import javax.swing.SwingUtilities;
//
//public class AppLemmings {
//	public static void main(String[] args) {
//		
//		SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				new WindowsLemmings();
//			}
//		});
//	}
//}

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//public class AppLemmings {
//	public static void main(String[] args) {
//		LemmingsView game = new LemmingsView(new LemmingsGame());
//		JFrame frame = new JFrame("Pacman");
//		frame.add(game);
//		frame.setSize(875,675);
//		frame.setLocation(400, 200);
//		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		
//		boolean fin = false;
//		int tour = 0;
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		while(tour < 8) {
//			game.getGame().moveInit(tour);
//			tour++;
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			game.repaint();
//		}
//		
//		
//}
//
