package es.granainopowa.guerras_de_petri.bacteria.appendix.output;

import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.appendix.BacteriaAppendix;

/**
 * <h1>This is being used for output appendices of bacterias.</h1>
 * <p>
 * Each one should use one or more outputs of the network to decide how it
 * affects the behaviour of the bacteria, E.G. moving it through the Petri Dish
 * </p>
 *
 * @author Rafael Jiménez (30 abr. 2018)
 */
public abstract class OutputAppendix extends BacteriaAppendix {

	/**
	 * @param host
	 *            owner of the OutputAppendix
	 * @param outputCount
	 *            number of network outputs needed by the OutputAppendix type
	 */
	protected OutputAppendix(Bacteria host, int outputNeuronConnections) {
		super(host, outputNeuronConnections);
	}

	/**
	 * Makes a reaction according to the Bacteria given outputs.
	 *
	 * @param networkOutputs
	 *            reacts to the provided output
	 */
	public abstract void reactToNetworkOutputs(List<Double> networkOutputs);

}
