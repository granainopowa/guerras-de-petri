package es.granainopowa.neural_network.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Neural Network
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class Network {

	private List<Neuron> inputLayer = new ArrayList<>();

	/**
	 * Creates a neural network with as many layers as parameters.
	 * 
	 * @param layerNeuronCount
	 *            array of layers. For each value in the array, a layer is created
	 *            with its number of neurons
	 */
	public Network(int... layerNeuronCount) {
		for (int neuronCount : layerNeuronCount) {
			//create layer
		}
	}

	public void addInputNeuron() {
		inputLayer.add(new Neuron());
	}
}
