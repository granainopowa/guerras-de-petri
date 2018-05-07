package es.granainopowa.guerras_de_petri.bacteria.appendix.output;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;

/**
 * <h1>Paws are used to move the Bacteria</h1>
 *
 * It uses 2 network outputs:
 * <ol>
 * <li>Force used to move the bacteria (0 don't move, 1 move as fast as
 * possible)</li>
 * <li>Direction of the paw (converted to rank [-1, 1])</li>
 * </ol>
 * 
 * @author Rafael Jiménez (2 may. 2018)
 */
public class PawOutputAppendix extends OutputAppendix {
	private static final int DEFAULT_LENGTH = 25;
	private static final int OUTPUT_COUNT = 2;

	private int length = DEFAULT_LENGTH;
	private int angleBacteriaRelative;
	private double acelerationReaction;
	private double angleReaction;

	/**
	 * @param host
	 *            Bacteria that is linked to the paw
	 * @param outputProviders
	 * @param angleBacteriaRelative
	 *            angular position relative to the bacteria in degrees
	 */
	public PawOutputAppendix(Bacteria host, int angleBacteriaRelative) {
		super(host, OUTPUT_COUNT);
		this.angleBacteriaRelative = angleBacteriaRelative;
	}

	@Override
	public void draw(Graphics2D g2d, AffineTransform petriDishCenterTransform, AffineTransform bacteriaTransform) {
		AffineTransform pawTransform = getPawTransform(bacteriaTransform);

		Line2D line = new Line2D.Double(0, 0, 0, -length * acelerationReaction);
		g2d.draw(pawTransform.createTransformedShape(line));
	}

	private AffineTransform getPawTransform(AffineTransform bacteriaTransform) {
		AffineTransform pawTransform = new AffineTransform(bacteriaTransform);

		double radians = Math.toRadians(angleBacteriaRelative);
		pawTransform.rotate(radians);
		pawTransform.translate(0, -getHost().getRadius());
		// rotate from paw origin
		pawTransform.rotate(angleReaction);

		return pawTransform;
	}

	@Override
	public void reactToNetworkOutputs(List<Double> networkOutputs) {
		// first parameter is aceleration [0, 1]
		this.acelerationReaction = networkOutputs.get(0);
		double angle = networkOutputs.get(1);
		// convert [0, 1] to rank [-PI, PI]
		this.angleReaction = (angle - 0.5) * Math.PI;

		Bacteria host = getHost();
		double d = angleReaction + host.getAngleInRadians() + getAngleInRadians();

		double x = Math.sin(d) * acelerationReaction;
		double y = -Math.cos(d) * acelerationReaction;

		Point2D position = host.getPosition();
		Point2D finalPosition = new Point2D.Double(position.getX() + x, position.getY() + y);
		host.setPosition(finalPosition);
	}

	private double getAngleInRadians() {
		return Math.toRadians(angleBacteriaRelative);
	}

}
