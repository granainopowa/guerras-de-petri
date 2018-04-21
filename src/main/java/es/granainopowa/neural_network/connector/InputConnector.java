package es.granainopowa.neural_network.connector;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class InputConnector extends Connector {

	protected double input = 0;

	public void setInput(double input) {
		this.input = input;
	}

	@Override
	protected double getConnectorInput() {
		return this.input;
	}
}
