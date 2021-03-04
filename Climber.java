package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

public class Climber extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -604263188972848638L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.CLIMBER;
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Climber(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	public Climber(Lemmings l,Command cmd,LemmingsGame game) {
		this.game = game;
		this.l = l;
		l.setCmd(Command.CLIMBER);
		l.changeStateto(this);
	}
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
		case PARACHUTIST:
			l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
			l.setCmd(newCmd);
			System.out.println("Climber to Parachute");
			break;
		case BLOCK:
			l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
			l.setCmd(newCmd);
			System.out.println("Climber to Block");
			break;
		case TUNNEL:
			l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
			l.setCmd(newCmd);
			System.out.println("Climber to Tunnel");
			break;
		case BOMBE:
			l.changeStateto(new Bombeur(l,Command.BOMBE,game));
			l.setCmd(newCmd);
			System.out.println("Climber to BOMBE");
			break;
		case CHARPENTER:
			l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
			l.setCmd(newCmd);
			System.out.println("Climber to CHARPENTER");
			break;
		case FOREUR:
			l.changeStateto(new Parachutiste(l,Command.FOREUR,game));
			l.setCmd(newCmd);
			System.out.println("Climber to Foreur");
			break;
		case NONE:
			l.setCmd(newCmd);
			l.changeStateto(new NoneState(l, game));
			System.out.println("Climber to NONE");
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
				Lemmings.btnClicked = "Climber";
				
			}
		});
		
	}
	@Override
	public void action() {
		if(l.isJePeuxGrimper() == false) {				
			l.setPrecDirection(l.getDir());
			l.setDir(Direction.NULL);
			l.setJePeuxGrimper(true);
		}
		
		if(l.isJePeuxGrimper() == true) {
			if(l.getPosx()-1 != 0) {
				
				if(l.getPrecDirection() == Direction.RIGHT) {
					if(game.getMap().getTab()[l.getPosx()-1][l.getPosy()] == 0 &&(game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 1 || game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 5
							|| game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 4)) {
						l.setPosx(l.getPosx()-1);
					} else {
						l.setDir(l.getPrecDirection());
						l.setJePeuxGrimper(false);
						l.setCmd(Command.NONE);
						Lemmings.btnClicked = "None";
					}						
				} else if(l.getPrecDirection() == Direction.LEFT) {
					if(game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 1 || game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 5
							|| game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 4) {
						l.setPosx(l.getPosx()-1);
					} else {
						
						l.setDir(l.getPrecDirection());
						l.setJePeuxGrimper(false);
						l.setCmd(Command.NONE);
						Lemmings.btnClicked = "None";
					}
				}
				if(l.getPrecDirection() == Direction.UP) {
					l.setPrecDirection(Direction.RIGHT);
				}
			} else {
				
				l.setDir(l.getPrecDirection());
				l.setPrecDirection(l.getDir());
				l.setDir(Direction.DOWN);
				l.setJePeuxGrimper(false);
				l.setCmd(Command.NONE);
				Lemmings.btnClicked = "None";
			}
		}
	}
}
