package api.genetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Algorizer<E> {

	protected List<Gene<E>> specimens = new ArrayList<>();
	protected int sampleSize;
	protected double threshhold;
	protected List<E> target;
	protected int generation = 0;
	protected static final Random r = new Random();
	
	public abstract Gene<E> genElement();
	public abstract boolean done();
	public abstract void setThreshold();
	public abstract void display();

	public void initializeSet() {
		boolean safe = false;
		while (!safe) {
		    specimens.clear();
			while (specimens.size() < sampleSize) {
				specimens.add(genElement());
			}
			calculateFitness();

			for (Gene<E> specimen : specimens) {
                //System.out.println(specimen);
                if (specimen.getFitness() != 0) {
                    safe = true;
                }
            }
            //System.out.println("Unsafe initialization; retrying");
        }

	}

	public void select() {
		calculateFitness();
		Iterator<Gene<E>> i = specimens.iterator();
		while (i.hasNext()) {
			Gene<E> temp = i.next();
			if (temp.getFitness() < threshhold)
				i.remove();
		}
	}

	public void crossover() {
		while (specimens.size() < sampleSize) {
			specimens.add((Gene<E>) specimens.get(r.nextInt(specimens.size())).cross(specimens.get(r.nextInt(specimens.size()))));
		}
	}

	public void mutate(){
		for (Gene<E> specimen : specimens){
			specimen.mutate();
		}
	}
	
	
	private void calculateFitness() {
		for (Gene<E> specimen : specimens) {
			specimen.findFitness(target);
		}
		normalize(specimens);
		double sum = 0;
		for (Gene<E> specimen : specimens) {
			double tempFitness = specimen.getFitness();
			specimen.setFitness(specimen.getFitness() + sum);
			sum += tempFitness;
		}
	}

	private void normalize(List<Gene<E>> spec) {
		double sum = 0;

		// may need to swap a and b
		spec.sort((b, a) -> Double.compare(b.getFitness(), a.getFitness()));

		for (Gene<E> specimen : specimens) {
			sum += specimen.getFitness();
		}

		if(sum == 0){
			return;
		}
		
		for (Gene<E> specimen : specimens) {
			specimen.setFitness(specimen.getFitness() / sum);
		}
	}
	
	public List<Gene<E>> output(){
		return new ArrayList<Gene<E>>(specimens);
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
			display();
		}
	}

	public Gene<E> worst() {
        double min = Double.MAX_VALUE;
        Gene<E> worst = null;
        for (Gene<E> t: specimens) {
            double f = t.getFitness();
            if (f < min) {
                min = f;
                worst = t;
            }
        }
        return worst;
    }

    public Gene<E> best() {
        double max = Double.MIN_VALUE;
        Gene<E> best = null;
        for (Gene<E> t: specimens) {
            double f = t.getFitness();
            if (f < max) {
                max = f;
                best = t;
            }
        }
        return best;
    }

    public double averageFitness() {
	    double f = 0;
	    for (Gene<E> t: specimens) f += t.getFitness();
	    return f / specimens.size();
    }
}
