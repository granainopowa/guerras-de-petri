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

	private Layer inputLayer;
	private Layer outputLayer;

	/**
	 * Creates a neural network with as many layers as parameters.
	 *
	 * @param inputCount
	 *            number of neurons in the input layer
	 * @param outputCount
	 *            number of neurons in the output layer
	 * @param hiddenLayersNeuronCount
	 *            array of hidden layers. For each value in the array, a layer is
	 *            created with its number of neurons
	 */
	public Network(int inputCount, int outputCount, int... hiddenLayersNeuronCount) {
		// TODO: connect layers
		this.inputLayer = new Layer(inputCount);
		for (int neuronCount : hiddenLayersNeuronCount) {
			Layer hiddenLayer = new Layer(neuronCount);
		}
		this.outputLayer = new Layer(outputCount);
	}

}
