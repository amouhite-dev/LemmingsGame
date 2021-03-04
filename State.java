package lemmings;

public interface State {
	abstract void changeStateto(Command cmd);
	abstract void action();
}
