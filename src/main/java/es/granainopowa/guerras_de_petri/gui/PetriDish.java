package es.granainopowa.guerras_de_petri.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.FirstTryBacteria;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public class PetriDish extends JPanel {
	private static final long serialVersionUID = -6727865483084967707L;
	private static final int DISH_RADIUS = 300;
	private static final int DISH_DIAMETER = 2 * DISH_RADIUS;

	List<Bacteria> bacterias = new ArrayList<>();

	public PetriDish() {
		FirstTryBacteria bac1 = new FirstTryBacteria();
		FirstTryBacteria bac2 = new FirstTryBacteria();
		this.bacterias.add(bac1);
		this.bacterias.add(bac2);
		bac1.moveToPosition(100, 10);
		bac1.setColor(Color.green);
		
		bac2.moveToPosition(-100, -10);
		bac2.setAngle(-1);
		bac2.setColor(Color.red);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		Dimension windowSize = getSize();
		AffineTransform centerTransform = AffineTransform.getTranslateInstance(
				windowSize.getWidth() / 2,
				windowSize.getHeight() / 2);

		drawPetriDish(g2d, centerTransform);
		drawBacterias(g2d, centerTransform);

		// Only for testing purposes. Show the center :)
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		Line2D line = new Line2D.Double(0, 0, windowSize.getWidth(), windowSize.getHeight());
		g2d.draw(line);
		line = new Line2D.Double(0, windowSize.getHeight(), windowSize.getWidth(), 0);
		g2d.draw(line);
	}

	private void drawBacterias(Graphics2D g2d, AffineTransform affineTransform) {
		for (Bacteria bacteria : bacterias) {
			bacteria.draw(g2d, affineTransform);
		}
	}

	private void drawPetriDish(Graphics2D g2d, AffineTransform affineTransform) {
		Ellipse2D e = new Ellipse2D.Double(-DISH_RADIUS, -DISH_RADIUS, DISH_DIAMETER, DISH_DIAMETER);
		Ellipse2D eC = new Ellipse2D.Double(-10, -10, 20, 20);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		g2d.draw(affineTransform.createTransformedShape(e));
		g2d.draw(affineTransform.createTransformedShape(eC));
	}
}
