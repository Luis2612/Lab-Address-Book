import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion = 0;

        while (opcion != 9) {
            System.out.println();
            System.out.println("=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar todos los contactos");
            System.out.println("5. Agregar grupo");
            System.out.println("6. Agregar contacto a grupo");
            System.out.println("7. Listar grupos");
            System.out.println("8. Mostrar contactos por grupo");
            System.out.println("9. Salir");
            System.out.println("==========================");
            System.out.print("Elige una opción: ");

            String entrada = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("--- AGREGAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine().trim();
                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();

                    Contacto nuevo = new Contacto(nombre, telefono, email);
                    agenda.agregarContacto(nuevo);
                    break;

                case 2:
                    System.out.println("--- BUSCAR CONTACTO ---");
                    System.out.print("Nombre del contacto: ");
                    String nombreBuscar = scanner.nextLine().trim();

                    Contacto encontrado = agenda.buscarContacto(nombreBuscar);
                    if (encontrado != null) {
                        System.out.println("Contacto encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró ningún contacto con el nombre '" + nombreBuscar + "'");
                    }
                    break;

                case 3:
                    System.out.println("--- ELIMINAR CONTACTO ---");
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine().trim();

                    if (agenda.eliminarContacto(nombreEliminar)) {
                        System.out.println("Contacto '" + nombreEliminar + "' eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún contacto con el nombre '" + nombreEliminar + "'");
                    }
                    break;

                case 4:
                    System.out.println("--- LISTA DE CONTACTOS ---");
                    agenda.listarContactos();
                    break;

                case 5:
                    System.out.println("--- AGREGAR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine().trim();
                    agenda.agregarGrupo(nombreGrupo);
                    break;

                case 6:
                    System.out.println("--- AGREGAR CONTACTO A GRUPO ---");
                    System.out.print("Nombre del contacto: ");
                    String contactoGrupo = scanner.nextLine().trim();
                    System.out.print("Nombre del grupo: ");
                    String grupoDestino = scanner.nextLine().trim();
                    agenda.agregarContactoAGrupo(contactoGrupo, grupoDestino);
                    break;

                case 7:
                    System.out.println("--- LISTA DE GRUPOS ---");
                    agenda.listarGrupos();
                    break;

                case 8:
                    System.out.println("--- CONTACTOS POR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String grupoMostrar = scanner.nextLine().trim();
                    agenda.mostrarContactosPorGrupo(grupoMostrar);
                    break;

                case 9:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Elige una opción del 1 al 9.");
                    break;
            }
        }

        scanner.close();
    }
}
