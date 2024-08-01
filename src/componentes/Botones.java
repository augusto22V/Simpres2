package componentes;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Botones extends JButton {

	public Botones() {
	 setSize(100,80);
	 setForeground(Color.BLACK);
	 setHorizontalTextPosition(SwingConstants.CENTER);
	 setVerticalTextPosition(SwingConstants.BOTTOM);
	 setBorderPainted(false);
	 setBackground(Color.BLACK);	
	 setOpaque(true);
	 setFocusable(false);

	}
	public void setText(String string){
		setIcon(string);
		super.setText(string);
	}
	
	public void setIcon(String nombreIcono){
	URL url = Botones.class.getResource("/img/" + nombreIcono.toLowerCase() + ".png" );
	
	try {
		setIcon(new ImageIcon(url));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
