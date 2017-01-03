package cl.lillo.produccionmanzanos.Otros;

/**
 * Created by Alexi on 06/07/2016.
 */
public class QR {
    private static QR instance;

    // Global variable
    private String tipoQR;

    // Restrict the constructor from being instantiated
    private QR() {
    }

    public String getTipoQR() {
        return tipoQR;
    }

    public void setTipoQR(String tipoQR) {
        this.tipoQR = tipoQR;
    }

    public static synchronized QR getInstance() {
        if (instance == null) {
            instance = new QR();
        }
        return instance;
    }
}
