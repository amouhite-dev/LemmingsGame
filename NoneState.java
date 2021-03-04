package lemmings;

public class NoneState implements State {
	LemmingsGame game;
	Lemmings l;
	public NoneState(Lemmings l,LemmingsGame game) {
		this.l = l;
		this.game =game;
	}
	
	@Override
	public void changeStateto(Command newCmd) {
		switch (newCmd) {
		case PARACHUTIST:
			l.changeStateto(new Parachutiste(l,Command.PARACHUTIST,game));
			l.setCmd(newCmd);
			System.out.println("None to Parachute");
			break;
		case BLOCK:
			l.changeStateto(new Bloqueur(l,Command.BLOCK,game));
			l.setCmd(newCmd);
			System.out.println("None to Block");
			break;
		case TUNNEL:
			Tunnelier tunnel = new Tunnelier(l,Command.TUNNEL,game);
			l.changeStateto(tunnel);
			l.setCmd(newCmd);
			System.out.println("None to Tunnel");
			break;
		case BOMBE:
			l.changeStateto(new Bombeur(l,Command.BOMBE,game));
			l.setCmd(newCmd);
			System.out.println("None to BOMBE");
			break;
		case CHARPENTER:
			l.changeStateto(new Charpenter(l,Command.CHARPENTER,game));
			l.setCmd(newCmd);
			System.out.println("None to CHARPENTER");
			break;
		case CLIMBER:
			l.changeStateto(new Climber(l,Command.CLIMBER,game));
			l.setCmd(newCmd);
			System.out.println("None to CLIMBER");
			break;
		case FOREUR:
			l.changeStateto(new Foreur(l,Command.FOREUR,game));
			l.setCmd(newCmd);
			System.out.println("NONE to FOREUR");
			break;
		default:
			break;
		}
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
}
