package componentes;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonesJtoollbarPersonalizado extends JButton {

	public BotonesJtoollbarPersonalizado() throws HeadlessException {
		setForeground(Color.BLACK);

		setSize(70, 70);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setFocusable(false);
		setFont(new Font("Helvetica", Font.BOLD, 12));
		setMargin(new Insets(2, 25, 2, 25));
	}

	public void setText(String string) {
		setIcon(string);
		super.setText(string);
	}

	public void setIcon(String nombreIcono) {
		URL url = BotonesJtoollbarPersonalizado.class.getResource("/img/" + nombreIcono.toLowerCase() + ".png");
		if (url != null) {
			try {
				setIcon(new ImageIcon(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Icono no encontrado: " + nombreIcono);
		}
	}

}
