package api.neural;

public class Neuron {
	protected Neuron[] neighbors;
	protected int[] weights;
	protected int output;
	protected int state;
	
	public Neuron (Neuron[] neighbors, int[] weights) {
		this.neighbors = neighbors;
		this.weights = weights;
		state = 0;
		output = 0;
	}
}