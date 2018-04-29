package es.granainopowa.neural_network.layer;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.neuron.Neuron;

/**
 * @author Rafael Jiménez
 * 19 abr. 2018
 *
 */
public abstract class Layer<T extends Neuron> {

	protected List<T> neurons;

	protected Layer(List<T> neurons) {
		if (neurons == null || neurons.isEmpty()) {
			throw new IllegalArgumentException("It is not possible to create a layer without neurons");
		}
		this.neurons = neurons;
	}

	public int getNeuronCount() {
		return neurons.size();
	}

	public final void compute() {
		for (T neuron : this.neurons) {
			neuron.computeOutput();
		}
	}

	public List<Double> getOutputs() {
		List<Double> outputs = new ArrayList<>();

		for (T neuron : neurons) {
			outputs.add(neuron.getOutput());
		}

		return outputs;
	}

}
