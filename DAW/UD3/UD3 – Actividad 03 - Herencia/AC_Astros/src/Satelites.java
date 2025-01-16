
import java.util.List;

public class Satelites extends Astros {

    private double distancia_al_planeta;
    private double orbita_planetaria;
    private int planeta_al_que_pertenece;
    private List<Satelites> satelitesRegistrados;

    public Satelites(double radio_ecuatorial, double rotacion_sobre_su_eje, double temperatura_media, double gravedad,
            double distancia_al_planeta, double orbita_planetaria) {
        super(radio_ecuatorial, rotacion_sobre_su_eje, temperatura_media, gravedad);
        this.distancia_al_planeta = distancia_al_planeta;
        this.orbita_planetaria = orbita_planetaria;

    }

    public void mostrarInfoSatelites() {
        super.toString();
        System.out.println("Distancia aal planeta: " + this.distancia_al_planeta);
        System.out.println("Orbita planetaria: " + this.orbita_planetaria);
        System.out.println("Planeta al que pertenece: " + this.planeta_al_que_pertenece);

    }

    public double getDistancia_al_planeta() {
        return this.distancia_al_planeta;
    }

    public double getOrbita_planetaria() {
        return this.orbita_planetaria;
    }

    public int getPlaneta_al_que_pertenece() {
        return this.planeta_al_que_pertenece;
    }

}
