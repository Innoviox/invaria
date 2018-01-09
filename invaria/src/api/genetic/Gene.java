package api.genetic;

public abstract class Gene<E> {
    private E[] genotype;
    private double fitness;

	public Gene (E[] alphabet, int length) {
	    genotype = (E[]) new Object[length];
	    for (int i = 0; i < length; i++)
	        genotype[i] = alphabet[Algorizer.r.nextInt(alphabet.length)];
    }

	public abstract void findFitness(Object target);

	public abstract Gene cross(Gene a);

	public abstract void mutate();




    public double getFitness() {
        return fitness;
    }

    public void setFitness(double d) {
        fitness = d;
    }

	public E[] getGenotype() {
	    return genotype;
    }

    public void setGenotype(E[] genotype) {
        this.genotype = genotype;
    }
}
