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
            System.out.println("Coche con esa matricula ya existe");
            return false;
        }else{
            cochesEnTaller.put(matricula, coche);
            System.out.println("Coche registardo correcto");
            return true;
        }  
    }

    public void eliminaElemento(String matricula){
        if (cochesEnTaller.containsKey(matricula)) {
            cochesEnTaller.remove(matricula);
            System.out.println("Coche eliminado");
        }else{
            System.out.println("No existe coche con este matricula");
        }
    }
    
    public void visualizaMatriculas(){
        if (cochesEnTaller.isEmpty()) {
            System.out.println("No hay coches en taller");
        }else{
            for (String matricula : cochesEnTaller.keySet()) {
                System.out.println(matricula);
            }
        }
    }
    
    public void visualizaCoches(){
        if (cochesEnTaller.isEmpty()) {
            System.out.println("No hay coches en taller");
        }else{
            for (Coche coches : cochesEnTaller.values()) {
                System.out.println(coches);
            }
        }
    }
    
    public void visualizaTaller(){
        if (cochesEnTaller.isEmpty()) {
            System.out.println("No hay coches en taller");
        }else{
            for (String matricula : cochesEnTaller.keySet()) {
                Coche coches = cochesEnTaller.get(matricula);
                System.out.println("Matricula =[" + matricula + "] " + "Coche=[" + coches + "]");
            }
        }
    }

}
