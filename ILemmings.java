package lemmings;

import lemmings.Lemmings.Direction;

public interface ILemmings {	
	
	Direction getPrecDirection();
	void setPrecDirection(Direction precDirection);
	
	boolean isExit();
	void setExit(boolean exit);
	
	Command getCmd();
	void setCmd(Command cmd);
	
	 State getState();
	 void changeStateto(State newState);
	 
	 int getIdL();
	 void setIdL(int idL);

	 Direction getDir();
	 void setDir(Direction dir);

	 int getPosx();
	 void setPosx(int pos_x);
		
	 int getPosy();
	 void setPosy(int pos_y);
		
	 int getWidth() ;
	 void setWidth(int width);
		
	 int getHeight();
	 void setHeight(int height);
		
	 int getPoint();
	 void setPoint(int point);
		
	 int getxLemBlock();
	 void setxLemBlock(int xLemBlock) ;
	 
	 int getyLemBlock();
	 void setyLemBlock(int yLemBlock);
	 
	 boolean isDead();
	 void setDead(boolean dead);
	
	 void enter();
	 
	 void move();
	 
	 boolean MakeState(int posX,int posY);
	 
	 void verifyState();
}
