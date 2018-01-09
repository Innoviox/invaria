package api.genetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Algorizer<T extends Gene, E> {

	protected List<T> specimens = new ArrayList<>();
	protected int sampleSize;
	protected double threshhold;
	protected E[] target;
	protected int generation = 0;
	protected static final Random r = new Random();
	
	public abstract T genElement();
	public abstract boolean done();
	public abstract void setThreshold();
	
	public void initializeSet() {
		boolean safe = false;
		while (!safe) {
			while (specimens.size() < sampleSize) {
				specimens.add(genElement());
			}
			calculateFitness();
			for (T specimen : specimens) {
				if (specimen.getFitness() != 0){
					safe = true;
				}
			}
		}

	}

	public void select() {
		calculateFitness();
		Iterator<T> i = specimens.iterator();
		while (i.hasNext()) {
			T temp = i.next();
			if (temp.getFitness() < threshhold)
				i.remove();
		}
	}

	public void crossover() {
		while (specimens.size() < sampleSize) {
			specimens.add((T) specimens.get(r.nextInt(specimens.size())).cross(specimens.get(r.nextInt(specimens.size()))));
		}
	}

	public void mutate(){
		for (T specimen : specimens){
			specimen.mutate();
		}
	}
	
	
	private void calculateFitness() {
		for (T specimen : specimens) {
			specimen.findFitness(target);
		}
		normalize(specimens);
		double sum = 0;
		for (T specimen : specimens) {
			double tempFitness = specimen.getFitness();
			specimen.setFitness(specimen.getFitness() + sum);
			sum += tempFitness;
		}
	}

	private void normalize(List<T> spec) {
		double sum = 0;

		// may need to swap a and b
		spec.sort((b, a) -> Double.compare(b.getFitness(), a.getFitness()));

		for (T specimen : specimens) {
			sum += specimen.getFitness();
		}

		if(sum == 0){
			return;
		}
		
		for (T specimen : specimens) {
			specimen.setFitness(specimen.getFitness() / sum);
		}
	}
	
	public List<T> output(){
		return new ArrayList<T>(specimens);
	}
	
	public void tick() {
		generation++;
		select();
		crossover();
		mutate();
	}
	
	public void main() {
		initializeSet();
		while (!done()) {
			tick();
			setThreshold();
		}
	}
}
