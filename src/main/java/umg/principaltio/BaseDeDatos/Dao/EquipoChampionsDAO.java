package umg.principaltio.BaseDeDatos.Dao;

import umg.principaltio.BaseDeDatos.Model.EquipoChampions;
import umg.principaltio.BaseDeDatos.conexion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoChampionsDAO {

    // Obtener todos los equipos
    public List<EquipoChampions> obtenerTodos() {
        List<EquipoChampions> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos_champions";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EquipoChampions equipo = new EquipoChampions( rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("estadio"),
                        rs.getInt("fundacion"),
                        rs.getString("entrenador"),
                        rs.getString("web_oficial"),
                        rs.getString("facebook"),
                        rs.getString("twitter"),
                        rs.getString("instagram"),
                        rs.getString("patrocinador_principal"),
                        rs.getTimestamp("creado_en")
                );

                equipos.add(equipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    // Agregar equipo
    public void agregarEquipo(EquipoChampions equipo) {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar equipo
    public void eliminarEquipo(int idEquipo) {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEquipo);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar equipo
    public void actualizarEquipo(EquipoChampions equipo) {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());
            pstmt.setInt(12, equipo.getIdEquipo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cargar equipo por ID
    public EquipoChampions cargarEquipoPorId(int idEquipo) throws SQLException {
        String sql = "SELECT * FROM equipos_champions WHERE id_equipo = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idEquipo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new EquipoChampions(
                            resultSet.getInt("id_equipo"),
                            resultSet.getString("nombre"),
                            resultSet.getString("pais"),
                            resultSet.getString("ciudad"),
                            resultSet.getString("estadio"),
                            resultSet.getInt("fundacion"),
                            resultSet.getString("entrenador"),
                            resultSet.getString("web_oficial"),
                            resultSet.getString("facebook"),
                            resultSet.getString("twitter"),
                            resultSet.getString("instagram"),
                            resultSet.getString("patrocinador_principal"),
                            resultSet.getTimestamp("creado_en")
                    );
                }
            }
        }
        return null;
    }
}
