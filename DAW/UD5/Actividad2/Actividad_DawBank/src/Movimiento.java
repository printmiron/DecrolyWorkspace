import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {

    private static int contador = 0;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private int id;
    private LocalDateTime fechaMovimiento;
    private String tipo;
    private double cantidad;

    public Movimiento(String tipo, double cantidad) {
        this.id = ++contador;
        this.fechaMovimiento = LocalDateTime.now();
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaMovimiento() {
        return this.fechaMovimiento;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getCantidad() {
        return this.cantidad;
    }

    public String mostrarInfoMovimiento() {
        return String.format("ID: %d, Fecha: %s, Tipo: %s, Cantidad: %.2f",
                id, fechaMovimiento.format(formatter), tipo, cantidad);
    }
}
