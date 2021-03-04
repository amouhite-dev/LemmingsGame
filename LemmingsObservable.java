package lemmings;

import lemmings.Lemmings.Direction;
import java.util.ArrayList;
import java.util.List;

public class LemmingsObservable implements ILemmings {
	private Lemmings l;
	private List<LemmingsObserver> observers;
	
	public LemmingsObservable(int pos_x, int pos_y, int idL,LemmingsGame game) {
		l = new Lemmings(pos_x, pos_y, idL,game);
		observers = new ArrayList<>();
	}

	@Override
	public Direction getPrecDirection() {
		
		return l.getDir();
	}

	@Override
	public void setPrecDirection(Direction precDirection) {
		l.setPrecDirection(precDirection);
	}

	@Override
	public boolean isExit() {
		return l.isExit();
	}

	@Override
	public void setExit(boolean exit) {
		l.setExit(exit);
	}

	@Override
	public Command getCmd() {
		return l.getCmd();
	}

	@Override
	public void setCmd(Command cmd) {
		l.setCmd(cmd);
	}

	@Override
	public State getState() {
		return l.getState();
	}

	@Override
	public void changeStateto(State newState) {
		l.changeStateto(newState);
	}

	@Override
	public int getIdL() {
		return l.getIdL();
	}

	@Override
	public void setIdL(int idL) {
		l.setIdL(idL);		
	}

	@Override
	public Direction getDir() {
		return l.getDir();
	}

	@Override
	public void setDir(Direction dir) {
		l.setDir(dir);		
	}

	@Override
	public int getPosx() {
		return l.getPosx();
	}

	@Override
	public void setPosx(int pos_x) {
		l.setPosx(pos_x);		
	}

	@Override
	public int getPosy() {
		return l.getPosy();
	}

	@Override
	public void setPosy(int pos_y) {
		l.setPosy(pos_y);		
	}

	@Override
	public int getWidth() {
		return l.getWidth();
	}

	@Override
	public void setWidth(int width) {
		l.setWidth(width);		
	}

	@Override
	public int getHeight() {
		return l.getHeight();
	}

	@Override
	public void setHeight(int height) {
		l.setHeight(height);
	}

	@Override
	public int getxLemBlock() {
		return l.getxLemBlock();
	}

	@Override
	public void setxLemBlock(int xLemBlock) {
		l.setxLemBlock(xLemBlock);
		
	}

	@Override
	public int getyLemBlock() {
		return l.getyLemBlock();
	}

	@Override
	public void setyLemBlock(int yLemBlock) {
		l.setyLemBlock(yLemBlock);
	}
	 
	@Override
	public int getPoint() {
		return l.getPoint();
	}

	@Override
	public void setPoint(int point) {
		l.setPoint(point);
	}

	@Override
	public boolean isDead() {
		return l.isDead();
	}

	@Override
	public void setDead(boolean dead) {
		l.setDead(dead);		
	}

	@Override
	public void enter() {
			l.enter();	
	}

	@Override
	public boolean MakeState(int posX, int posY) {
		return l.MakeState(posX,posY);
	}

	@Override
	public void verifyState() {
		l.verifyState();
		
	}
	
	public void register(LemmingsObserver o) {
		observers.add(o);
	}
	
	public void unregister(LemmingsObserver o) {
		observers.remove(o);
	}
	
	void notifyObservers() {
		for(LemmingsObserver lem : observers) {
			lem.update();
		}
	}
	
	@Override
	public void move() {
		l.move();
		notifyObservers();
		
	}

}
