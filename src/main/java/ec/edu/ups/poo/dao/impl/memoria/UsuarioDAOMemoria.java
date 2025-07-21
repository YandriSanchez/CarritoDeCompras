package ec.edu.ups.poo.dao.impl.memoria;

import ec.edu.ups.poo.dao.UsuarioDAO;
import ec.edu.ups.poo.modelo.Pregunta;
import ec.edu.ups.poo.modelo.PreguntaUsuario;
import ec.edu.ups.poo.modelo.Rol;
import ec.edu.ups.poo.modelo.Usuario;
import ec.edu.ups.poo.excepciones.ContrasenaInvalidaException;
import ec.edu.ups.poo.excepciones.CedulaInvalidaException;

import java.util.*;

/**
 * Implementación en memoria del DAO de Usuario.
 * Permite gestionar usuarios, autenticar, crear, buscar, eliminar, actualizar y asignar preguntas de seguridad.
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    private final List<Usuario> usuarios;

    /**
     * Constructor de UsuarioDAOMemoria.
     * Inicializa la lista de usuarios y agrega usuarios de ejemplo con preguntas de seguridad.
     *
     * @param preguntas Lista de preguntas de seguridad disponibles.
     */
    public UsuarioDAOMemoria(List<Pregunta> preguntas) {
        usuarios = new ArrayList<>();

        String nombreAdmin = "Administrador General";
        Date fechaAdmin = new GregorianCalendar(1992, Calendar.MARCH, 22).getTime();
        String correoAdmin = "administrado@store.com";
        String telefonoAdmin = "0980604092";
        try {
            Usuario usuarioAdmin = new Usuario(
                    "1720882685",
                    "Admin@123",
                    Rol.ADMINISTRADOR,
                    nombreAdmin,
                    fechaAdmin,
                    correoAdmin,
                    telefonoAdmin
            );
            List<PreguntaUsuario> preguntasAdmin = new ArrayList<>();
            if (preguntas != null && preguntas.size() >= 3) {
                preguntasAdmin.add(new PreguntaUsuario(preguntas.get(0), "odisea"));
                preguntasAdmin.add(new PreguntaUsuario(preguntas.get(1), "ciclismo"));
                preguntasAdmin.add(new PreguntaUsuario(preguntas.get(2), "montañita"));
            }
            usuarioAdmin.setPreguntaValidacion(preguntasAdmin);
            usuarios.add(usuarioAdmin);
        } catch (ContrasenaInvalidaException | CedulaInvalidaException e) { }

        String nombreUser = "Usuario General";
        Date fechaUser = new GregorianCalendar(2003, Calendar.JULY, 12).getTime();
        String correoUser = "usuario@store.com";
        String telefonoUser = "0981402280";
        try {
            Usuario usuarioUser = new Usuario(
                    "1400403331",
                    "User@123",
                    Rol.USUARIO,
                    nombreUser,
                    fechaUser,
                    correoUser,
                    telefonoUser
            );
            List<PreguntaUsuario> preguntasUser = new ArrayList<>();
            if (preguntas != null && preguntas.size() >= 3) {
                preguntasUser.add(new PreguntaUsuario(preguntas.get(0), "quijote"));
                preguntasUser.add(new PreguntaUsuario(preguntas.get(1), "futbol"));
                preguntasUser.add(new PreguntaUsuario(preguntas.get(2), "quito"));
            }
            usuarioUser.setPreguntaValidacion(preguntasUser);
            usuarios.add(usuarioUser);
        } catch (ContrasenaInvalidaException | CedulaInvalidaException e) { }
    }

    /**
     * Obtiene una instancia de Date a partir de año, mes y día.
     *
     * @param year Año.
     * @param month Mes (1-12).
     * @param day Día del mes.
     * @return Instancia de Date correspondiente.
     */
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Auténtica un usuario por nombre de usuario y contraseña.
     *
     * @param userName Nombre de usuario (cedula).
     * @param contrasena Contraseña.
     * @return Usuario autenticado si las credenciales son correctas, null en caso contrario.
     */
    @Override
    public Usuario autenticarUsuario(String userName, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula().equals(userName) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario si no existe previamente.
     *
     * @param usuario Usuario a crear.
     */
    @Override
    public void crearUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getCedula()) == null) {
            usuarios.add(usuario);
        }
    }

    /**
     * Busca un usuario por su nombre de usuario (cedula).
     *
     * @param username Nombre de usuario (cedula).
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarUsuario(String username) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su nombre de usuario (cedula).
     *
     * @param userName Nombre de usuario (cedula) a eliminar.
     */
    @Override
    public void eliminarUsuario(String userName) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getCedula().equals(userName)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza la contraseña y el rol de un usuario.
     *
     * @param userName Nombre de usuario (cedula).
     * @param contrasena Nueva contraseña.
     * @param rol Nuevo rol.
     */
    @Override
    public void actualizar(String userName, String contrasena, Rol rol) {
        Usuario usuario = buscarUsuario(userName);
        if (usuario != null) {
            try {
                usuario.setContrasena(contrasena);
            } catch (ContrasenaInvalidaException e) {
            }
            usuario.setRol(rol);
        }
    }

    /**
     * Lista todos los usuarios almacenados en memoria.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarUsuariosTodos() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Busca usuarios por su rol.
     *
     * @param rol Rol a buscar.
     * @return Lista de usuarios que tienen el rol especificado.
     */
    @Override
    public List<Usuario> buscarUsuariosPorRol(Rol rol) {
        List<Usuario> usuariosRol = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getRol() == rol) {
                usuariosRol.add(usuario);
            }
        }
        return usuariosRol;
    }

    /**
     * Actualiza todos los datos de un usuario.
     *
     * @param usuario Usuario con los datos actualizados.
     */
    @Override
    public void actualizarTodo(Usuario usuario) {
        actualizar(usuario.getCedula(), usuario.getContrasena(), usuario.getRol());
    }

    /**
     * Agrega preguntas de seguridad a un usuario.
     * Solo agrega preguntas que no estén ya presentes.
     *
     * @param cedula Cedula del usuario.
     * @param nuevasPreguntas Lista de nuevas preguntas a agregar.
     */
    @Override
    public void agregarPreguntasAUsuario(String cedula, List<PreguntaUsuario> nuevasPreguntas) {
        Usuario usuario = buscarUsuario(cedula);
        if (usuario != null) {
            List<PreguntaUsuario> preguntasExistentes = usuario.getPreguntaValidacion();
            for (PreguntaUsuario nuevaPregunta : nuevasPreguntas) {
                if (!preguntasExistentes.contains(nuevaPregunta)) {
                    preguntasExistentes.add(nuevaPregunta);
                }
            }
            usuario.setPreguntaValidacion(preguntasExistentes);
        }
    }
}