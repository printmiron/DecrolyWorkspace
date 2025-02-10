import java.util.ArrayList;

public class CuentaBancaria {

    // Atributos
    private String iban;
    private String cliente;
    private double saldo;
    private ArrayList <Movimiento> movimientos;
    private int nElementosActuales;

    // Constructor
    public CuentaBancaria(String iban, String cliente, double saldo, ArrayList<Movimiento> movimientos,
            int nElementosActuales) {
        this.iban = iban;
        this.cliente = cliente;
        this.saldo = saldo;
        this.movimientos = movimientos;
        this.nElementosActuales = nElementosActuales;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public String getIBAN() {
        return this.iban;
    }

    public String getCliente() {
        return this.cliente;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public boolean ingresarDinero(Movimiento m1) {
        boolean isAdd = false;
        if (m1 != null) {
            movimientos.add(m1);
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
                movimientos.remove(m1);
                this.nElementosActuales--;
                this.saldo -= m1.getCantidad();
                isRemove = true;
            }
        }
        return isRemove;
    }

    public String infoCuentaBancaria() {
        return String.format("Cuenta - IBAN: %s, Cliente: %s, Saldo: %.2f", iban, cliente, saldo);
    }

    public String infoSaldo() {
        return String.format("Saldo disponible: %.2f", saldo);
    }
}
