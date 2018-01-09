package examples;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import api.genetic.*;

public class GeneticStrings extends Algorizer<StringGene, Character> {
	private int maxGenerations;
	
	public GeneticStrings() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Target string? ");
		target = sc.nextLine().chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
		System.out.print("Population size? ");
		sampleSize = sc.nextInt();
		System.out.print("How many generations to run? ");
		maxGenerations = sc.nextInt();
		sc.close();		
	}

	@Override
	public StringGene genElement() {
		return new StringGene(target.length);
	}
	
	@Override
	public boolean done() {
		if (generation > maxGenerations) return true;
		for (StringGene sg: specimens) if (sg.getFitness() == 1) return true;
		return false;
	}

	@Override
	public void setThreshold() {
		double min = Double.MAX_VALUE;
		for (StringGene sg: specimens) {
			double f = sg.getFitness();
			if (f < min) min = f;
		}
		this.threshhold = min;
	}
	
	public static void main(String[] args) {	
		GeneticStrings gs = new GeneticStrings();
		gs.main();
	}

}
