package Module;

public enum Sexo {
    MASCULINO, FEMININO;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
