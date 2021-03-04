package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

public class Bombeur extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7693852621142911230L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.BOMBE;
	
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Bombeur(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	public Bombeur(Lemmings l, Command cmd, LemmingsGame game) {
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
				System.out.println("Bombeur to Parachute");
				break;
			case BLOCK:
				l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
				l.setCmd(newCmd);
				System.out.println("Bombeur to block");
				break;
			case TUNNEL:
				l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
				l.setCmd(newCmd);
				System.out.println("Bombeur to Tunnel");
				break;
			case FOREUR:
				l.changeStateto(new Foreur(l,Command.FOREUR,game));
				l.setCmd(newCmd);
				System.out.println("Bombeur to BOMBE");
				break;
			case CHARPENTER:
				l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
				l.setCmd(newCmd);
				System.out.println("Bombeur to CHARPENTER");
				break;
			case CLIMBER:
				l.changeStateto(new Climber(l,Command.CLIMBER,game));
				l.setCmd(newCmd);
				System.out.println("Bombeur to CLIMBER");
				break;
			case NONE:
				l.setCmd(newCmd);
				l.changeStateto(new NoneState(l, game));
				System.out.println("Bombeur to NONE");
				break;
			default:
				break;
			}
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		v.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Lemmings.btnClicked = "Bombe";
				
			}
		});
		
	}

	@Override
	public void action() {
		if(l.getNbParsBombeur() == 3) {
			l.setPrecDirection(l.getDir());
			l.setDir(Direction.NULL);
			for(int i = 2; i< 4; i ++){
				game.getMap().setTab(l.getPosx(),l.getPosy()+i,0);
				game.getMap().setTab(l.getPosx(),l.getPosy()-i,0);
				game.getMap().setTab(l.getPosx()-(i-1),l.getPosy(),0);
				game.getMap().setTab(l.getPosx()+i,l.getPosy(),0);
				
				game.getMap().setTab(l.getPosx()-(i-1),l.getPosy()+1,0);
				game.getMap().setTab(l.getPosx()-(i-1),l.getPosy()-1,0);
				game.getMap().setTab(l.getPosx()+i,l.getPosy()-1,0);
				game.getMap().setTab(l.getPosx()+i,l.getPosy()+1,0);
			}
			//l.setDead(true);
			
			l.setCmd(Command.NONE);
			Lemmings.btnClicked = "None";
			l.setNbParsBombeur(0);
			l.setDir(l.getPrecDirection());
		} else {
			l.setNbParsBombeur(l.getNbParsBombeur()+1);
		}
	}
		
}

