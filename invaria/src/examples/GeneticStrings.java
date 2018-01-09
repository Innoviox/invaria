package examples;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import api.genetic.*;

public class GeneticStrings extends Algorizer {
	private static final Random r = new Random();
	private static final String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .,";//012345679!\"#$%&\\'()*+,-./:;<=>?@[\\\\]^_`{|}~ ";//\\t\\n";

	public static double linear_fitness(String elem, String target) {
		double f = 0;
		for (int i = 0; i < target.length(); i++) if (target.charAt(i) == elem.charAt(i)) f++;
		return f / target.length();
	}
	
	public static double quadratic_fitness(String elem, String target) {
		double f = 0;
		for (int i = 0; i < target.length(); i++) if (target.charAt(i) == elem.charAt(i)) f++;
		return (f * f) / (target.length() * target.length());
	}
	public static String crossover (String a, String b) {
		StringBuilder child = new StringBuilder();
		for (int i = 0; i < a.length(); i++) {
			if (r.nextInt(2) == 1) child.append(a.charAt(i));
			else child.append(b.charAt(i));
		}
		return child.toString();
	}
	
	public static String mutate (String a, double probability) {
		StringBuilder child = new StringBuilder();
		for (char c: a.toCharArray()) {
			if (r.nextDouble() > probability) child.append(c);
			else child.append(alpha.charAt(r.nextInt(alpha.length())));
		}
		return child.toString();
	}
	
	public static void main (String[] args) {	
		//String alpha = "abcdefghijklmnopqrstuvwxyz";//ABCDEFGHIJKLMNOPQRSTUVWXYZ//14690!@#$%^&*()_+-=`~[]\\{}|;':,./<>?\"";
		Scanner sc = new Scanner(System.in);
		System.out.println("Target string? ");
		String target = sc.nextLine(); 
		System.out.print("Population size? ");
		int plen = sc.nextInt();
		System.out.print("How many generations to run? ");
		int generations = sc.nextInt();
		System.out.print("Mutation constant? ");
		double mutation = sc.nextDouble();

		sc.close();
		//System.out.println(target);
		int len = target.length();
		String[] pop = new String[plen];
		String best = null;
		for (int j = 0; j < plen; j++) {
			StringBuilder str = new StringBuilder();
		
			final int alen = alpha.length();
			for (int i = 0; i < len; i++) {
				str.append(alpha.charAt(r.nextInt(alen)));
			}
			pop[j] = str.toString();
		}
		
		//String target = "to be or not to be";

		//sc.close();
		int gen;
		double avg = 0.0;
		ArrayList<String> matingPool = new ArrayList<String>();
		//System.out.println(quadratic_fitness("Heldo", "Hello"));
		for (gen = 0; gen < generations; gen++) {
			int bestn = Integer.MIN_VALUE;
			//String worst = null;
			int worstn = Integer.MAX_VALUE;
			int worsti = 0;
			avg = 0.0;
			matingPool.clear();
			for (String elem: pop) {
				int n = (int)(quadratic_fitness(elem, target) * len * len);//linear_fitness(elem, target) * 100);
				if (n > bestn) {
					bestn = n;
					best = elem;
				}
				avg += (double)n / (len * len);
				for (int j = 0; j < n; j++) matingPool.add(elem);//{System.out.println("ADDING");matingPool.add(elem);System.out.println(matingPool.size());}
			}
			
			for (int i = 0; i < plen; i++) {
				String a, b;
				do {
					a = matingPool.get(r.nextInt(matingPool.size()));
					b = matingPool.get(r.nextInt(matingPool.size()));
				} while (false);//!a.equals(b));
				String child = crossover(a, b);
				child = mutate(child, mutation);
				pop[i] = child;
			}
			
			//elitism
			for (int i = 0; i < plen; i++) {
				int n = (int)(quadratic_fitness(pop[i], target) * len * len);//linear_fitness(pop[i], target) * 100);
				if (n < worstn) {
					//worst = pop[i];
					worstn = n;
					worsti = i;
				}
			}
			pop[worsti] = best;
			
			avg /= plen;
			System.out.println("Generation " + (gen+1) + ": ");
			System.out.println("\tBest: " + best);
			System.out.println("\tAverage fitness: " + avg);
			if (best.equals(target)) break;
		}
	}

	@Override
	public Gene genElement() {
		// TODO Auto-generated method stub
		return null;
	}
}
