package ec.edu.ups.poo.dao.impl.memoria;

import ec.edu.ups.poo.dao.PreguntaDAO;
import ec.edu.ups.poo.modelo.Pregunta;
import ec.edu.ups.poo.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria del DAO de Pregunta.
 * Permite gestionar y listar preguntas de seguridad en memoria.
 */
public class PreguntaDAOMemoria implements PreguntaDAO {

    private final List<Pregunta> preguntas = new ArrayList<>();
    private MensajeInternacionalizacionHandler i18n;

    /**
     * Constructor de PreguntaDAOMemoria.
     * Inicializa el manejador de internacionalización y carga las preguntas de seguridad.
     *
     * @param i18n Manejador de internacionalización de mensajes.
     */
    public PreguntaDAOMemoria(MensajeInternacionalizacionHandler i18n) {
        this.i18n = i18n;
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
     * Lista todas las preguntas de seguridad almacenadas en memoria.
     *
     * @return Lista de preguntas de seguridad.
     */
    @Override
    public List<Pregunta> listarTodas() {
        return new ArrayList<>(preguntas);
    }
}