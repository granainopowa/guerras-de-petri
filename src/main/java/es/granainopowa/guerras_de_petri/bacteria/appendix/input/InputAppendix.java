package es.granainopowa.guerras_de_petri.bacteria.appendix.input;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;

/**
 * <h1>This is being used for input appendices of bacterias.</h1>
 * <p>
 * Each one should provide an input to the network
 * </p>
 *
 * @author Rafael Jiménez (30 abr. 2018)
 */
public abstract class InputAppendix {

	private Bacteria host;

	protected InputAppendix(Bacteria host) {
		this.host = host;
	}

	protected Bacteria getHost() {
		return host;
	}

	public abstract double getInput();
}
