package es.granainopowa.guerras_de_petri.bacteria.appendix;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;

/**
 * @author Rafael Jiménez (2 may. 2018)
 */
public abstract class BacteriaAppendix {

	private Bacteria host;
	private int neuronConnections;

	protected BacteriaAppendix(Bacteria host, int neuronConnections) {
		this.host = host;
		this.neuronConnections = neuronConnections;
	}

	/**
	 * @param g2d
	 *            Graphics used to draw the Appendix
	 * @param bacteriaTransform
	 *            transformation required for the Appendix to move its origin to be
	 *            relative to the Bacteria
	 */
	public abstract void draw(Graphics2D g2d, AffineTransform petriDishCenterTransform, AffineTransform bacteriaTransform);

	protected Bacteria getHost() {
		return host;
	}

	public final int getNeuronConnectionsCount() {
		return neuronConnections;
	}

}
