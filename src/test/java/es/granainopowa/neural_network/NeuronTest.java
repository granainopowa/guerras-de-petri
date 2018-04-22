package es.granainopowa.neural_network;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class NeuronTest {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(6);
		Network network = new Network(3, 4, list);
		network.computeNetwork(1, -2, 3);
		//List<Double> networkOutputs = network.getNetworkOutputs();

		fail("Not yet implemented");
	}

}
