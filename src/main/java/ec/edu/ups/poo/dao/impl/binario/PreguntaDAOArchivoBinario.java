package ec.edu.ups.poo.dao.impl.binario;

import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de PreguntaDAO que almacena las preguntas de seguridad en un archivo binario.
 * Permite cargar, guardar, listar y modificar preguntas de seguridad persistidas en disco.
 */
public class PreguntaDAOArchivoBinario implements PreguntaDAO {
    private final List<Pregunta> preguntas = new ArrayList<>();
    private final String rutaArchivo;

    /**
     * Constructor de PreguntaDAOArchivoBinario.
     * Inicializa la ruta del archivo y carga las preguntas desde el archivo binario.
     * Si el archivo está vacío, agrega preguntas por defecto y las guarda.
     *
     * @param rutaArchivo Ruta del archivo binario donde se almacenan las preguntas.
     */
    public PreguntaDAOArchivoBinario(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        crearArchivoSiNoExiste();
        cargarPreguntas();
        if (preguntas.isEmpty()) {
            agregarPreguntasPorDefecto();
            guardarPreguntas();
        }
    }

    /**
     * Crea el archivo de preguntas si no existe en la ruta especificada.
     * No recibe parámetros ni retorna valores.
     */
    private void crearArchivoSiNoExiste() {
        File f = new File(rutaArchivo);
        if (!f.exists()) {
            try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Carga las preguntas almacenadas en el archivo binario a la lista interna.
     * No recibe parámetros ni retorna valores.
     */
    private void cargarPreguntas() {
        preguntas.clear();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) return;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivo))) {
            while (dis.available() > 0) {
                int id = dis.readInt();
                String texto = dis.readUTF();
                preguntas.add(new Pregunta(id, texto));
            }
        } catch (EOFException eof) {
            // fin de archivo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda todas las preguntas de la lista interna en el archivo binario.
     * No recibe parámetros ni retorna valores.
     */
    private void guardarPreguntas() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo, false))) {
            for (Pregunta pregunta : preguntas) {
                dos.writeInt(pregunta.getId());
                dos.writeUTF(pregunta.getTexto());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega preguntas de seguridad por defecto a la lista interna.
     * No recibe parámetros ni retorna valores.
     */
    private void agregarPreguntasPorDefecto() {
        preguntas.add(new Pregunta(1, "pregunta.libro_favorito"));
        preguntas.add(new Pregunta(2, "pregunta.deporte_favorito"));
        preguntas.add(new Pregunta(3, "pregunta.lugar_vacaciones"));
        preguntas.add(new Pregunta(4, "pregunta.apodo_infancia"));
        preguntas.add(new Pregunta(5, "pregunta.cancion_favorita"));
        preguntas.add(new Pregunta(6, "pregunta.heroe_infancia"));
        preguntas.add(new Pregunta(7, "pregunta.primer_concierto"));
        preguntas.add(new Pregunta(8, "pregunta.postre_favorito"));
        preguntas.add(new Pregunta(9, "pregunta.primer_auto"));
        preguntas.add(new Pregunta(10, "pregunta.nombre_mascota_actual"));
    }

    /**
     * Lista todas las preguntas de seguridad almacenadas.
     *
     * @return Lista de preguntas de seguridad.
     */
    @Override
    public List<Pregunta> listarTodas() {
        // Devuelve una copia para evitar modificación externa directa
        return new ArrayList<>(preguntas);
    }
}