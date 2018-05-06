package es.granainopowa.guerras_de_petri.bacteria.appendix.input;

import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.appendix.BacteriaAppendix;

/**
 * <h1>This is being used for input appendices of bacterias.</h1>
 * <p>
 * Each one should provide an input to the network
 * </p>
 *
 * @author Rafael Jiménez (30 abr. 2018)
 */
public abstract class InputAppendix extends BacteriaAppendix {

	/**
	 * @param host
	 *            owner of the InputAppendix
	 * @param inputNeuronConnections
	 *            number of network inputs needed by the InputAppendix type
	 */
	public InputAppendix(Bacteria host, int inputNeuronConnections) {
		super(host, inputNeuronConnections);
	}

	/**
	 * Gets the inputs needed for the InputAppendix type
	 */
	public abstract List<Double> getInputs();

}
