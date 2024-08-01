package componentes;

import java.awt.Color; 
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JtextPersonalizado extends JTextField {

	public JtextPersonalizado() {
	 setFont(new Font("Helvetica", Font.BOLD, 11));
	 setDisabledTextColor(Color.DARK_GRAY);
	 
	}
	public void soloNumerosEnteros() {
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) e.consume();
			}
		});
	}
}
