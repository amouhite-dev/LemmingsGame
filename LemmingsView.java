package lemmings;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lemmings.Lemmings.Direction;

public class LemmingsView extends JPanel {

	private static final long serialVersionUID = 1L;
	private final LemmingsGame game;
	private Commands command;
	public final static int TAILLE = 889;
	public final static int DIM = 35;
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if(e.getY()/(TAILLE/DIM) == 2 && e.getX()/(TAILLE/DIM) == 2) {
				game.enter = true;
				game.getLemmings().add(new LemmingsObservable(2, 2, game.nbLemmings, game));		
			}else {
				for (LemmingsObservable l : game.getLemmings()) {
					if(l.getxLemBlock() == e.getY()/(TAILLE/DIM) && l.getyLemBlock() == e.getX()/(TAILLE/DIM)) {
						 l.setCmd(Command.NONE);
						 l.setDir(l.getPrecDirection());
						 game.getMap().getTab()[l.getxLemBlock()][l.getyLemBlock()] = 0;
					 }else {
						 if((position(e.getX(), TAILLE) ==  l.getPosy()) && (position(e.getY(), TAILLE) ==  l.getPosx())) {
							 l.MakeState(position(e.getY(), 875), position(e.getX(), 875));
						 }
					 }
				 }
			}			 
		}
	};
	
	public LemmingsView(LemmingsGame game, JFrame frame) {
		super();
		this.game = game;
		this.command = new Commands(this, frame);
		addMouseListener(mouseAdapter);
	}
	
	public LemmingsGame getGame() {
		return game;
	}
	private Graphics graphic;
	public Graphics getGraphic() {
		return this.graphic;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.GRAY);
		drawMap(g);
		drawLemmings(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString("Nb Lemmings: " + game.getNbLem(), 10, 620);   
        g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString("Score: " + game.score(), 10, 640);	
		
	}
	public void drawLemmings(Graphics g) {
		for (int i = 0; i < game.getLemmings().size(); i++) {
			if(game.getLemmings().get(i).isDead() == false && game.getLemmings().get(i).getCmd() == Command.NONE && game.getLemmings().get(i).isExit() == false) {
					draw(g,Color.BLUE,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.FOREUR && game.getLemmings().get(i).getDir() == Direction.NULL){
					draw(g,Color.MAGENTA,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.PARACHUTIST && game.getLemmings().get(i).getDir() != Direction.NULL ){
					draw(g,Color.GREEN,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.CLIMBER && game.getLemmings().get(i).getDir() == Direction.NULL ){
					draw(g,Color.DARK_GRAY,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.TUNNEL && game.getLemmings().get(i).getDir() == Direction.NULL ){
					draw(g,Color.CYAN,i); 
			}else
				if(game.getLemmings().get(i).getCmd() == Command.CHARPENTER && game.getLemmings().get(i).getDir() != Direction.NULL){
					draw(g,Color.PINK,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.BOMBE && game.getLemmings().get(i).getDir() != Direction.NULL){
					draw(g,Color.RED,i);
			}else
				if(game.getLemmings().get(i).getCmd() == Command.BLOCK && game.getLemmings().get(i).getDir() == Direction.NULL){
					draw(g,Color.YELLOW,i);
			}				
		}
	}
	public void drawMap(Graphics g) {
		LemmingsMap map = game.getMap();
		for(int i=0;i < map.getX();i++) {
			for(int j = 0;j < map.getY();j++) {
				if(map.getTab()[i][j] == 1 ){// obstacle simple ou insurmontable
					g.setColor(Color.BLACK);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 5) {//obstacle ressurection
					g.setColor(Color.WHITE);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 2) {//magma
					g.setColor(Color.RED);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 6) {//exit
					g.setColor(Color.GREEN);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 3) {//téléporteur
					g.setColor(Color.BLUE);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 4) {//bombe
					g.setColor(Color.ORANGE);
					g.fillRect((j*25),(i*25), 25, 25);
				}else if(map.getTab()[i][j] == 7) {//entrée
					g.setColor(Color.PINK);
					g.fillRect((j*25),(i*25), 25, 25);
				}
			}
		}
		command.commands();
	}

	public void draw(Graphics g,Color c, int idl) {
		g.setColor(c);
		g.fillRect(game.getLemmings().get(idl).getPosy()*25, game.getLemmings().get(idl).getPosx()*25, 22, 22);
	}	
		
	private int position(int pos, int size) {
		return pos / (size / 35);
	}
}
