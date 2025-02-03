public class CuentaBancaria {

    // Atributos
    private String iban;
    private String titular;
    private double saldo;
    private Movimiento[] movimientos;
    private int nElementosActuales;

    // Constructor
    public CuentaBancaria(String iban, String titular, double saldo) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = saldo;
        this.movimientos = new Movimiento[100]; // Hasta 100 movimientos
        this.nElementosActuales = 0;
    }

    public Movimiento[] getMovimientos() {
        return this.movimientos;
    }

    public String getIBAN() {
        return this.iban;
    }

    public String getTitular() {
        return this.titular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public boolean ingresarDinero(Movimiento m1) {
        boolean isAdd = false;
        if (m1 != null) {
            this.movimientos[nElementosActuales] = m1;
            this.nElementosActuales++;
            this.saldo += m1.getCantidad();
            isAdd = true;
        }
        return isAdd;
    }

    public boolean retirarDinero(Movimiento m1) {
        boolean isRemove = false;
        if (m1 != null) {
            if (this.saldo - m1.getCantidad() < 0) {
                System.out.println("Aviso! La cantidad es 0");
            }
            if (this.saldo - m1.getCantidad() < -50) {
                System.out.println("La cantidad no puede ser inferior de -50");
            } else {
                this.movimientos[nElementosActuales] = m1;
                this.nElementosActuales++;
                this.saldo -= m1.getCantidad();
                isRemove = true;
            }
        }

        return isRemove;
    }

    public String infoCuentaBancaria() {
        return String.format("Cuenta - IBAN: %s, Titular: %s, Saldo: %.2f", iban, titular, saldo);
    }

    public String infoSaldo() {
        return String.format("Saldo disponible: %.2f", saldo);
    }
}
