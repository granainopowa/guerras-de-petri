package es.granainopowa.neural_network.layer;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.neural_network.connector.Connector;
import es.granainopowa.neural_network.neuron.HiddenNeuron;
import es.granainopowa.neural_network.neuron.Neuron;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class HiddenLayer extends Layer<HiddenNeuron> {

	public HiddenLayer(int neuronCount, Layer<? extends Neuron> previousLayer) {
		super(createNeurons(neuronCount, previousLayer));
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
}
