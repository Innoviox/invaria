package examples;

import api.genetic.Gene;

public class StringGene extends Gene<Character> {
	private static final Character[] alphabet = "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(c -> (char)c).toArray(Character[]::new);
	
	public StringGene(int length) {
		super(alphabet, length);
	}

	@Override
	public void findFitness(Character[] target) {
		
	}

	@Override
	public Gene cross(Gene a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mutate() {
		// TODO Auto-generated method stub

	}

}
