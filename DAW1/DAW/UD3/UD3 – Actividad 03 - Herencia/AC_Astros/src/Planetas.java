
import java.util.List;

public class Planetas extends Astros {

    private double distancia_al_sol;
    private double orbita_al_sol;
    private boolean tiene_satelites;
    private List<Planetas> planetasRegistrados;

    public Planetas(double radio_ecuatorial, double rotacion_sobre_su_eje, double temperatura_media, double gravedad,
            double distancia_al_sol, double orbita_al_sol, boolean tiene_satelites) {

        super(radio_ecuatorial, rotacion_sobre_su_eje, temperatura_media, gravedad);
        this.distancia_al_sol = distancia_al_sol;
        this.orbita_al_sol = orbita_al_sol;
        this.tiene_satelites = tiene_satelites;
    }

    public void mostrarInfoPlanetas() {
        super.toString();
        System.out.println("Distancia al sol " + this.distancia_al_sol);
        System.out.println("Orbita al sol: " + this.orbita_al_sol);
        System.out.println("Tiene satelites: " + this.tiene_satelites);

    }

    public double getDistancia_al_sol() {
        return this.distancia_al_sol;
    }

    public double getOrbita_al_sol() {
        return this.orbita_al_sol;
    }

    public boolean isTiene_satelites() {
        return this.tiene_satelites;
    }

    

}
