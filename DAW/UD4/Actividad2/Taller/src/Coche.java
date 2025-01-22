public class Coche {

    private String color;
    private String marca;
    
    public Coche(String color, String marca) {
        this.color = color;
        this.marca = marca;
    }

    public String getColor() {
        return this.color;
    }

    public String getMarca() {
        return this.marca;
    }

    @Override
    public String toString() {
        return "Coche [color=" + color + ", marca=" + marca + "]";
    }

}
