package lemmings;

import java.util.ArrayList;

public class LemmingsGame {
	private LemmingsMap map;
	
	private ArrayList <LemmingsObservable> lemmings;
	
	private int nbLem;//pour le nombre de lemmings a l'affichage et la taille de ma ArrayList
	public boolean enter = true;
	public int nbLemmings = 0; // pour l'identifiant
	
	public LemmingsGame() {
		this.map = new LemmingsMap();
	    nbLemmings ++;
	    this.lemmings = new ArrayList<LemmingsObservable>();
	    this.lemmings.add(new LemmingsObservable(2, 2, nbLemmings, this));
	    this.nbLem = lemmings.size();
	}
	
	public int getNbLem() {
        return nbLem;
    }
    public void setNbLem(int nbLem) {
        this.nbLem = nbLem;
    }
    
	public int score()	{
		int score = 0;
		for(int i =0;i < lemmings.size();i++) {
			score += lemmings.get(i).getPoint();
		}
		return score;
	}
	
	
	public ArrayList<LemmingsObservable> getLemmings() {
		return lemmings;
	}
	
	public LemmingsMap getMap() {
		return map;
	}
		
	public void moveInit(int i) {
		if(lemmings.get(lemmings.size()-1).getPosx() < 5) {
			lemmings.get(lemmings.size()-1).enter();
		}else {
			lemmings.get(lemmings.size()-1).enter();
		    this.nbLem = lemmings.size();
			enter = false;
			lemmings.get(lemmings.size()-1).move();
		}
	}

	public void step() {
		for (LemmingsObservable l : lemmings) {
			l.move();
		}
	}
	
	public int getLem(int Lx,int Ly,int Sx,int Sy) {
		for (int i = 0; i < lemmings.size(); i++) {
			if(Lx == Sx && Ly == Sy) return lemmings.get(i).getIdL();
		}
		return -1;
	}
}
