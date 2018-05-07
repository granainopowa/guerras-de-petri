package es.granainopowa.guerras_de_petri.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * @author Rafael Jiménez (3 may. 2018)
 *
 */
public class DrawUtils {

	public static void drawArrow(Graphics2D g2d, AffineTransform bacteriaTransform, Point2D origin, Point2D head) {
		Line2D line = new Line2D.Double(origin, head);
		g2d.draw(bacteriaTransform.createTransformedShape(line));
		drawArrowHead(g2d, bacteriaTransform, origin, head);
	}
	
	private static void drawArrowHead(Graphics2D g2, AffineTransform bacteriaTransform, Point2D origin, Point2D head) {
		double dy = head.getY() - origin.getY();
		double dx = head.getX() - origin.getX();
		double theta = Math.atan2(dy, dx);
		// System.out.println("theta = " + Math.toDegrees(theta));
		double phi = Math.toRadians(20);
		double x, y, rho = theta + phi;
		for (int j = 0; j < 2; j++) {
			x = head.getX() - 10 * Math.cos(rho);
			y = head.getY() - 10 * Math.sin(rho);
			g2.draw(bacteriaTransform.createTransformedShape(new Line2D.Double(head.getX(), head.getY(), x, y)));
			rho = theta - phi;
		}
	}

}
