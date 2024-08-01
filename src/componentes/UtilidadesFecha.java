package componentes;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilidadesFecha {

    private static MaskFormatter formatter;
    private static DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public static MaskFormatter getFormato() {
        try {
            formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter('_');
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public static Date stringAFechaConFormato(String fecha) {
        DateFormat dateFormatInput = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return dateFormatInput.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace(); 
            return null;
        }
    }



    public static Date stringAFecha(String fecha) {
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String fechaAString(Date fecha) {
        if (fecha == null) {
            return ""; 
        }
        return dateFormat.format(fecha);
    }
}
