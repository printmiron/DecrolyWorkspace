
import java.util.ArrayList;

public class CuentaBancaria {

    // Atributos
    private String iban;
    private Cliente cliente;
    private double saldo;
    private ArrayList<Movimiento> movimientos;
    private int nElementosActuales;

    // Constructor
    public CuentaBancaria(String nombre, String dni, String fechaNacimiento, String telefono, String email,
            String direccion, String iban, double saldo, ArrayList<Movimiento> movimientos,
            int nElementosActuales) {
        this.iban = iban;
        this.cliente = new Cliente(nombre, dni, fechaNacimiento, telefono, email, direccion);
        this.saldo = saldo;
        this.movimientos = movimientos;
        this.nElementosActuales = nElementosActuales;
    }

    public String getIban() {
        return iban;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public int getnElementosActuales() {
        return nElementosActuales;
    }

    public boolean ingresarDinero(Movimiento m1) throws AvisarHaciendaException {
        boolean isAdd = false;
        if (m1 != null) {
            movimientos.add(m1);
            this.nElementosActuales++;
            this.saldo += m1.getCantidad();

            if (m1.getCantidad() > 5000) {
                throw new AvisarHaciendaException(cliente, iban, "Ingreso: " + m1.getCantidad());
            }

            isAdd = true;
        }
        return isAdd;
    }

    public boolean retirarDinero(Movimiento m1) throws Exception {
        boolean isRemove = false;

        if (m1 != null) {
            double sladoMenos50 = this.saldo - m1.getCantidad();

            if (sladoMenos50 < -50) {
                throw new CuentaException("Error: No puedes retirar esta cantidad, el saldo no puede ser menor a -50.");
            }
            movimientos.add(m1);
            this.nElementosActuales++;
            this.saldo = sladoMenos50;
            isRemove = true;

            if (this.saldo < 0) {
                System.out.println("Aviso: La cantidad de tu saldo es 0 o menos.");
            }

            if (m1.getCantidad() > 5000) {
                throw new AvisarHaciendaException(cliente, iban, "Retiro: " + m1.getCantidad());
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
