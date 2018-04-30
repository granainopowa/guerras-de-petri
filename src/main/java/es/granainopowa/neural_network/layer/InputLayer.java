package es.granainopowa.neural_network.layer;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.neuron.InputNeuron;

/**
 * <h1>Creates an input layer</h1>
 *
 * @author Rafael Jiménez (19 abr. 2018)
 */
public class InputLayer extends Layer<InputNeuron> {

	public InputLayer(int neuronCount) {
		super(createNeurons(neuronCount));
	}

	public void setInputs(List<Double> inputs) {
		if (inputs == null || inputs.size() != getNeuronCount()) {
			throw new IllegalStateException("Invalid input count for the layer");
		}
		for (int i = 0; i < inputs.size(); i++) {
			this.neurons.get(i).setInput(inputs.get(i));
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
