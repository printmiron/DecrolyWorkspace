package Module;

public enum Tipo {

    GRANDE,
    MEDIO,
    PEQUENO;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

}
