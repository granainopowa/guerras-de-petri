package es.granainopowa.neural_network;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Rafael Jiménez 18 abr. 2018
 *
 */
public class NeuronTest {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(6);
		Network network = new Network(3, list, 4);

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

		network.computeNetwork(2, 3, 4);
		parsedNetwork.computeNetwork(2, 3, 4);

		if (!(network.getNetworkOutputs().equals(parsedNetwork.getNetworkOutputs()))) {
			fail("Parsed netros is not right");
		}

		network.computeNetwork(-2, -3, -4);
		parsedNetwork.computeNetwork(-2, -3, -4);

		if (!(network.getNetworkOutputs().equals(parsedNetwork.getNetworkOutputs()))) {
			fail("Parsed netros is not right");
		}
	}

}
