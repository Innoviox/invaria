package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import api.genetic.*;

public class GeneticStrings extends Algorizer<Character> {
	private int maxGenerations;
	
	public GeneticStrings() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Target string? ");
		target = sc.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		// StringGene.alphabet = target.toArray(new Character[0]);
		System.out.print("Population size? ");
		sampleSize = sc.nextInt();
		System.out.print("How many generations to run? ");
		maxGenerations = sc.nextInt();
		sc.close();		
	}

	@Override
	public StringGene genElement() {
		return new StringGene(target.size());
	}
	
	@Override
	public boolean done() {
		if (generation > maxGenerations) return true;
		for (Gene<Character> sg: specimens) if (sg.getFitness() == 1) return true;
		return false;
	}

	@Override
	public void setThreshold() {
		this.threshhold = averageFitness();
	}

	@Override
	public void display() {
		Gene<Character> best = best();
		System.out.println("Best: " + best.toString());
	}

	public static void main(String[] args) {	
		GeneticStrings gs = new GeneticStrings();
		gs.main();
	}

}
