package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

public class Bloqueur extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2009791992631869384L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.BLOCK;
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Bloqueur(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	public Bloqueur(Lemmings l, Command cmd, LemmingsGame game) {
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
			case PARACHUTIST:
				l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to Parachute");
				break;
			case BOMBE:
				l.changeStateto(new Bombeur(l,Command.BOMBE,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to bombe");
				break;
			case TUNNEL:
				l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to Tunnel");
				break;
			case FOREUR:
				l.changeStateto(new Foreur(l,Command.FOREUR,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to BOMBE");
				break;
			case CHARPENTER:
				l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to CHARPENTER");
				break;
			case CLIMBER:
				l.changeStateto(new Climber(l,Command.CLIMBER,game));
				l.setCmd(newCmd);
				System.out.println("Bloqueur to CLIMBER");
				break;
			case NONE:
				l.setCmd(newCmd);
				l.changeStateto(new NoneState(l, game));
				System.out.println("Bloqueur to NONE");
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
				Lemmings.btnClicked = "Block";
			}
		});
	}

	@Override
	public void action() {
		if (l.getCmd() == Command.BLOCK){
			if(l.getDir() == Direction.RIGHT || l.getDir() == Direction.LEFT) {
				l.setPrecDirection(l.getDir());
				l.setDir(Direction.NULL);
				game.getMap().getTab()[l.getPosx()][l.getPosy()] = 10;	
				l.setxLemBlock(l.getPosx());
				l.setyLemBlock(l.getPosy());				
			}
		}
	}
}
