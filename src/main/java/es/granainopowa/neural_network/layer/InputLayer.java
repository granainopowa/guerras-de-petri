package es.granainopowa.neural_network.layer;

import es.granainopowa.neural_network.base.neuron.InputNeuron;
import es.granainopowa.neural_network.base.neuron.Neuron;

/**
 * @author Rafael Jiménez
 * 19 abr. 2018
 *
 */
public class InputLayer extends Layer {

	public InputLayer(int neuronCount) {
		super(getNeurons(neuronCount));
	}

	private static Neuron[] getNeurons(int neuronCount) {
		Neuron[] neurons = new InputNeuron[neuronCount];

		for (int i = 0; i < neuronCount; i++) {
			neurons[i] = new InputNeuron();
		}

		return neurons;
	}

}
