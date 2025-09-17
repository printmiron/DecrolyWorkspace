import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Gestion {
    public static void main(String[] args) throws Exception {
         Scanner sc = new Scanner(System.in);

         List<Trabajador> trabajadores = new LinkedList<>();
        List<GerenteDep> gerentes = new LinkedList<>();
        List<Director> director = new LinkedList<>();

        System.out.println("Introduce el nombre de la empresa: ");
        String nombre = sc.nextLine();

        System.out.println("Introduce el CIF de la empresa (EJ: A12345678): ");
        String cif = validarEntrada("[A-HJNP-SUVW][0-9]{8}");

        LocalDate fechafechaFundacion = LocalDate.now();

        Empresa e1 = new Empresa(nombre, cif, fechafechaFundacion);

        String opcion = null;
        do {
            System.out.println("1) Registrar trabajador en empresa");
            System.out.println("2) Mostrar información general de la empresa, con todos los trabajadores, gerentes y director");
            System.out.println("3) Mostrar el número de trabajadores actuales y el organigrama de la empresa");
            System.out.println("4) Mostrar información de un departamento");
            System.out.println("5) Eliminar trabajador de la empresa");
            System.out.println("6) Agenda Director");
            System.out.println("7) Salir de la aplicacion");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    //introducir tipo de trabajador
                    System.out.print("Quien qieres registrar | Director, Gerente o Trabajador ? ");
                    String tipo = sc.nextLine();

                    switch (tipo) {

                        case "Director":
                            System.out.println("Introduce el nombre del director: ");
                            String nombreDirector = sc.nextLine();

                            System.out.println("Introduce su fecha necimiento: ");
                            String fechaNacimientoDirector = sc.nextLine();

                            System.out.println("Introduce su dni (EJ: 12345678A): ");
                            String dniDirector = validarEntrada("^\\d{8}[A-Z]$");

                            System.out.println("Introduce su direccion: ");
                            String direccionDirector = sc.nextLine();

                            System.out.println("Introduce su numero de telefono: ");
                            String numeroTelDirector = sc.nextLine();

                            System.out.println("Esta el director fuera de oficina? ");
                            boolean fueraOficinaDirector = sc.nextBoolean();

                            System.out.println("Es reunido el director? ");
                            boolean reunidoDirector = sc.nextBoolean();

                            System.out.println("Director registrado bien!");

                            Director d1 = new Director(nombreDirector, fechaNacimientoDirector, dniDirector, direccionDirector, numeroTelDirector, reunidoDirector, fueraOficinaDirector);
                            director.add(d1);

                            break;

                        case "Gerente":
                            //entinedo que cada gerente tiene que ser repartido por cada uno de tres departmaentos y que cada gerente tiene sus numeros de trabajadores.
                            System.out.println("Introduce el nombre del gerente: ");
                            String nombreGerente = sc.nextLine();

                            System.out.println("Introduce su fecha necimiento: ");
                            String fechaNacimientoGerente = sc.nextLine();

                            System.out.println("Introduce su dni: ");
                            String dniGerente = validarEntrada("^\\d{8}[A-Z]$");

                            System.out.println("Introduce su direccion: ");
                            String direccionGerente = sc.nextLine();

                            System.out.println("Introduce su numero de telefono: ");
                            String numeroTelGerente = sc.nextLine();

                            System.out.println("Gerente registrado bien!");

                            GerenteDep g1 = new GerenteDep(nombreGerente, fechaNacimientoGerente, dniGerente, direccionGerente, 0, numeroTelGerente);
                            gerentes.add(g1);

                            break;
                        case "Trabajador":

                            System.out.println("Introduce el nombre del trabajador: ");
                            String nombreTrabajador = sc.nextLine();

                            System.out.println("Introduce su fecha necimiento: ");
                            String fechaNacimientoTrabajador = sc.nextLine();

                            System.out.println("Introduce su dni: ");
                            String dniTrabajador = validarEntrada("^\\d{8}[A-Z]$");

                            System.out.println("Introduce su direccion: ");
                            String direccionTrabajador = sc.nextLine();

                            System.out.println("Introduce su numero de socio (EJ: 1234567890): ");
                            String numeroSS = validarEntrada("^\\d{10}$");

                            System.out.println("Introduce email de empresa: ");
                            String emailEmpresa = sc.nextLine();

                            System.out.println("Introduce su salario: ");
                            String salario = sc.nextLine();

                            System.out.println("Introduce su departamento | Informatica,Gestion o Marketing: ");
                            String departamento = sc.nextLine();

                            System.out.println("Esta en oficina el trabajador? ");
                            boolean enOficina = sc.nextBoolean();

                            Trabajador t1 = new Trabajador(nombreTrabajador, fechaNacimientoTrabajador, dniTrabajador, direccionTrabajador, numeroSS, emailEmpresa, salario, departamento, enOficina);
                            trabajadores.add(t1);

                            break;

                    }
                case "2":
                    System.out.println("Infromacion general empresa: " + e1.toString());
                    System.out.println("Trabajadores: ");
                    for (Trabajador t : trabajadores) {
                        System.out.println(t.toString());
                    }
                    System.out.println("Gerentes: ");
                    for (GerenteDep g : gerentes) {
                        System.out.println(g.toString());
                    }
                    System.out.println("Director: ");
                    for (Director d : director) {
                        System.out.println(d.toString());
                    }
                    break;

                case "3":
                    System.out.println(e1.trabajadoresRegistrados.size());
                    break;

                case "4":
                    //Aqui solo enseño los trabajadores de cada departamento elegido, compruebo que el departamento que contiene el trabajador es el mismo con del departamento al elegirlo mas abajo
                    System.out.print("Monstrar info de un departamento | Informatica, Gestion o Marketing ? ");
                    String tipoDep = sc.nextLine();

                    String Informatica = null;
                    String Gestion = null;
                    String Marketing = null;

                    switch (tipoDep) {
                        case "Informatica":
                            System.out.println("Trabajadores de INFORMATICA: ");
                            for (Trabajador t : trabajadores) {
                                if (t.getDepartamento().equals(Informatica)) {
                                    System.out.println(t.toString());
                                }
                            }
                            break;
                        case "Gestion":
                            System.out.println("Trabajadores de GESTION: ");
                            for (Trabajador t : trabajadores) {
                                if (t.getDepartamento().equals(Gestion)) {
                                    System.out.println(t.toString());
                                }
                            }
                            break;
                        case "Marketing":
                        System.out.println("Trabajadores de MARKETING: ");
                            for (Trabajador t : trabajadores) {
                                if (t.getDepartamento().equals(Marketing)) {
                                    System.out.println(t.toString());
                                }
                            }
                            break;

                        default:

                    }

                case "5":
                    if (trabajadores.isEmpty()) {
                        System.out.println("No hay trabajadores!");
                    }

                    for (Trabajador t : trabajadores) {
                        System.out.println(t.toString());
                    }

                    System.out.println("Introduce el dni del trabajador que quieres eliminar: ");
                    String dniEliminar = sc.nextLine();

                    Trabajador trabajadorEliminar = null;
                    for (Trabajador t : e1.getTrabajadoresRegistrados()) {
                        if (trabajadores != null && t.getDni().equalsIgnoreCase(dniEliminar)) {
                            trabajadorEliminar = t;
                            break;
                        }
                    }
                    if (dniEliminar == null) {
                        System.out.println("Cliente no encontrado");
                        return;
                    }

                    if (trabajadorEliminar != null) {
                        boolean eliminar = e1.eliminarTrabajador(trabajadorEliminar);
                        if (eliminar) {
                            System.out.println("Trabajador eliminado bien!");
                        } else {
                            System.out.println("No se pudo eliminar el trabajador");
                        }
                    }
                    break;

                case "6":

                    break;

                case "7":
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no valido!");
            }
        } while (!"7".equals(opcion));

    }














    private static String validarEntrada(String regex) {
        Scanner sc = new Scanner(System.in);
        String entrada;
        Pattern pattern = Pattern.compile(regex);
        do {
            entrada = sc.nextLine().trim();
            if (!pattern.matcher(entrada).matches()) {
                System.out.println("Formato incorrecto, intenta de nuevo: ");
            }
        } while (!pattern.matcher(entrada).matches());
        return entrada;
    }




}

