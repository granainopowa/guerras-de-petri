package es.granainopowa.neural_network;

import es.granainopowa.neural_network.base.neuron.InputNeuron;
import es.granainopowa.neural_network.connector.HiddenConnector;
import es.granainopowa.neural_network.connector.InputConnector;
import es.granainopowa.neural_network.layer.HiddenLayer;
import es.granainopowa.neural_network.layer.InputLayer;
import es.granainopowa.neural_network.layer.Layer;

/**
 * Neural Network
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class Network {

	private InputLayer inputLayer;
	private HiddenLayer[] hiddenLayers;
	private int outputCount;

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
		this.inputLayer = new InputLayer(inputCount);
		this.hiddenLayers = new HiddenLayer[hiddenLayersNeuronCount.length + 1];
		this.outputCount = outputCount;

		if (hiddenLayersNeuronCount.length == 0) {
			// there is only outputLayer
			this.hiddenLayers[0] = new HiddenLayer(outputCount, inputLayer);
		} else {
			int layerCount = hiddenLayersNeuronCount.length;
			int i = 1;
			this.hiddenLayers[0] = new HiddenLayer(hiddenLayersNeuronCount[0], inputLayer);
			while (i < layerCount) {
				int neuronCount = hiddenLayersNeuronCount[i];
				this.hiddenLayers[i] = new HiddenLayer(neuronCount, this.hiddenLayers[i - 1]);
				i++;
			}
			this.hiddenLayers[hiddenLayersNeuronCount.length] = new HiddenLayer(outputCount, this.hiddenLayers[i - 1]);
		}
	}

}
