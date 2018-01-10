package examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import api.genetic.Gene;

public class StringGene extends Gene<Character> {
	public static Character[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .,".chars().mapToObj(c -> (char)c).toArray(Character[]::new);
	private static final Random r = new Random();
	private static final double mutation = 0.01;
	
	public StringGene(int length) {
		super(alphabet, length);
	}

	public StringGene(List<Character> genotype) {
		super(genotype);
	}

	@Override
	public void findFitness(List<Character> target) {
		double f = 0;
		for (int i = 0; i < target.size(); i++)
			if (get(i).equals(target.get(i)))
				f++;
		setFitness((f * f) / (target.size() * target.size()));
	}

	@Override
	public Gene<Character> cross(Gene<Character> a) {
		List<Character> child = new ArrayList();
		for (int i = 0; i < genotype.size(); i++) {
			if (r.nextInt(2) == 1) child.add(get(i));
			else child.add(a.get(i));
		}
		return new StringGene(child);
	}

	@Override
	public void mutate() {
		List<Character> a = new ArrayList();
		for (int i = 0; i < genotype.size(); i++) {
			if (r.nextDouble() > mutation) a.add(get(i));
			else a.add(alphabet[r.nextInt(alphabet.length)]);
		}
		this.setGenotype(a);
	}

	@Override
	public String toString() {
		String s = "";
		for (Character c: genotype) s += c;
		return s;// + " " + fitness;
	}
}
