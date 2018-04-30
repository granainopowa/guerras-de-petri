package es.granainopowa.neural_network.layer;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.connector.Connector;
import es.granainopowa.neural_network.neuron.HiddenNeuron;
import es.granainopowa.neural_network.neuron.HiddenNeuronPOJO;
import es.granainopowa.neural_network.neuron.Neuron;

/**
 * @author Rafael Jiménez (21 abr. 2018)
 */
public class HiddenLayer extends Layer<HiddenNeuron> {

	public HiddenLayer(int neuronCount, Layer<? extends Neuron> previousLayer) {
		super(createNeurons(neuronCount, previousLayer));
	}

	public HiddenLayer(Layer<? extends Neuron> previousLayer, HiddenLayerPOJO hiddenLayerPOJO) {
		super(createNeurons(previousLayer, hiddenLayerPOJO));
	}

	public HiddenLayerPOJO getPOJO() {
		HiddenLayerPOJO hiddenLayerPOJO = new HiddenLayerPOJO();
		List<HiddenNeuronPOJO> neuronPOJOs = new ArrayList<>();

		for (HiddenNeuron hiddenNeuron : this.neurons) {
			neuronPOJOs.add(hiddenNeuron.getPOJO());
		}

		hiddenLayerPOJO.setNeurons(neuronPOJOs);

		return hiddenLayerPOJO;
	}

	private static List<HiddenNeuron> createNeurons(int neuronCount, Layer<? extends Neuron> previousLayer) {
		List<HiddenNeuron> neurons = new ArrayList<>();

		// create neuronCount neurons
		for (int i = 0; i < neuronCount; i++) {
			List<Connector> hiddenConnectors = new ArrayList<>();
			// each new neuron has a connector to each neuron of the previous layer
			for (Neuron previousNeuron : previousLayer.neurons) {
				hiddenConnectors.add(new Connector(previousNeuron));
			}
			neurons.add(new HiddenNeuron(hiddenConnectors));
		}

		return neurons;
	}

	private static List<HiddenNeuron> createNeurons(Layer<? extends Neuron> previousLayer,
			HiddenLayerPOJO hiddenLayerPOJO) {
		List<HiddenNeuron> neurons = new ArrayList<>();
		int previousLayerNeuronCount = previousLayer.getNeuronCount();

		// create a neuron for each hiddenNeuronPOJO
		for (HiddenNeuronPOJO hiddenNeuronPOJO : hiddenLayerPOJO.getNeuronPOJOs()) {
			List<Double> inputWeights = hiddenNeuronPOJO.getInputWeights();
			if (inputWeights.size() != previousLayerNeuronCount) {
				throw new IllegalStateException(
						"Cannot connect layers. Connectors and previous layer sizes are not the same");
			}
			List<Connector> hiddenConnectors = new ArrayList<>();
			for (int i = 0; i < previousLayerNeuronCount; i++) {
				Neuron previousNeuron = previousLayer.neurons.get(i);
				hiddenConnectors.add(new Connector(previousNeuron, inputWeights.get(i)));
			}
			neurons.add(new HiddenNeuron(hiddenConnectors));
		}

		return neurons;
	}

}
