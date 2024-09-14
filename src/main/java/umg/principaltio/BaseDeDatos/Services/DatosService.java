package umg.principaltio.BaseDeDatos.Services;

import umg.principaltio.BaseDeDatos.Dao.DatosDAO;
import umg.principaltio.BaseDeDatos.Model.Datos;

import java.sql.SQLException;
import java.util.List;

public class DatosService {

    private DatosDAO datosDAO = new DatosDAO();

    public void agregarDatos(Datos datos) throws SQLException {
        validarFechaNacimiento(datos.getFechaNacimiento());
        datosDAO.insertar(datos);
    }

    public List<Datos> obtenerTodosLosDatos() throws SQLException {
        return datosDAO.obtenerTodos();
    }

    public Datos obtenerDatosPorCodigo(int codigo) throws SQLException {
        return datosDAO.obtenerPorCodigo(codigo);
    }

    public void actualizarDatos(Datos datos) throws SQLException {
        validarFechaNacimiento(datos.getFechaNacimiento());
        datosDAO.actualizar(datos);
    }

    public void eliminarDatos(int codigo) throws SQLException {
        datosDAO.eliminar(codigo);
    }

    private void validarFechaNacimiento(java.sql.Date fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.after(new java.sql.Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("La fecha de nacimiento no es válida.");
        }
    }
}
