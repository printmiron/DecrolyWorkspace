public class AvisarHaciendaException extends Exception {

    private String cliente;
    private String iban;
    private String operacion;

    public AvisarHaciendaException(Libro cliente2, String iban, String operacion) {
            super("Aviso a hacienda. Cliente: " + cliente2 + ", Iban: " + iban + ", Operacion: " + operacion);
        this.cliente = cliente;
        this.iban = iban;
        this.operacion = operacion;
    }

    public String getCliente() {
        return this.cliente;
    }

    public String getIban() {
        return this.iban;
    }

    public String getOperacion() {
        return this.operacion;
    }

    

    

}
