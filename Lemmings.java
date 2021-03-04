package lemmings;
import javax.swing.JPanel;

public class Lemmings extends JPanel implements ILemmings {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Direction {
		RIGHT, LEFT, DOWN, UP, NULL
	}
	
	private Direction dir;
	private int posx = 0, posy = 0;
	private int width;
	private int height;
	private LemmingsGame game;
	private boolean dead;
	private int idL;
	private State state;
	private Command cmd;
	private int point;
	private boolean exit;
	private int xLemBlock ,yLemBlock ;
	private int tabApparitionObsX [] = {5,7,13,16,20};
	private int tabApparitionObsY [] = {8,30,13,27,14};
	private int nbParsBombeur = 0, nbParsEscalier = 0;
	private int nbParsForeur = 0;
	public static int nb = 0;
	private int nbParsAvantMort = 0;
	private Direction precDirection = Direction.RIGHT;
	public static  String btnClicked = "None";
	
	public Lemmings(int pos_x, int pos_y, int idL,LemmingsGame game){
		this.posx = pos_x;
		this.posy = pos_y;
		this.game  = game;
		this.dir = Direction.NULL;
		this.width = this.height = 25;
		this.exit = false;
		this.dead = false;
		this.setIdL(idL);
		this.state = new NoneState(this,game);
		this.cmd = Command.NONE;
		this.setPoint(0);
	}
	
	@Override
	public Direction getPrecDirection() {	return precDirection;}
	@Override
	public void setPrecDirection(Direction precDirection) {	this.precDirection = precDirection;	}
	
	@Override
	public Command getCmd() {	return cmd;	}
	@Override
	public void setCmd(Command cmd) {this.cmd = cmd;}
	
	@Override
	public State getState() {	return state;}
	@Override
	public void changeStateto(State newState) {	this.state = newState;	}
	
	@Override
	public int getIdL() { return idL;	}
	@Override
	public void setIdL(int idL) { this.idL = idL;	}

	@Override
	public Direction getDir() {	return dir;	}
	@Override
	public void setDir(Direction dir) {	this.dir = dir;	}

	@Override
	public int getPosx() {	return posx;	}
	@Override
	public void setPosx(int pos_x) { this.posx = pos_x;	}
	
	@Override
	public int getPosy() { return posy;	}
	@Override
	public void setPosy(int pos_y) { this.posy = pos_y;	}
	
	@Override
	public int getWidth() {	return width; }
	@Override
	public void setWidth(int width) { this.width = width; }
	
	@Override
	public int getHeight() { return height; }
	@Override
	public void setHeight(int height) {	this.height = height; }
	
	@Override
	public int getPoint() {	return point; }
	@Override
	public void setPoint(int point) { this.point = point; }
	
	public static String getBtnClicked() {	return btnClicked;	}
	public static void setBtnClicked(String btnClicked) {Lemmings.btnClicked = btnClicked;}
	
	public int getNbParsAvantMort() {return nbParsAvantMort;}
	public void setNbParsAvantMort(int nbParsAvantMort) {this.nbParsAvantMort = nbParsAvantMort;}
	
	private boolean jePeux = false, mortCertaine = false,jePeuxGrimper = false;
	
	public int getNbParsBombeur() {	return nbParsBombeur;}
	public void setNbParsBombeur(int nbParsBombeur) {this.nbParsBombeur = nbParsBombeur;}
	
	public int getNbParsEscalier() {return nbParsEscalier;}
	public void setNbParsEscalier(int nbParsEscalier) { this.nbParsEscalier = nbParsEscalier;}
	
	public int getNbParsForeur() { return nbParsForeur;}
	public void setNbParsForeur(int nbParsForeur) { this.nbParsForeur = nbParsForeur;}
	
	public boolean isJePeux() { return jePeux;	}
	public void setJePeux(boolean jePeux) { this.jePeux = jePeux; }
	
	public boolean isMortCertaine() {return mortCertaine;}
	public void setMortCertaine(boolean mortCertaine) { this.mortCertaine = mortCertaine; }
	
	public boolean isJePeuxGrimper() {return jePeuxGrimper;}
	public void setJePeuxGrimper(boolean jePeuxGrimper) { this.jePeuxGrimper = jePeuxGrimper;}
	
	public  int[] getTabApparitionObsX() {	return tabApparitionObsX;}
	public void setTabApparitionObsX(int[] tabApparitionObsX) {	this.tabApparitionObsX = tabApparitionObsX;	}
	
	public int[] getTabApparitionObsY() {return tabApparitionObsY;}
	public void setTabApparitionObsY(int[] tabApparitionObsY) { this.tabApparitionObsY = tabApparitionObsY;}

	public int getxLemBlock() {	return xLemBlock;}
	public void setxLemBlock(int xLemBlock) {this.xLemBlock = xLemBlock;}
	
	public int getyLemBlock() {	return yLemBlock;}
	public void setyLemBlock(int yLemBlock) {	this.yLemBlock = yLemBlock;	}
	
	@Override
	public boolean isDead() { return dead; }
	@Override
	public void setDead(boolean dead) {
        this.dead = dead;
        if(dead == true) {
            game.setNbLem(game.getNbLem()-1);
            setDir(Direction.NULL);
        }
    }
	
	@Override
	public boolean isExit() { return exit; }
	@Override
    public void setExit(boolean exit) {
        this.exit = exit;
        if(this.exit == true) {
            setPoint(10);
            setDir(Direction.NULL);
        }else {
            this.posy --;
        }
    }
   
	@Override
    public void enter() {
        if(this.posx < 5) {
            this.posx ++ ;
        } else {
            this.dir = Direction.RIGHT;
            this.posy ++;
        }
    }
	
	@Override
	public void move() {
		if(getCmd() != Command.NONE) state.action();
		
		if(this.getPosx() == 20 && this.getPosy()==5) {
            setExit(true);
        }
		switch(this.dir) {
			case UP :
				refreshDirection();
				break;
			case DOWN :
				if(game.getMap().getTab()[posx+1][posy] != 2) {
					if(game.getMap().getTab()[posx+1][posy] == 0 ) {
						if(getCmd() != Command.PARACHUTIST) {
							this.posx ++;
							setNbParsAvantMort(getNbParsAvantMort()+1);
							if(this.getNbParsAvantMort() == 6) {
								setMortCertaine(true);
							}
							if(game.getMap().getTab()[posx+1][posy] == 1 && mortCertaine == true) {
								setDead(true);
							}
						}else {
							if(game.getMap().getTab()[posx+1][posy] == 0 ) {
								this.posx ++;	
								setNbParsAvantMort(0);
								setMortCertaine(false);
							}else {
								setNbParsAvantMort(0);
								setMortCertaine(false);
								refreshDirection();
							}
						}
					} else {
						if(getCmd() !=  Command.NONE ) {
							setCmd(Command.NONE); 
							btnClicked = "None";
							changeStateto(new NoneState(this, game));
							setNbParsAvantMort(0);
						} 
						setNbParsAvantMort(0);
						setMortCertaine(false);
						System.out.println("ici 436"+this.dir + "  "+this.precDirection);
						setDir(getPrecDirection());
						System.out.println("554"+this.precDirection);
						refreshDirection();			
					}
				} else {
					this.setDead(true);
				}				
				break;
			case LEFT :
				if (posx == 13 && posy == 1) {
					setPosx(7);
					setPosy(33);
					break;
				}
				if(posy != 0) {
					if(game.getMap().getTab()[posx][posy-1] !=10){
						if(game.getMap().getTab()[posx+1][posy-1] == 1){
							if((game.getMap().getTab()[posx][posy-1] == 1 && game.getMap().getTab()[posx-1][posy-1] == 0)) {											
								if(game.getMap().getTab()[posx-1][posy] == 1 ) {
									this.setPosx(getPosx() -1);
									this.setPosy(getPosy() -1);
								}else {
									setPrecDirection(getDir());
									setDir(Direction.UP);
									setPosx(getPosx()-1);
								}
							} else if((game.getMap().getTab()[posx][posy-1] == 4) ||(game.getMap().getTab()[posx][posy-1] == 5) ||
									(game.getMap().getTab()[posx][posy-1] == 1 && game.getMap().getTab()[posx-1][posy-1] == 1)) {							
								if(btnClicked == "Climber") { 
									state.action();
									changeStateto(new Climber(this,Command.CLIMBER,game));
								}else {
									System.out.println("465 - N°"+getIdL()+" -dir"+getDir());
									setPrecDirection(this.dir);
									this.dir = Direction.RIGHT;
								}
							}else {
								this.posy--;										
							}
						} else {
							if(game.getMap().getTab()[posx][posy-1] == 0) {
								if(getCmd() == Command.CHARPENTER) {
									game.getMap().setTab(getPosx(), getPosy()-1, 1);
									setDir(Direction.UP);
								}else {
									setPrecDirection(this.getDir());
									setNbParsAvantMort(0);
									this.dir = Direction.DOWN;
									this.posy--;
								}
							} else if((game.getMap().getTab()[posx][posy-1] == 1 && game.getMap().getTab()[posx+1][posy] == 1)){
								setPrecDirection(this.getDir());
								this.dir = Direction.UP;
								this.posx --;
							}
						}
					}else {					
						setPrecDirection(this.getDir());
						setDir(Direction.RIGHT);
						this.posy ++;
					}
				}else {
					setPrecDirection(this.getDir());
					setDir(Direction.RIGHT);
					this.posy ++;
				}
				break;
			case RIGHT :
				if(posx == 7 && posy == 33) {
					setPosx(13);
					setPosy(1);
					break;
				}
				if((posy) != (game.getMap().getY()-1)) {	
					if(game.getMap().getTab()[posx][posy+1] !=10){						
						if(game.getMap().getTab()[posx+1][posy+1] == 1){
							if((game.getMap().getTab()[posx][posy+1] == 4) ||(game.getMap().getTab()[posx][posy+1] == 5) ||
									game.getMap().getTab()[posx][posy+1] == 1 && game.getMap().getTab()[posx-1][posy+1] == 1){
								if(btnClicked == "Climber") {
									state.action();
									changeStateto(new Climber(this,Command.CLIMBER,game));
								}else {
									setPrecDirection(this.dir);
									this.dir = Direction.LEFT;
								}
							}else {
								int x = -1;
								if(getPosx()-1 < 0) { x =0;}else {x = getPosx()-1;}
								if(game.getMap().getTab()[x][posy] == 0 && game.getMap().getTab()[posx][posy+1] == 1) {
									setPosx(getPosx()-1);
								}else if(game.getMap().getTab()[x][posy] == 1 && game.getMap().getTab()[posx][posy+1] == 1) {
									setPosx(getPosx()-1);
									setPosy(getPosy()+1);
								}else {
									this.precDirection = this.dir;
									this.dir = Direction.UP;
								}
							}
						} else {
							if(game.getMap().getTab()[posx][posy+1] == 0) {
								if(getCmd() == Command.CHARPENTER) {
									game.getMap().setTab(getPosx(), getPosy(), 1);
									setPrecDirection(this.getDir());
									setDir(Direction.UP);
								}else {
									setNbParsAvantMort(0);
									setPrecDirection(this.getDir());
									this.dir = Direction.DOWN;
									this.posy++;
								}
							} else if(game.getMap().getTab()[posx][posy+1] == 1 && game.getMap().getTab()[posx+1][posy] == 1){
								setPrecDirection(this.getDir());
								this.dir = Direction.UP;
							}
						}
					}else {
						setPrecDirection(this.getDir());
						setDir(Direction.LEFT);
						this.posy --;
					}
				}else {
					setPrecDirection(this.getDir());
					setDir(Direction.LEFT);
					this.posy --;
				}
				break;
			default: break;
		}
	}
	
	private void refreshDirection() {
		int cas_rarely = 0;
		if(precDirection==Direction.RIGHT) {
			if(game.getMap().getTab()[posx][posy+1] == 1 && 
				game.getMap().getTab()[posx-1][posy+1] == 0) {
				this.posx --;
				this.dir = Direction.DOWN;
				setNbParsAvantMort(0);
			} else if(game.getMap().getTab()[posx][posy+1] == 1 && 
				game.getMap().getTab()[posx-1][posy+1] == 1 && game.getMap().getTab()[posx][posy-1] == 0) {
				this.dir = Direction.LEFT;
				this.posy --;
				setNbParsAvantMort(0);
			} else if(game.getMap().getTab()[posx+1][posy] == 1 &&
				game.getMap().getTab()[posx+1][posy+1] == 0 &&
				game.getMap().getTab()[posx+1][posy+1] == 0){
				cas_rarely = (posy -2)+1;
				this.posy = cas_rarely +1;
				setNbParsAvantMort(0);
			} else {				
				this.posy ++;
				setNbParsAvantMort(0);
			}
		}else if(precDirection == Direction.LEFT) {
			if(game.getMap().getTab()[posx][posy-1] == 1) {
				if(game.getMap().getTab()[posx-1][posy-1] == 0) {
					this.posx --;
					this.dir = Direction.DOWN;
				}else{
					this.dir = Direction.RIGHT;
					this.posy ++;
				}
			} else if(game.getMap().getTab()[posx+1][posy] == 1 &&
					game.getMap().getTab()[posx+1][posy-1] == 0 &&
					game.getMap().getTab()[posx][posy-1] == 0){
				cas_rarely = (posy +2)-1;
				this.posy = cas_rarely -1;
			} else {
				this.posy --;
			}
		} 
		this.dir = precDirection;
	}	
	
	@Override
	public boolean MakeState(int posX,int posY) {
		int length = game.getMap().getTab().length;
		if (posX < 0 || posX >= length || posY < 0 || posY >= length) return false;
		verifyState();
		state.changeStateto(getCmd());
		state.action();
		return true;
	}
	
	private int ecart5(int tab[][],int x, int y) {
		int i = x + 1; int j= 0;
			while(tab[i][y] == 1) {
				i++;j++;
			}
		return j;
	}
	
	@Override
	public void verifyState() {
		switch(btnClicked) {
		case "Foreur":
			setCmd(Command.FOREUR);
			nb = (ecart5(game.getMap().getTab(),getPosx(),getPosy()));
			break;
		case "Climber":
			setCmd(Command.CLIMBER);
			break;
		case "Charpenter":
			setCmd(Command.CHARPENTER);
			break;
		case "Tunnel":
			setCmd(Command.TUNNEL);
			System.out.println("toto");
			break;
		case "Parachutist":
			setCmd(Command.PARACHUTIST);
			break;
		case "Bombe":
			setCmd(Command.BOMBE);
			break;
		case "Block":
			setCmd(Command.BLOCK);
			break;
		case "None":
			setCmd(Command.NONE);
			break;
		default:
			break;	
		}
	}
}