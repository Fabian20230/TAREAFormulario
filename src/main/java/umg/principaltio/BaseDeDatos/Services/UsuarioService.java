package umg.principaltio.BaseDeDatos.Services;

import umg.principaltio.BaseDeDatos.Dao.UsuarioDAO;
import umg.principaltio.BaseDeDatos.Model.Usuario;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario getUsuarioById(int idUsuario) {
        return usuarioDAO.getUsuarioById(idUsuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.getAllUsuarios();
    }

    public boolean addUsuario(Usuario usuario) {
        // Verificar si el correo ya existe antes de insertar
        if (usuarioDAO.correoExiste(usuario.getCorreo())) {
            System.out.println("Error: El correo ya está en uso.");
            return false;
        }
        return usuarioDAO.insertUsuario(usuario);
    }

    public boolean updateUsuario(Usuario usuario) {
        // Verificar si el correo ya existe en otro usuario solo si ha cambiado
        return usuarioDAO.updateUsuario(usuario);
    }

    public boolean deleteUsuario(int idUsuario) {
        return usuarioDAO.deleteUsuario(idUsuario);
    }
}
