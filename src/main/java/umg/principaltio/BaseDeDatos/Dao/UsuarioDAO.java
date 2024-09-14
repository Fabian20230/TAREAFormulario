package umg.principaltio.BaseDeDatos.Dao;

import umg.principaltio.BaseDeDatos.Model.Usuario;
import umg.principaltio.BaseDeDatos.conexion.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean correoExiste(String correo) {
        String query = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si hay al menos 1 registro, entonces el correo ya existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Verificar si el correo existe en otros usuarios excepto el actual
    public boolean correoExisteExceptoId(String correo, int idUsuario) {
        String query = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ? AND idusuario != ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            stmt.setInt(2, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si hay al menos 1 registro, entonces el correo ya existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Obtener el correo actual del usuario en base a su id
    public String getCorreoById(int idUsuario) {
        String query = "SELECT correo FROM tb_usuarios WHERE idusuario = ?";
        String correo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                correo = rs.getString("correo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correo;
    }

    // Actualizar el usuario, pero solo verificar el correo si ha cambiado
    public boolean updateUsuario(Usuario usuario) {
        // Obtener el correo actual del usuario
        String correoActual = getCorreoById(usuario.getIdUsuario());

        // Si el correo ha cambiado, verificar que no exista en otro usuario
        if (!usuario.getCorreo().equals(correoActual) && correoExiste(usuario.getCorreo())) {
            System.out.println("El correo ya existe. No se puede actualizar el usuario.");
            return false;
        }

        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());
            stmt.setInt(7, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public Usuario getUsuarioById(int idUsuario) {
        Usuario usuario = null;
        String query = "SELECT * FROM tb_usuarios WHERE idusuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("carne"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("seccion"),
                        rs.getLong("telegramid"),
                        rs.getString("activo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM tb_usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("carne"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("seccion"),
                        rs.getLong("telegramid"),
                        rs.getString("activo")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean insertUsuario(Usuario usuario) {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public boolean updateUsuario(Usuario usuario) {
//        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, usuario.getCarne());
//            stmt.setString(2, usuario.getNombre());
//            stmt.setString(3, usuario.getCorreo());
//            stmt.setString(4, usuario.getSeccion());
//            stmt.setLong(5, usuario.getTelegramId());
//            stmt.setString(6, usuario.getActivo());
//            stmt.setInt(7, usuario.getIdUsuario());
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    public boolean deleteUsuario(int idUsuario) {
        String query = "DELETE FROM tb_usuarios WHERE idusuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
