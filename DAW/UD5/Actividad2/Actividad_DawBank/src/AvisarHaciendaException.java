public class AvisarHaciendaException extends Exception {

    private String cliente;
    private String iban;
    private String operacion;

    public AvisarHaciendaException(String cliente, String iban, String operacion) {
            super("Aviso a hacienda. Cliente: " + cliente + ", Iban: " + iban + ", Operacion: " + operacion);
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
