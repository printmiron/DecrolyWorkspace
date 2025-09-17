package Model;

public enum GeneroV {
    ACCION,
    AVENTURA,
    CATASTROFE,
    CIENCIA_FICCION,
    COMEDIA,
    DOCUMENTALES,
    DRAMA,
    FANTASIA;


    public static GeneroV fromString(String text) {
        for (GeneroV value : GeneroV.values()) {
            if (value.name().equalsIgnoreCase(text)) {
                return value;
            }
        }
        throw new IllegalArgumentException(text);
    }



}
