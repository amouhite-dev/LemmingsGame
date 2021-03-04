package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

public class Charpenter extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8768297217085501269L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.CHARPENTER;
	
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Charpenter(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	Charpenter(Lemmings l,Command cmd,LemmingsGame game) {
		this.game = game;
		this.l = l;
		l.setCmd(cmd);
	}
	
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
		case PARACHUTIST:
			l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
			l.setCmd(newCmd);
			System.out.println("PARACHUTIST to Parachute");
			break;
		case BLOCK:
			l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
			l.setCmd(newCmd);
			System.out.println("PARACHUTIST to Block");
			break;
		case TUNNEL:
			l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
			l.setCmd(newCmd);
			System.out.println("PARACHUTIST to Tunnel");
			break;
		case BOMBE:
			l.changeStateto(new Bombeur(l,Command.BOMBE,game));
			l.setCmd(newCmd);
			System.out.println("PARACHUTIST to BOMBE");
			break;
		case CLIMBER:
			l.changeStateto(new Climber(l,Command.CLIMBER,game));
			l.setCmd(newCmd);
			System.out.println("PARACHUTIST to CLIMBER");
			break;
		case FOREUR:
			l.changeStateto(new Foreur(l,Command.FOREUR,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to FOREUR");
			break;
		case NONE:
			l.setCmd(newCmd);
			l.changeStateto(new NoneState(l, game));
			System.out.println("PARACHUTIST to NONE");
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
				Lemmings.btnClicked = "Charpenter";
				
			}
		});
		
	}

	@Override
	public void action() {
		if(l.getDir() != Direction.UP) {
			if(l.isJePeux() == false) {
				l.setPrecDirection(l.getDir());
				l.setDir(Direction.NULL);
				if((l.getPosx()-4) != 0) {
					for(int i = 1; i< 6; i ++) {
						if(l.getPrecDirection() == Direction.RIGHT) {
							if(game.getMap().getTab()[l.getPosx()-i][l.getPosy()+i] == 1 || game.getMap().getTab()[l.getPosx()-i][l.getPosy()+(i-1)] == 1) {
								l.setJePeux(false);
								l.setCmd(Command.NONE); 
								Lemmings.btnClicked = "None";
								l.setDir(l.getPrecDirection());
								break;
							}else {
								l.setJePeux(true);
							}
						} else if(l.getPrecDirection() == Direction.LEFT) {
							if(game.getMap().getTab()[l.getPosx()-i][l.getPosy()-i] == 1 || game.getMap().getTab()[l.getPosx()-i][l.getPosy()-(i-1)] == 1) {
								l.setJePeux(false);
								l.setCmd(Command.NONE); 
								Lemmings.btnClicked = "None";
								l.setDir(l.getPrecDirection());
								break;
							}else {
								l.setJePeux(true);
							}						
						}
					}
				}else {
					l.setJePeux(false);
					l.setCmd(Command.NONE); 
					Lemmings.btnClicked = "None";
					l.setDir(l.getPrecDirection());
				}
			} else {
				l.setDir(l.getPrecDirection());
				if((game.getMap().getTab()[l.getPosx()+1][l.getPosy()] == 1)) {
					if(l.getNbParsEscalier() < 5) {	
						if(l.getDir() == Direction.RIGHT) {
							if(game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 0) {
								game.getMap().setTab(l.getPosx(),l.getPosy()+1,1);
							}
							l.setDir(Direction.UP);
						}else if(l.getDir() == Direction.LEFT) {
							if(game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 0) {
								game.getMap().getTab()[l.getPosx()][l.getPosy()-1] = 1;
							} 
							l.setDir(Direction.UP);
						}
						l.setNbParsEscalier(l.getNbParsEscalier() +1);
					}
					if(l.getNbParsEscalier() == 5) {
						l.setCmd(Command.NONE);
						Lemmings.btnClicked = "None";
					}
				}
			}
		}
	}
}
