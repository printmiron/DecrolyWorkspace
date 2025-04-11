package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class VideoDaw {

    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    List <Articulo> articulosRegistrados = new LinkedList<>();
    List <Cliente> clientesRegistrados = new LinkedList<>();
    
    public VideoDaw(String cif, String direccion, LocalDate fechaAlta, List<Articulo> articulosRegistrados,
            List<Cliente> clientesRegistrados) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.articulosRegistrados = articulosRegistrados;
        this.clientesRegistrados = clientesRegistrados;
    }
    public String getCif() {
        return cif;
    }
    public String getDireccion() {
        return direccion;
    }
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public List<Articulo> getArticulosRegistrados() {
        return articulosRegistrados;
    }
    public List<Cliente> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public String mostrarInfoVideoClub() {
        return String.format("Informacion Video Club: CIF - %s, Direccion - %s, Fecha de Alta - %s", 
                              cif, direccion, fechaAlta);
    }

    public String mostrarArticulosRegistrados () {
        return String.format("Informacion articulos registrados: ", articulosRegistrados.toString());
    }

    public String  mostrarClientesRegistrados () {
        return String.format("Informacion clientes registrados: ", clientesRegistrados.toString());
    }

    public boolean alquilarPelicula(Pelicula p, Cliente c) {
        if (p != null && c != null && !p.isAlquilada()) {
            p.setIsAlquilada(true);
            p.setFechaAlquilier(LocalDateTime.now());
            System.out.println( p.getCod() + p.getTitulo() + " (pelicula) alquilada a " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean alquilarJuego(Videojuego v, Cliente c) {
        if (v != null && c != null && !v.isAlquilada()) {
            v.setIsAlquilada(true);
            v.setFechaAlquilier(LocalDateTime.now());
            System.out.println(v.getCod() + v.getTitulo() + " (videojuego) alquilada a " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean devolverPelicula(Pelicula p, Cliente c) throws TiempoExcendidoEx {
        if (p != null && c != null && p.isAlquilada()) {

            //verificar si ha pasado mas de 48 horas desde cuando fue alqilado el articulo
            Duration horasAlquilado = Duration.between(p.getFechaAlquilier(), LocalDateTime.now());
            if (horasAlquilado.compareTo(Duration.ofHours(48)) > 0) {
                throw new TiempoExcendidoEx("El tiempo maximo de alquilier (48h) ha superado para " + c.getDni() + c.getNombre());
            }

            p.setIsAlquilada(false);
            System.out.println( p.getCod() + p.getTitulo() + " (pelicula) devuelta por " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean devolverJuego(Videojuego v, Cliente c) throws TiempoExcendidoEx {
        if (v != null && c != null && v.isAlquilada()) {

            //verificar si ha pasado mas de 48 horas desde cuando fue alqilado el articulo
            Duration horasAlquilado = Duration.between(v.getFechaAlquilier(), LocalDateTime.now());
            if (horasAlquilado.compareTo(Duration.ofHours(48)) > 0) {
                throw new TiempoExcendidoEx("El tiempo maximo de alquilier (48h) ha superado para " + c.getDni() + c.getNombre());
            }

            v.setIsAlquilada(false);
            System.out.println(v.getCod() + v.getTitulo() + " (videojuego) devuelta por " + c.getNombre());
            return true;
        }
        return false;
    }

    public boolean darBajaCliente(Cliente c) {
            return clientesRegistrados.remove(c);
    }

    public boolean darBajaArticulo(Articulo a) {
        return articulosRegistrados.remove(a);
}

    public boolean registrarCliente(Cliente c) throws ClienteExisteEx {
            //verificar si cliente existe por dni
            for (Cliente clientes : clientesRegistrados) {
                if (clientes.getDni().equals(c.getDni())) {
                    throw new ClienteExisteEx("El cliente con dni: " + c.getDni() + " ya existe!");
                }
            }

        return clientesRegistrados.add(c);
    }
   


    









}
