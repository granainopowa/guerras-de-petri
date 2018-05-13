package es.granainopowa.neural_network;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import es.granainopowa.guerras_de_petri.utils.ActivationFunction;
import es.granainopowa.neural_network.connector.Connector;
import es.granainopowa.neural_network.layer.HiddenLayer;
import es.granainopowa.neural_network.neuron.HiddenNeuron;

/**
 * @author Rafael Jiménez (9 may. 2018)
 *
 */
public class NetworkTest {

	@Test
	public void test() {
		Network network = new Network(1, Arrays.asList(1), 1);

		// InputLayer inputLayer = network.getInputLayer();
		HiddenLayer hiddenLayer = network.getHiddenLayers().get(0);
		HiddenLayer outputLayer = network.getOutputLayer();

		// InputNeuron inputNeuron = inputLayer.getNeurons().get(0);
		HiddenNeuron hiddenNeuron = hiddenLayer.getNeurons().get(0);
		HiddenNeuron outputNeuron = outputLayer.getNeurons().get(0);

		Connector hiddenConnector = hiddenNeuron.getInputs().get(0);
		Connector outputConnector = outputNeuron.getInputs().get(0);

		hiddenConnector.setWeight(0);
		outputConnector.setWeight(0);

		network.computeNetwork(Arrays.asList(1.0));
		Double output = network.getNetworkOutputs().get(0);

		if (!(output == 0)) {
			fail("wrong output (" + output + " != 0)");
		}
	}

	@Test
	public void testSigmoid() {
		testSigmoid(-100000000, -1);
		testSigmoid(0, 0);
		testSigmoid(100000000, 1);
	}

	private void testSigmoid(double input, double desired) {
		double output = ActivationFunction.sigmoid(input);
		if (output != desired) {
			fail("wrong output (" + output + " != " + desired + ")");
		}
	}

	@Test
	public void testTanHiperbolic() {
		testTanHiperbolic(-100000000, -1);
		testTanHiperbolic(0, 0);
		testTanHiperbolic(100000000, 1);
	}

	public void testTanHiperbolic(double input, double desired) {
		double output = ActivationFunction.tanHiperbolic(input);
		if (output != desired) {
			fail("wrong output (" + output + " != " + desired + ")");
		}
	}
}
