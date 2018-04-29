package es.granainopowa.guerras_de_petri.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * @author Rafael Jiménez
 * 29 abr. 2018
 *
 */
public class PetriDish extends JPanel {
	private static final long serialVersionUID = -6727865483084967707L;

	private static final int DISH_DIAMETER = 500;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawDonut(g);
	}

	private void drawDonut(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		Dimension size = getSize();
		double w = size.getWidth();
		double h = size.getHeight();

		Ellipse2D e = new Ellipse2D.Double(0, 0, DISH_DIAMETER, DISH_DIAMETER);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);

		AffineTransform at = AffineTransform.getTranslateInstance((w - DISH_DIAMETER) / 2, (h - DISH_DIAMETER) / 2);
		g2d.draw(at.createTransformedShape(e));
	}
}
