
import java.util.List;

public abstract class Astros {

    private static int contador = 0;

    private int id;
    private double radio_ecuatorial;
    private double rotacion_sobre_su_eje;
    private double temperatura_media;
    private double gravedad;
    private List<Astros> astrosRegistrados;
   

    public Astros(double radio_ecuatorial, double rotacion_sobre_su_eje, double temperatura_media, double gravedad) {
        this.id = ++contador;
        this.radio_ecuatorial = radio_ecuatorial;
        this.rotacion_sobre_su_eje = rotacion_sobre_su_eje;
        this.temperatura_media = temperatura_media;
        this.gravedad = gravedad;
    }

    public int getId() {
        return this.id;
    }

    public double getRadio_ecuatorial() {
        return this.radio_ecuatorial;
    }

    public double getRotacion_sobre_su_eje() {
        return this.rotacion_sobre_su_eje;
    }

    public double getTemperatura_media() {
        return this.temperatura_media;
    }

    public double getGravedad() {
        return this.gravedad;
    }

    @Override
    public String toString() {
        return "Astros [id=" + id + ", radio_ecuatorial=" + radio_ecuatorial + ", rotacion_sobre_su_eje="
                + rotacion_sobre_su_eje + ", temperatura_media=" + temperatura_media + ", gravedad=" + gravedad + "]";
    }

    

}
