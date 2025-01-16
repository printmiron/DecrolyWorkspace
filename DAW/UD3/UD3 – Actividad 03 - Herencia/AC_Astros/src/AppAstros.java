
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppAstros {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        String opcionMenu;

        List<Astros> astrosRegistrados = new ArrayList<>();

        do {
            System.out.println("1. Registrar Astro");
            System.out.println("2. Seleccionar un Astro y agregar Planeta/Satelite");
            System.out.println("3. Mostrar astros planetas y satelites");
            System.out.println("4. Salir");
            opcionMenu = sc.nextLine();

            switch (opcionMenu) {
                case "1":
                    //Introducir Astros

                    System.out.println("Introduce el radio ecuatorial del astro: ");
                    double radio_ecuatorial = sc.nextDouble();

                    System.out.println("Introduce la rotacion sobre su eje: ");
                    double rotacion_sobre_su_eje = sc.nextDouble();

                    System.out.println("Introduce la temperatura media: ");
                    double temperatura_media = sc.nextDouble();

                    System.out.println("Introduce su gravedad: ");
                    double gravedad = sc.nextDouble();

                    Planetas astro = new Planetas(
                            radio_ecuatorial, rotacion_sobre_su_eje, temperatura_media, gravedad, radio_ecuatorial, gravedad, false);
                    astrosRegistrados.add(astro);

                    System.out.println("Astro registrado!");

                    break;

                case "2":
                    //Selecionar un Astro y agregar una planeta y/o satelite
                    if (astrosRegistrados.isEmpty()) {
                        System.out.println("No hay astros");
                        break;
                    }

                    //monstrar todos los astros que hay registrados
                    for (int i = 0; i < astrosRegistrados.size(); i++) {
                        System.out.println((i + 1) + ". " + astrosRegistrados.get(i).toString());
                    }

                    System.out.println("Seleccione el id del astro: ");
                    int seleccionAstro = sc.nextInt();

                    Planetas astroSeleccionado = (Planetas) astrosRegistrados.get(seleccionAstro - 1);

                    System.out.print("Introduce la distancia al sol del planeta: ");
                    double distanciaSol = sc.nextDouble();

                    System.out.print("Introduce la orbita al sol: ");
                    double orbitaSol = sc.nextDouble();

                    System.out.print("Tiene satelites? (true o false): ");
                    boolean tieneSatelites = sc.nextBoolean();

                    Planetas planeta = new Planetas(
                            astroSeleccionado.getRadio_ecuatorial(),
                            astroSeleccionado.getRotacion_sobre_su_eje(),
                            astroSeleccionado.getTemperatura_media(),
                            astroSeleccionado.getGravedad(),
                            distanciaSol, orbitaSol, tieneSatelites);

                    if (tieneSatelites) {
                        System.out.println("Introduce la distancia del satilite al planeta: ");
                        double distancia_al_planeta = sc.nextDouble();

                        System.out.println("Introduce la orbita planetaria: ");
                        double orbita_planetaria = sc.nextDouble();

                        Satelites satelite = new Satelites(
                                planeta.getRadio_ecuatorial(),
                                planeta.getRotacion_sobre_su_eje(),
                                planeta.getTemperatura_media(),
                                planeta.getGravedad(),
                                distancia_al_planeta, orbita_planetaria);

                        System.out.println("Satelite registrado");

                    }

                    System.out.println("Planeta registrada");

                    break;

                case "3":

                    // mostrar astros planetas y satelites
                    if (astrosRegistrados.isEmpty()) {
                        System.out.println("No hay astros registrados.");
                    } else {
                        System.out.println("Lista de Astros:");
                        for (Astros a : astrosRegistrados) {
                            System.out.println(a.toString());
                        }
                    }

                    break;

                case "4":
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida, intenta de nuevo.");
            }
        } while (!"4".equals(opcionMenu));

        sc.close();

    }
}

