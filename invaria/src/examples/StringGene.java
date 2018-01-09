package examples;

import java.util.Random;

import api.genetic.Gene;

public class StringGene extends Gene<Character> {
	private static final Character[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .,".chars().mapToObj(c -> (char)c).toArray(Character[]::new);
	private static final Random r = new Random();
	private static final double mutation = 0.05;
	
	public StringGene(int length) {
		super(alphabet, length);
	}

	public StringGene(Character[] genotype) {
		super(genotype);
	}

	@Override
	public void findFitness(Character[] target) {
		int f = 0;
		for (int i = 0; i < target.length; i++) 
			if (genotype[i].equals(target[i])) 
				f++;
		setFitness((f * f) / (target.length * target.length));
	}

	@Override
	public Gene<Character> cross(Gene<Character> a) {
		Character[] child = new Character[genotype.length];
		for (int i = 0; i < genotype.length; i++) {
			if (r.nextInt(2) == 1) child[i] = genotype[i];
			else child[i] = (Character) a.getGenotype()[i];
		}
		return new StringGene(child);
	}

	@Override
	public void mutate() {
		Character[] child = new Character[genotype.length];
		for (int i = 0; i < genotype.length; i++) {
			if (r.nextDouble() > mutation) child[i] = genotype[i];
			else child[i] = alphabet[r.nextInt(alphabet.length)];
		}
		this.setGenotype(child);
	}

}
