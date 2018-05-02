package es.granainopowa.guerras_de_petri.bacteria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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

	private int x;
	private int y;
	private int angle;
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

	public void draw(Graphics2D g2d, Dimension windowSize) {
		double w = windowSize.getWidth();
		double h = windowSize.getHeight();

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.red);
		AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h / 2);
		at.rotate(angle);

		Ellipse2D e = new Ellipse2D.Double(-BACTERIA_RADIUS, -BACTERIA_RADIUS, BACTERIA_DIAMETER, BACTERIA_DIAMETER);
		Line2D line = new Line2D.Double(0, 0, 0, -(BACTERIA_RADIUS + 20));

		g2d.draw(at.createTransformedShape(e));
		g2d.draw(at.createTransformedShape(line));
	}
}
