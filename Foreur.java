package lemmings;

import java.awt.event.ActionEvent;


import javax.swing.AbstractAction;

import lemmings.Lemmings.Direction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Foreur extends AbstractAction implements State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7673033281457390972L;
	private LemmingsGame game;
	private Lemmings l;
	
	Command cmd = Command.FOREUR;
	private LemmingsView v;
	@SuppressWarnings("unused")
	private String btnText;
	
	public Foreur(LemmingsView v , String btnText) {
		super(btnText);
		this.v = v;
		this.btnText = btnText;
	}
	
	public Foreur(Lemmings l,Command cmd,LemmingsGame game) {
		this.game = game;
		this.l = l;
		l.setCmd(cmd);
		l.changeStateto(this);
	}
	
	//Changed state to another state
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
			case PARACHUTIST:
				l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to Parachute");
				break;
			case BLOCK:
				l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to Block");
				break;
			case TUNNEL:
				l.changeStateto(new Tunnelier(l,Command.TUNNEL,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to Tunnel");
				break;
			case BOMBE:
				l.changeStateto(new Bombeur(l,Command.BOMBE,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to BOMBE");
				break;
			case CHARPENTER:
				l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to CHARPENTER");
				break;
			case CLIMBER:
				l.changeStateto(new Climber(l,Command.CLIMBER,game));
				l.setCmd(newCmd);
				System.out.println("Foreur to CLIMBER");
				break;
			case NONE:
				l.setCmd(newCmd);
				l.changeStateto(new NoneState(l, game));
				System.out.println("Foreur to NONE");
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
			Lemmings.btnClicked = "Foreur";
			
		}
	});
	
}

@Override
public void action() {
	if(l.getCmd() == Command.FOREUR) {
		if(l.isJePeux() == false) {
			l.setDir(l.getPrecDirection());
			l.setPrecDirection(l.getDir());
			l.setDir(Direction.NULL);
		}
		if(l.getNbParsForeur() == 0) {
			if(Lemmings.nb<=5) { l.setJePeux(true);
			}else {
				l.setJePeux(false);
				l.setJePeux(false);
				l.setCmd(Command.NONE);
				Lemmings.setBtnClicked("None");
				l.setDir(l.getPrecDirection());
			}
		}
		if(l.isJePeux() == true ) {
			l.setDir(Direction.NULL);
			if(game.getMap().getTab()[l.getPosx()+1][l.getPosy()] == 4) {
				int xobs = l.getPosx()+1; int yobs = l.getPosy();
				int a = xobs; int b = yobs-1;
				game.getMap().setTab(xobs,yobs,0);
				for (int i = xobs; i > a-2; i--) {
					for (int j = yobs; j < b+2; j++) {
						for (LemmingsObservable l : game.getLemmings()) {
							if(l.getPosx() == i && l.getPosy() == j) {
								l.setDead(true);
								l.setDir(Direction.NULL);}
						}
					}
				}
				
			}else if(game.getMap().getTab()[l.getPosx()+1][l.getPosy()] == 5) {
				Random rand = new Random();
				int x = rand.nextInt(5);
				//int y = rand.nextInt(5);

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
			if(game.getMap().getTab()[l.getPosx()+1][l.getPosy()] == 1 ||
					game.getMap().getTab()[this.l.getPosx()+1][l.getPosy()] == 4 ||
					game.getMap().getTab()[this.l.getPosx()+1][l.getPosy()] == 5){
				if(l.getNbParsForeur() < 5) {
					game.getMap().getTab()[l.getPosx()+1][l.getPosy()] = 0;
					l.setPosx(l.getPosx()+1);
					l.setNbParsForeur(l.getNbParsForeur() +1);
					l.setNbParsAvantMort(0);
				} else {
					l.setNbParsForeur(0);
					Lemmings.nb=0;
					l.setCmd(Command.NONE);
					Lemmings.setBtnClicked("None");
					l.setDir(l.getPrecDirection());
				}
			}else {
				l.setJePeux(false);
				l.setCmd(Command.NONE);
				l.changeStateto(new NoneState(l,game));
				Lemmings.setBtnClicked("None");				
				l.setDir(Direction.DOWN);
			}
		}else {
			l.setCmd(Command.NONE);
			Lemmings.setBtnClicked("None");
			l.setDir(l.getPrecDirection());
		}
	}
	
}	
}
