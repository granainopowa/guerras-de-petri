package es.granainopowa.neural_network;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.granainopowa.neural_network.layer.HiddenLayer;
import es.granainopowa.neural_network.layer.InputLayer;

/**
 * @author Rafael Jiménez (18 abr. 2018)
 */
public class NeuronTest {

	@Test
	public void test() {
		List<Integer> hiddenLayersNeuronCount = Arrays.asList(5, 6);
		Network network = new Network(3, hiddenLayersNeuronCount, 4);

		String networkJSON;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			NetworkPOJO networkPOJO = network.getPOJO();
			networkJSON = objectMapper.writeValueAsString(networkPOJO);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		ObjectMapper mapper = new ObjectMapper();
		Network parsedNetwork;
		try {
			NetworkPOJO parsedNetworkPOJO = mapper.readValue(networkJSON, NetworkPOJO.class);
			parsedNetwork = new Network(parsedNetworkPOJO);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		InputLayer inputLayer = network.getInputLayer();
		InputLayer parsedInputLayer = parsedNetwork.getInputLayer();
		if (inputLayer.getNeuronCount() != parsedInputLayer.getNeuronCount()) {
			fail("not same input count");
		}

		List<HiddenLayer> layers = network.getHiddenLayers();
		List<HiddenLayer> parsedLayers = parsedNetwork.getHiddenLayers();
		if (layers.size() != parsedLayers.size()) {
			fail("not same layers count");
		}

		for (int i = 0; i < layers.size(); i++) {
			HiddenLayer hiddenLayer = layers.get(i);
			HiddenLayer parsedHiddenLayer = parsedLayers.get(i);
			if (hiddenLayer.getNeuronCount() != parsedHiddenLayer.getNeuronCount()) {
				fail("not same neuron count in the hidden layer " + i);
			}
		}

		HiddenLayer outputLayer = network.getOutputLayer();
		HiddenLayer parsedOutputLayer = parsedNetwork.getOutputLayer();
		if (outputLayer.getNeuronCount() != parsedOutputLayer.getNeuronCount()) {
			fail("not same output count");
		}

		compareNetworkOutputs(network, parsedNetwork, Arrays.asList(2d, 3d, 4d));
		compareNetworkOutputs(network, parsedNetwork, Arrays.asList(-2d, -3d, -4d));
	}

	private void compareNetworkOutputs(Network network1, Network network2, List<Double> inputs) {
		network1.computeNetwork(inputs);
		network2.computeNetwork(inputs);

		List<Double> outputs1 = network1.getNetworkOutputs();
		List<Double> outputs2 = network2.getNetworkOutputs();
		if (!(outputs1.equals(outputs2))) {
			fail("Parsed network is not right");
		}
	}

}
