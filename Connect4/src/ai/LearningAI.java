package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import gameDefs.Board;
import gameDefs.Player;

public class LearningAI extends MinimaxAI implements Player{
	HashMap<Pair<IntPair,IntPair>, IntPair> states = new HashMap<Pair<IntPair,IntPair>, IntPair>();
	ArrayList<Pair<IntPair,IntPair>> gamePath = new ArrayList<Pair<IntPair,IntPair>>();
	Heuristic heuristic1;
	Heuristic heuristic2;
	int color;
	
	public LearningAI(Heuristic h1, Heuristic h2, int depth) {
		super(new CompositeHeuristic(h1,h2), depth);
		heuristic1 = h1;
		heuristic2 = h2;
		color = -1;
	}
	
	public int getMove(Board b, int color) {
		this.color = color;
		IntPair myState = new IntPair(heuristic1.evaluate(b,color), heuristic2.evaluate(b,color));
		IntPair oppState = new IntPair(heuristic1.evaluate(b,color), heuristic2.evaluate(b,color));
		Pair<IntPair, IntPair> state= new Pair<IntPair, IntPair>(myState,oppState);
		gamePath.add(state);
		
		List<Integer> moves = b.getLegalMoves();
		List<Integer> bestMoves = new ArrayList<Integer>();
		List<Double> learnedWeights = new ArrayList<Double>();
		for (int i = 0; i < 7; i++) {
			learnedWeights.add(0.0);
		}
		
		for (int i : moves) {
			Board ni = b.makeMove(i, color);
			IntPair nMyState = new IntPair(heuristic1.evaluate(ni,color), heuristic2.evaluate(ni,color));
			IntPair nOppState = new IntPair(heuristic1.evaluate(ni,color), heuristic2.evaluate(ni,color));
			Pair<IntPair, IntPair> nState= new Pair<IntPair, IntPair>(nMyState,nOppState);
			
			if (states.containsKey(nState)) {
				IntPair winloss = states.get(nState);
				int wins = winloss.first();
				int losses = winloss.second();
				
				//Weight based on winratio and how many times this state has been explored
				double learnedWeight = (wins + .0)/(wins + losses) + .3/(wins + losses);
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
			if (maxWeight - learnedWeights.get(i) <= .5) 
				bestMoves.add(i);
			else if (rand.nextInt(10) == 1) 
				bestMoves.add(i);
		}
		
		int move = super.getMoveFromList(b, color, bestMoves);
		Board nb = b.makeMove(move, color);
		IntPair nMyState = new IntPair(heuristic1.evaluate(nb,color), heuristic2.evaluate(nb,color));
		IntPair nOppState = new IntPair(heuristic1.evaluate(nb,color), heuristic2.evaluate(nb,color));
		Pair<IntPair, IntPair> nState= new Pair<IntPair, IntPair>(nMyState,nOppState);
		gamePath.add(nState);
		return move;
		
		
		//return super.getMoveFromList(b, color, moves);
	}
	
	@Override
	public void gameOver(int status) {
		boolean win = (status == color);
		boolean loss = (status == 1-color);
		
		for (Pair<IntPair,IntPair> state : gamePath) {
			int wins;
			int losses;
			if (states.containsKey(state)) {
				IntPair winloss = states.get(state);
				wins = winloss.first();
				losses = winloss.second();
			}
			else {
				wins = 0;
				losses = 0;
			}
			if (win) {
				states.put(state, new IntPair(wins+1,losses));
			}
			if (loss) {
				states.put(state, new IntPair(wins,losses+1));
			}
		}
		
		gamePath.clear();
	}
	
	@Override
	public String getName() {
		return "Learning AI";
	}
}
