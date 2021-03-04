package lemmings;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Commands {	
	private LemmingsView view;
	private JFrame frame;
	public Commands(LemmingsView view, JFrame frame) {
		this.view = view;
		this.frame = frame;
	}	
	
	public void buttonView(JButton b, int x_deb, int y_deb, int x_fin, int y_fin) {
		b.setBounds(x_deb,y_deb,x_fin,y_fin);
		frame.getContentPane().add(b);
		view.add(b);
	}
	public void commands() {
		view.setLayout(null);
		JButton btnForeur = new JButton(new Foreur(view,"Foreur"));
		buttonView(btnForeur,355,610,95,30);
		
		JButton btnCharpenter = new JButton(new Charpenter(view,"Escalier"));
		buttonView(btnCharpenter,555,610,95,30);
		
		JButton btnBlock = new JButton(new Bloqueur(view,"Bloque"));
		buttonView(btnBlock,155,610,95,30);
		
		JButton btnGrimpeur = new JButton(new Climber(view,"Grimpe"));
		buttonView(btnGrimpeur,255,610,95,30);
		
		JButton btnParachute = new JButton(new Parachutiste(view,"Parachute"));
		buttonView(btnParachute,455,610,95,30);
		
		JButton btnTunnelier = new JButton(new Tunnelier(view,"Tunnel"));
		buttonView(btnTunnelier,655,610,95,30);
		
		JButton btnBombeur = new JButton(new Bombeur(view,"Bombe"));
		buttonView(btnBombeur,755,610,95,30);
//		frame.getContentPane().add(btnForeur);
//		view.add(btnForeur);
	}


}
