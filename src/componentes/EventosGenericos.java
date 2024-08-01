package componentes;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;


import componentes.JtextPersonalizado;

public class EventosGenericos {
	
	public static void estadosJtexField(Component component, boolean estado) {
		if(component instanceof JtextPersonalizado) {
			JtextPersonalizado jtextFieldPersonalizado = ((JtextPersonalizado) component);
			jtextFieldPersonalizado.setEnabled(estado);
		}else {
			if(component instanceof Container) {
				for(Component c : ((Container) component).getComponents()) {
					estadosJtexField(c, estado);
				}
			}
		}
	}
	
	public static void limpiarJtexField(Component component) {
		if(component instanceof JtextPersonalizado) {
			JtextPersonalizado jtextPersonalizado = ((JtextPersonalizado) component);
			jtextPersonalizado.setText("");;
		}else {
			if(component instanceof Container) {
				for(Component c : ((Container) component).getComponents()) {
					limpiarJtexField(c);
				}
			}
		}
	}
	
	public static void moverConEnter(KeyEvent e, Component component) {
		if(e.getKeyChar()==KeyEvent.VK_ENTER) component.requestFocus();
	}

}
