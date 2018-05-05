package es.granainopowa.guerras_de_petri;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import es.granainopowa.guerras_de_petri.gui.PetriDish;

/**
 * @author Rafael Jiménez (18 abr. 2018)
 */
public class GuerrasDePetriApplication extends JFrame {
	private static final long serialVersionUID = -3420476485861654160L;

	public GuerrasDePetriApplication() {
		initUI();
	}

	private void initUI() {

		setBackground(Color.black);
		add(new PetriDish());

		setSize(600, 600);

		setTitle("Guerras de Petri");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			GuerrasDePetriApplication ex = new GuerrasDePetriApplication();
			ex.setVisible(true);
		});
	}
}
