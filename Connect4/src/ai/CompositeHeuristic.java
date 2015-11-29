package ai;

import gameDefs.Board;

/**
 * Heuristic that composes two given ones and returns the sum. Can be
 * constructed with weights on each heuristic, defaults to 1.
 */
public class CompositeHeuristic implements Heuristic {

	Heuristic heuristic_a;
	Heuristic heuristic_b;
	int weight_a;
	int weight_b;

	public CompositeHeuristic(Heuristic a, Heuristic b) {
		heuristic_a = a;
		heuristic_b = b;
		weight_a = 1;
		weight_b = 1;
	}

	public CompositeHeuristic(Heuristic a, Heuristic b, int weight_a,
			int weight_b) {
		heuristic_a = a;
		heuristic_b = b;
		this.weight_a = weight_a;
		this.weight_b = weight_b;
	}

	@Override
	public int evaluate(Board b, int player) {
		return heuristic_a.evaluate(b, player) * weight_a
				+ heuristic_b.evaluate(b, player) * weight_b;
	}

	@Override
	public String getName() {
		return heuristic_a.getName() + " + " + heuristic_b.getName();
	}

}
