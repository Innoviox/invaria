package api.genetic;

import java.util.ArrayList;
import java.util.List;

public abstract class Gene<E> {
    protected List<E> genotype;
    protected double fitness;

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
	    genotype = new ArrayList<E>();
	    for (int i = 0; i < length; i++)
	        genotype.add(alphabet[Algorizer.r.nextInt(alphabet.length)]);
    }
	
	public Gene (List<E> genotype) {
		this.genotype = genotype;
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
	public abstract void findFitness(List<E> target);

	public abstract Gene<E> cross(Gene<E> a);

	public abstract void mutate();


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double d) {
        fitness = d;
    }

	public List<E> getGenotype() {
	    return genotype;
    }

    public void setGenotype(List<E> genotype) {
        this.genotype = genotype;
    }

    public E get(int i) { return genotype.get(i); }
}
