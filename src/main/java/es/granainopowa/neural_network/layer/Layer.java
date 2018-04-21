package es.granainopowa.neural_network.layer;

import es.granainopowa.neural_network.base.neuron.Neuron;

/**
 * @author Rafael Jiménez
 * 19 abr. 2018
 *
 */
public abstract class Layer {

	protected Neuron[] neurons;

	protected Layer(Neuron[] neurons) {
		this.neurons = neurons;
	}

	public final void compute() {
		for (Neuron neuron : this.neurons) {
			neuron.computeOutput();
		}
	}

	public double[] getOutputs() {
		int neuronsCount = this.neurons.length;
		double[] outputs = new double[neuronsCount];

		for (int i = 0; i <= neuronsCount; i++) {
			Neuron neuron = this.neurons[i];
			outputs[i] = neuron.getOutput();
		}

		return outputs;
	}

}
