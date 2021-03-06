package ai;

import java.util.List;
import java.util.Random;

import gameDefs.Board;
import gameDefs.Player;

public class RandomAI implements Player{

	
	@Override
	public int getMove(Board b, int color) {
		List<Integer> possibleMoves = b.getLegalMoves();
		
		Random rand = new Random();
		int col = rand.nextInt(possibleMoves.size());
		return possibleMoves.get(col);
	}

	@Override
	public String getName() {
		return "Random AI Player";
	}
	
	@Override
	public void gameOver(int status) {
		return;
	}
}
