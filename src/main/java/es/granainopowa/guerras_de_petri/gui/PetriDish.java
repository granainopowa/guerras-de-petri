package es.granainopowa.guerras_de_petri.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
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

	private static final int DISH_DIAMETER = 500;

	List<Bacteria> bacterias = new ArrayList<>();

	public PetriDish() {
		this.bacterias.add(new FirstTryBacteria());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		Dimension windowSize = getSize();

		drawPetriDish(g2d, windowSize);
		drawBacterias(g2d, windowSize);
	}

	private void drawBacterias(Graphics2D g2d, Dimension windowSize) {
		for (Bacteria bacteria : bacterias) {
			bacteria.draw(g2d, windowSize);
		}
	}

	private void drawPetriDish(Graphics2D g2d, Dimension windowSize) {
		double w = windowSize.getWidth();
		double h = windowSize.getHeight();

		Ellipse2D e = new Ellipse2D.Double(0, 0, DISH_DIAMETER, DISH_DIAMETER);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		AffineTransform at = AffineTransform.getTranslateInstance((w - DISH_DIAMETER) / 2, (h - DISH_DIAMETER) / 2);
		g2d.draw(at.createTransformedShape(e));
	}
}
