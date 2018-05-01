package es.granainopowa.guerras_de_petri.bacteria.appendix.output;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.appendix.BacteriaAppendix;

/**
 * <h1>This is being used for output appendices of bacterias.</h1>
 * <p>
 * Each one should use an output of the network to decide how it affects the
 * behaviour of the bacteria, E.G. moving it through the Petri Dish
 * </p>
 *
 * @author Rafael Jiménez (30 abr. 2018)
 */
public interface OutputAppendix extends BacteriaAppendix {

	public void react(Bacteria host, Double output);
}
