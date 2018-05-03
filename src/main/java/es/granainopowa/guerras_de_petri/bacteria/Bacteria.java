package es.granainopowa.guerras_de_petri.bacteria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.neural_network.Network;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public abstract class Bacteria {
	private static final int BACTERIA_RADIUS = 25;
	private static final int BACTERIA_DIAMETER = 2 * BACTERIA_RADIUS;

	// Physical properties
	private int x;
	private int y;
	private int angle;
	private Color color;
	
	private Network network;
	private List<InputAppendix> inputAppendices;
	private List<OutputAppendix> outputAppendices;

	protected Bacteria(List<InputAppendix> inputAppendices, List<OutputAppendix> outputAppendices) {
		List<Integer> hiddenLayersNeuronCount = Arrays.asList(3, 4, 3);
		this.network = new Network(inputAppendices.size(), hiddenLayersNeuronCount, outputAppendices.size());
		this.inputAppendices = inputAppendices;
		this.outputAppendices = outputAppendices;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveToPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void step() {
		List<Double> inputs = new ArrayList<>();
		for (InputAppendix inputAppendix : inputAppendices) {
			inputs.add(inputAppendix.getInput(this));
		}
		network.computeNetwork(inputs);

		List<Double> networkOutputs = network.getNetworkOutputs();
		for (int i = 0; i < networkOutputs.size(); i++) {
			outputAppendices.get(i).react(this, networkOutputs.get(i));
		}
	}

	public final void draw(Graphics2D g2d, final AffineTransform affineTransform) {
		AffineTransform bacteriaTransform = new AffineTransform(affineTransform);
		bacteriaTransform.translate(x, y);
		bacteriaTransform.rotate(angle);

		if (color != null) {
			g2d.setColor(color);
		}

		Ellipse2D e = new Ellipse2D.Double(-BACTERIA_RADIUS, -BACTERIA_RADIUS, BACTERIA_DIAMETER, BACTERIA_DIAMETER);
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(bacteriaTransform.createTransformedShape(e));
		
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.black);
		
		Point2D center = new Point2D.Double(0, 0);
		Point2D arrowHead = new Point2D.Double(0, -BACTERIA_RADIUS);
		drawArrow(g2d, bacteriaTransform, center, arrowHead);
		
		drawComplements(g2d, bacteriaTransform);
	}
	
	/**
	 * Override this method if the Bacteria implementation needs to draw additional complements
	 *  
	 * @param bacteriaTransform AffineTransform tied to the center of the bacteria
	 */
	public void drawComplements(Graphics2D g2d, final AffineTransform bacteriaTransform) {
		
	}
	
	protected void drawArrow(Graphics2D g2d, final AffineTransform bacteriaTransform, Point2D origin, Point2D head) {
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.black);
		
		Line2D line = new Line2D.Double(origin, head);
		Line2D arrowLine = new Line2D.Double(head, new Point2D.Double(head.getX() + 5, head.getY() + 10));
		Line2D arrowLine2 = new Line2D.Double(head, new Point2D.Double(head.getX() - 5, head.getY() + 10));
		g2d.draw(bacteriaTransform.createTransformedShape(line));
		g2d.draw(bacteriaTransform.createTransformedShape(arrowLine));
		g2d.draw(bacteriaTransform.createTransformedShape(arrowLine2));
	}
}
