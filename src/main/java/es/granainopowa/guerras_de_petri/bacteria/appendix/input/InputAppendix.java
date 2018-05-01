package es.granainopowa.guerras_de_petri.bacteria.appendix.input;

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
public interface InputAppendix extends BacteriaAppendix {

	public double getInput(Bacteria host);
}
