package es.granainopowa.neural_network;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.layer.HiddenLayer;
import es.granainopowa.neural_network.layer.InputLayer;
import es.granainopowa.neural_network.layer.Layer;
import es.granainopowa.neural_network.neuron.Neuron;

/**
 * Neural Network
 *
 * @author Rafael Jiménez 18 abr. 2018
 *
 */
public class Network {

	private InputLayer inputLayer;
	private List<HiddenLayer> hiddenLayers;

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
	public Network(int inputCount, int outputCount, List<Integer> hiddenLayersNeuronCount) {
		if (hiddenLayersNeuronCount == null) {
			throw new IllegalStateException("hiddenLayersNeuronCount cannot be null");
		}
		createNetwork(inputCount, outputCount, hiddenLayersNeuronCount);
	}

	public Network(int inputCount, int outputCount) {
		createNetwork(inputCount, outputCount, new ArrayList<>());
	}

	public void computeNetwork(double... inputs) {
		inputLayer.setInputs(inputs);
		inputLayer.compute();
		for (HiddenLayer layer : this.hiddenLayers) {
			layer.compute();
		}
	}

	public List<Double> getNetworkOutputs() {
		int lastLayerIndex = this.hiddenLayers.size() - 1;
		HiddenLayer lastLayer = this.hiddenLayers.get(lastLayerIndex);

		return lastLayer.getOutputs();
	}

	protected List<HiddenLayer> getLayers() {
		return this.hiddenLayers;
	}

	private void createNetwork(int inputCount, int outputCount, List<Integer> hiddenLayersNeuronCount) {
		if (inputCount < 1) {
			throw new IllegalStateException("At least one input is needed");
		}
		if (outputCount < 1) {
			throw new IllegalStateException("At least one output is needed");
		}

		// add the output layer at the end of the hidden layers
		hiddenLayersNeuronCount.add(outputCount);

		this.inputLayer = new InputLayer(inputCount);
		hiddenLayers = new ArrayList<>();

		Layer<? extends Neuron> previousLayer = this.inputLayer;
		for (Integer neuronCount : hiddenLayersNeuronCount) {
			HiddenLayer layer = new HiddenLayer(neuronCount, previousLayer);
			this.hiddenLayers.add(layer);
			previousLayer = layer;
		}
	}
}
