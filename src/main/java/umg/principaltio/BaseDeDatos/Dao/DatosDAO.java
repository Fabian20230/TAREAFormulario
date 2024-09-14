package umg.principaltio.BaseDeDatos.Dao;

import umg.principaltio.BaseDeDatos.Model.Datos;
import umg.principaltio.BaseDeDatos.conexion.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosDAO {

    public void insertar(Datos datos) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, datos.getFechaNacimiento());
            statement.executeUpdate();
        }
    }

    public List<Datos> obtenerTodos() throws SQLException {
        List<Datos> listaDatos = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Datos datos = new Datos(
                        resultSet.getInt("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("departamento"),
                        resultSet.getDate("fecha_nacimiento")
                );
                listaDatos.add(datos);
            }
        }
        return listaDatos;
    }

    public Datos obtenerPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Datos(
                            resultSet.getInt("codigo"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("departamento"),
                            resultSet.getDate("fecha_nacimiento")
                    );
                }
            }
        }
        return null;
    }

    public void actualizar(Datos datos) throws SQLException {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, datos.getFechaNacimiento());
            statement.setInt(5, datos.getCodigo());
            statement.executeUpdate();
        }
    }

    public void eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }
}
