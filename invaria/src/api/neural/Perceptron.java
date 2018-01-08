package api.neural;

public class Perceptron extends Neuron {
	public Perceptron (Perceptron next) {
		
	}

	@Override
    /**
     * Sum all inputs in the array. If pass
     * this.threshold, do something?
     */
	public void process (double[] inputs) {
		if (inputs.length != weights.length)
			throw new IllegalArgumentException("Incorrect number of inputs or weights: found " +
					inputs.length + " inputs and " + weights.length + " weights.");

        double sum = 0;
        for (int i = 0; i < inputs.length; i++) sum += inputs[i] * weights[i];
        if (sum > threshold) {
            state = 1;

        }
	}
}
