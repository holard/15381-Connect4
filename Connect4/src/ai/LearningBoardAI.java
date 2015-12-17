package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import gameDefs.Board;
import gameDefs.Player;

public class LearningBoardAI extends MinimaxAI implements Player{
	HashMap<Board, IntPair> states = new HashMap<Board, IntPair>();
	ArrayList<Board> gamePath = new ArrayList<Board>();
	int color;
	
	public LearningBoardAI(Heuristic h, int depth) {
		super(h, depth);
		color = -1;
	}
	
	public int getMove(Board b, int color) {
		this.color = color;
		
		List<Integer> moves = b.getLegalMoves();
		List<Integer> bestMoves = new ArrayList<Integer>();
		List<Double> learnedWeights = new ArrayList<Double>();
		for (int i = 0; i < 7; i++) {
			learnedWeights.add(0.0);
		}
		
		System.out.println("board hash code is " + Integer.toString(b.hashCode()));
		for (int i : moves) {
			Board ni = b.makeMove(i, color);
			
			if (states.containsKey(ni)) {
				IntPair winloss = states.get(ni);
				int wins = winloss.first();
				int losses = winloss.second();
				
				//Weight based on winratio and how many times this state has been explored
				double learnedWeight = (wins + .0)/(wins + losses) + .1/(wins + losses);
				learnedWeights.set(i, learnedWeight);
			}
			else {
				learnedWeights.set(i, .6);
			}
		}
		
		double maxWeight = 0;
		
		for (int i: moves) {
			if (learnedWeights.get(i) > maxWeight) {
				maxWeight = learnedWeights.get(i);
			}
		}
		System.out.println("Max weight is " + Double.toString(maxWeight));
		
		for (int i: moves) {
			Random rand = new Random();
			if (maxWeight - learnedWeights.get(i) <= .1) 
				bestMoves.add(i);
			else if (rand.nextInt(10) == 1) 
				bestMoves.add(i);
		}
		
		int move = super.getMoveFromList(b, color, bestMoves);
		gamePath.add(b.makeMove(move, color));
		return move;
		
		//return super.getMoveFromList(b, color, moves);
	}
	
	@Override
	public void gameOver(int status) {
		boolean win = (status == color);
		boolean loss = (status == 1-color);
		
		for (Board b : gamePath) {
			int wins;
			int losses;
			if (states.containsKey(b)) {
				IntPair winloss = states.get(b);
				wins = winloss.first();
				losses = winloss.second();
			}
			else {
				wins = 0;
				losses = 0;
			}
			if (win) {
				states.put(b, new IntPair(wins+1,losses));
				System.out.println("updated board");
			}
			if (loss) {
				states.put(b, new IntPair(wins,losses+1));
				System.out.println("updated board");
			}
		}
		
		gamePath.clear();
	}
	
	@Override
	public String getName() {
		return "Learning Board AI";
	}
}