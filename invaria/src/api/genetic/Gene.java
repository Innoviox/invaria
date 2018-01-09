package api.genetic;

public abstract class Gene<E> {
    private E[] genotype;
    private double fitness;

    /**
     * Randomly initialize a Gene of size length
     * from a typed alphabet. Example:
     *
     * <pre>Gene a = new Gene<Char>("abcdefghijklmnopqrstuvwxyz".toCharArray(), 5);</pre>
     *
     * would create a string gene of five random letters.
     * @param alphabet array of elements to choose from
     * @param length size of the array
     */
	public Gene (E[] alphabet, int length) {
	    genotype = (E[]) new Object[length];
	    for (int i = 0; i < length; i++)
	        genotype[i] = alphabet[Algorizer.r.nextInt(alphabet.length)];
    }

    /**
     * Calculate the fitness of a gene
     * For example:
     * Gene is ["a", "b", "c", "d", "e"].
     * Fitness test is "how many letters are in the right
     * positions for "apple". a and e, so 2. Should also set
     * the fitness of this Gene to the found fitness.
     * @param target the target to check to, in this case "apple".
     */
	public abstract void findFitness(E[] target);

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
