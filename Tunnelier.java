package lemmings;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

public class Tunnelier extends AbstractAction  implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2704681208757626614L;
	LemmingsGame game;
	Lemmings l;
	Command cmd = Command.TUNNEL;
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	public Tunnelier(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	public Tunnelier(Lemmings l,Command cmd,LemmingsGame game) {
		this.game = game;
		this.l = l;
		l.setCmd(Command.TUNNEL);
		l.changeStateto(this);
	}
	
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
		case PARACHUTIST:
			l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to Parachute");
			break;
		case BLOCK:
			l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to Block");
			break;
		case BOMBE:
			l.changeStateto(new Bombeur(l,Command.BOMBE,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to BOMBE");
			break;
		case CHARPENTER:
			l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to CHARPENTER");
			break;
		case CLIMBER:
			l.changeStateto(new Climber(l,Command.CLIMBER,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to CLIMBER");
			break;
		case FOREUR:
			l.changeStateto(new Foreur(l,Command.FOREUR,game));
			l.setCmd(newCmd);
			System.out.println("Tunnelier to FOREUR");
			break;
		case NONE:
			l.setCmd(newCmd);
			l.changeStateto(new NoneState(l, game));
			System.out.println("Tunnelier to NONE");
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
				Lemmings.setBtnClicked("Tunnel");
			}
		});
	}

	@Override
	public void action() {
		if(l.getCmd() == Command.TUNNEL) {
			if(l.isJePeux() == false) {
				l.setPrecDirection(l.getDir());
				l.setJePeux(true);
			}
			l.setDir(Direction.NULL);
			if(l.getPrecDirection() == Direction.RIGHT) {
				if(game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 4) {
					int xobs = l.getPosx(); int yobs = l.getPosy()+1;
					int a = xobs; int b = yobs-1;
					game.getMap().setTab(xobs,yobs,0);
					for (int i = xobs; i > a-2; i--) {
						for (int j = yobs; j < b+2; j++) {
							for (LemmingsObservable l : game.getLemmings()) {
								if(l.getPosx() == i && l.getPosy()+1 == j) {
									l.setDead(true);l.setDir(Direction.NULL);
								}
							}
						}
					}
					
				} else if(game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 5) {
					Random rand = new Random();
					int x = rand.nextInt(5);
					int a = l.getTabApparitionObsX()[x];
					int b = l.getTabApparitionObsY()[x];
					if(x == 0){
						game.getMap().getTab()[a][b] = 1;
					} else if(x == 1) {
						game.getMap().getTab()[a][b] = 1;
						game.getMap().getTab()[a-1][b] = 1;
					} else if(x == 2) {
						game.getMap().getTab()[a][b] = 4;
					} else if(x == 3) {
						game.getMap().getTab()[a][b] = 5;
					} else {
						game.getMap().getTab()[a][b] = 1;
						game.getMap().getTab()[a-1][b] = 1;
					}
				}
				if(game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 1 ||
					game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 4 ||
					game.getMap().getTab()[l.getPosx()][l.getPosy()+1] == 5) {
					game.getMap().getTab()[l.getPosx()][l.getPosy()+1] = 0;
					l.setPosy(l.getPosy()+1);
				}else {
					l.setJePeux(false);
					l.setCmd(Command.NONE); 
					Lemmings.setBtnClicked("None");
			   		l.setDir(l.getPrecDirection());
				}
			}else if(l.getPrecDirection() == Direction.LEFT) {
				if(game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 4) {
					int xobs = l.getPosx(); int yobs = l.getPosy()-1;
					int a = xobs; int b = yobs-1;
					game.getMap().setTab(xobs,yobs,0);
					for (int i = xobs; i > a-2; i--) {
						for (int j = yobs; j < b+2; j++) {
							for (LemmingsObservable l : game.getLemmings()) {
								if(l.getPosx() == i && l.getPosy()-1 == j) {
									l.setDead(true);
								}
							}
						}
					}
				} else if(game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 5) {
					Random rand = new Random();
					int x = rand.nextInt(5);
					int a = l.getTabApparitionObsX()[x];
					int b = l.getTabApparitionObsY()[x];
					if(x == 0){
						game.getMap().getTab()[a][b] = 1;
					} else if(x == 1) {
						game.getMap().getTab()[a][b] = 1;
						game.getMap().getTab()[a-1][b] = 1;
					} else if(x == 2) {
						game.getMap().getTab()[a][b] = 4;
					} else if(x == 3) {
						game.getMap().getTab()[a][b] = 5;
					} else {
						game.getMap().getTab()[a][b] = 1;
						game.getMap().getTab()[a-1][b] = 1;
					}
				}

				if(game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 1 ||
					game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 4 ||
					game.getMap().getTab()[l.getPosx()][l.getPosy()-1] == 5) {
					game.getMap().getTab()[l.getPosx()][l.getPosy()-1] = 0;
					//l.setPosx(l.getPosx()-1);
				}else {
					l.setJePeux(false);
					l.setCmd(Command.NONE); 
					Lemmings.setBtnClicked("None");
			   		l.setDir(l.getPrecDirection());
				}
			}
		}
	}
}
