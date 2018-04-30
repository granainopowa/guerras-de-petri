package es.granainopowa.neural_network;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.layer.HiddenLayer;
import es.granainopowa.neural_network.layer.HiddenLayerPOJO;
import es.granainopowa.neural_network.layer.InputLayer;
import es.granainopowa.neural_network.layer.Layer;
import es.granainopowa.neural_network.neuron.Neuron;

/**
 * Neural Network
 *
 * @author Rafael Jiménez (18 abr. 2018)
 */
public class Network {

	private InputLayer inputLayer;
	private List<HiddenLayer> hiddenLayers;

	/**
	 * Creates a neural network with as many layers as parameters.
	 *
	 * @param inputCount
	 *            number of neurons in the input layer
	 * @param hiddenLayersNeuronCount
	 *            array of hidden layers. For each value in the array, a layer is
	 *            created with its number of neurons
	 * @param outputCount
	 *            number of neurons in the output layer
	 */
	public Network(int inputCount, List<Integer> hiddenLayersNeuronCount, int outputCount) {
		if (hiddenLayersNeuronCount == null) {
			throw new IllegalStateException("hiddenLayersNeuronCount cannot be null");
		}
		createNetwork(inputCount, hiddenLayersNeuronCount, outputCount);
	}

	public Network(int inputCount, int outputCount) {
		createNetwork(inputCount, new ArrayList<>(), outputCount);
	}

	public Network(NetworkPOJO networkPOJO) {
		if (networkPOJO == null) {
			throw new IllegalStateException("networkPOJO cannot be null");
		}
		int inputCount = networkPOJO.getInputCount();
		List<HiddenLayerPOJO> hiddenLayerPOJOs = networkPOJO.getHiddenLayerPOJOs();
		createNetwork(inputCount, hiddenLayerPOJOs);
	}

	public NetworkPOJO getPOJO() {
		NetworkPOJO networkPOJO = new NetworkPOJO();
		List<HiddenLayerPOJO> hiddenLayerPOJOs = new ArrayList<>();

		for (HiddenLayer hiddenLayer : this.hiddenLayers) {
			hiddenLayerPOJOs.add(hiddenLayer.getPOJO());
		}

		networkPOJO.setInputCount(this.inputLayer.getNeuronCount());
		networkPOJO.setHiddenLayerPOJOs(hiddenLayerPOJOs);

		return networkPOJO;
	}

	public void computeNetwork(List<Double> inputs) {
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

	private void createNetwork(int inputCount, List<Integer> hiddenLayersNeuronCount, int outputCount) {
		if (inputCount < 1) {
			throw new IllegalStateException("At least one input is needed");
		}
		if (outputCount < 1) {
			throw new IllegalStateException("At least one output is needed");
		}

		ArrayList<Integer> arrayList = new ArrayList<>(hiddenLayersNeuronCount);

		// add the output layer at the end of the hidden layers
		arrayList.add(outputCount);

		this.inputLayer = new InputLayer(inputCount);
		this.hiddenLayers = new ArrayList<>();

		Layer<? extends Neuron> previousLayer = this.inputLayer;
		for (Integer neuronCount : arrayList) {
			HiddenLayer layer = new HiddenLayer(neuronCount, previousLayer);
			this.hiddenLayers.add(layer);
			previousLayer = layer;
		}
	}

	private void createNetwork(int inputCount, List<HiddenLayerPOJO> hiddenLayerPOJOs) {
		if (inputCount < 1) {
			throw new IllegalStateException("At least one input is needed");
		}
		if (hiddenLayerPOJOs.size() < 1) {
			throw new IllegalStateException("At least one output is needed");
		}

		this.inputLayer = new InputLayer(inputCount);
		this.hiddenLayers = new ArrayList<>();

		Layer<? extends Neuron> previousLayer = this.inputLayer;
		for (HiddenLayerPOJO hiddenLayerPOJO : hiddenLayerPOJOs) {
			HiddenLayer layer = new HiddenLayer(previousLayer, hiddenLayerPOJO);
			this.hiddenLayers.add(layer);
			previousLayer = layer;
		}
	}

}
