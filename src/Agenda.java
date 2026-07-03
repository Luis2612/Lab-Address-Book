import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Agenda {

    private Map<String, Contacto> contactos;
    private Set<String> grupos;
    private Map<String, List<Contacto>> contactosPorGrupo;

    public Agenda() {
        this.contactos = new HashMap<>();
        this.grupos = new HashSet<>();
        this.contactosPorGrupo = new HashMap<>();
    }

    public void agregarContacto(Contacto c) {
        if (contactos.containsKey(c.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + c.getNombre() + "'");
        } else {
            contactos.put(c.getNombre(), c);
            System.out.println("Contacto agregado: " + c.getNombre());
        }
    }

    public Contacto buscarContacto(String nombre) {
        return contactos.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {
        if (contactos.containsKey(nombre)) {
            Contacto eliminado = contactos.remove(nombre);

            for (List<Contacto> listaGrupo : contactosPorGrupo.values()) {
                listaGrupo.remove(eliminado);
            }

            return true;
        }
        return false;
    }

    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        List<Contacto> listaOrdenada = new ArrayList<>(contactos.values());
        listaOrdenada.sort((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));

        for (Contacto c : listaOrdenada) {
            System.out.println(c);
        }
    }

    public void agregarGrupo(String nombreGrupo) {
        if (grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "ya existe.");
        } else {
            grupos.add(nombreGrupo);
            contactosPorGrupo.put(nombreGrupo, new ArrayList<>());
            System.out.println("Grupo '" + nombreGrupo + "' creado");
        }
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        Contacto contacto = contactos.get(nombreContacto);
        if (contacto == null) {
            System.out.println("Error: No se encontró el contacto '" + nombreContacto + "'");
            return;
        }

        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No se encontró el grupo '" + nombreGrupo + "'");
            return;
        }

        List<Contacto> listaGrupo = contactosPorGrupo.get(nombreGrupo);
        if (listaGrupo.contains(contacto)) {
            System.out.println("Error: El contacto '" + nombreContacto + "' ya está en el grupo '" + nombreGrupo + "'");
            return;
        }

        listaGrupo.add(contacto);
        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
    }

    public void listarGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos creados.");
            return;
        }

        System.out.println("Grupos disponibles:");
        for (String grupo : grupos) {
            System.out.println("- " + grupo);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No se encontró el grupo '" + nombreGrupo + "'");
            return;
        }

        List<Contacto> listaGrupo = contactosPorGrupo.get(nombreGrupo);

        if (listaGrupo.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos.");
            return;
        }

        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (Contacto c : listaGrupo) {
            System.out.println("- " + c);
        }
    }
}
