package es.granainopowa.neural_network.layer;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.neuron.InputNeuron;

/**
 * <p>Creates an input layer.</p>
 * <p>.</p>
 *
 * @author Rafael Jiménez
 * 19 abr. 2018
 */
public class InputLayer extends Layer<InputNeuron> {

	public InputLayer(int neuronCount) {
		super(createNeurons(neuronCount));
	}

	public void setInputs(double... inputs) {
		if (inputs == null || inputs.length != neuronCount()) {
			throw new IllegalStateException("Invalid input count for the layer");
		}
		for (int i = 0; i < inputs.length; i++) {
			this.neurons.get(i).setInput(inputs[i]);
		}
	}

	private static List<InputNeuron> createNeurons(int neuronCount) {
		List<InputNeuron> neurons = new ArrayList<>();

		for (int i = 0; i < neuronCount; i++) {
			neurons.add(new InputNeuron());
		}

		return neurons;
	}

}
