package Game;
public class mainTicTacToe {
	public static void main(String[] args) {
		GameTicTacToe k =new GameTicTacToe(" Game TicTacToe", 0, 0);
		if (Math.random() >= 0.5) {
			k.timer.start();
			k.icon = "O";
		}
		else
			k.icon = "X";
	}
}
