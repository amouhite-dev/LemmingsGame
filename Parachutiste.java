package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

public class Parachutiste extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3145836557292034947L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.PARACHUTIST;
	
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Parachutiste(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	public Parachutiste(Lemmings l, Command  cmd, LemmingsGame game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.l = l;
		l.setCmd(cmd);
		l.changeStateto(this);
		//doIt(l);
	}
		
	/*
	 * Changed state to another state
	 */
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
		case BLOCK:
			l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to Block");
			break;
		case TUNNEL:
			l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to Tunnel");
			break;
		case BOMBE:
			l.changeStateto(new Bombeur(l,Command.BOMBE,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to BOMBE");
			break;
		case CHARPENTER:
			l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to CHARPENTER");
			break;
		case CLIMBER:
			l.changeStateto(new Climber(l,Command.CLIMBER,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to CLIMBER");
			break;
		case FOREUR:
			l.changeStateto(new Foreur(l,Command.FOREUR,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to Foreur");
			break;
		case NONE:
			l.changeStateto(new NoneState(l,game));
			l.setCmd(newCmd);
			System.out.println("Parachutist to NONE");
			break;
		default: 
			break;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		v.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Lemmings.btnClicked = "Parachutist";
				
			}
		});
		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
}
