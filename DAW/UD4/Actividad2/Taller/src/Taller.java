import java.util.HashMap;

public class Taller {

    private HashMap<String, Coche> cochesEnTaller;
    private int indiceActual;

    public Taller(HashMap<String, Coche> cochesEnTaller) {
        this.cochesEnTaller = cochesEnTaller;
        this.indiceActual = 0;
    }

    public boolean anadeElemento(String matricula, Coche coche){
        if (cochesEnTaller.containsKey(matricula)) {
            
        }
        return true;
    }

    
 
    
   
    
    
}
