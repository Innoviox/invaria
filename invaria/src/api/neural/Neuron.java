package api.neural;

import java.util.Random;

public abstract class Neuron {
	protected Neuron[] neighbors;
	protected double[] weights;
	protected double output;
	protected double state;
    protected double threshold;

    /**
     * No default instantiation for you!
     */
    Neuron ( ) { }

    /**
     * Basic neuron constructor
     * Default values for state and output are 0.
     * @param neighbors
     * @param weights
     */
	public Neuron (Neuron[] neighbors, double[] weights, double threshold) {
		this.neighbors = neighbors;
		this.weights = weights;
		state = 0;
		output = 0;
		threshold = 0;
	}

    /**
     * Random neuron constructor
     * State: random from 0 - 1, excl.
     * Output: 0
     * @param neighbors
     * @param weights
     * @param r random generator to use (instance of java.util.Random)
     */
    public Neuron(Neuron[] neighbors, double[] weights, double threshold, Random r) {
	    this(neighbors, weights, threshold);
	    state = r.nextDouble();
    }

    /**
     * Process an input array and maybe feed it forward
     * @param inputs
     */
    public abstract void process(double[] inputs);
}