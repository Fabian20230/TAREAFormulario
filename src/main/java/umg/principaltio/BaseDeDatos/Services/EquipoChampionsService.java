package umg.principaltio.BaseDeDatos.Services;

import umg.principaltio.BaseDeDatos.Dao.EquipoChampionsDAO;
import umg.principaltio.BaseDeDatos.Model.EquipoChampions;

import java.sql.SQLException;
import java.util.List;

public class EquipoChampionsService {

    private EquipoChampionsDAO equipoDAO = new EquipoChampionsDAO();

    // Obtener todos los equipos
    public List<EquipoChampions> obtenerTodosLosEquipos() {
        return equipoDAO.obtenerTodos();
    }

    // Agregar un nuevo equipo
    public void agregarNuevoEquipo(EquipoChampions equipo) {
        equipoDAO.agregarEquipo(equipo);
    }

    // Eliminar un equipo por ID
    public void eliminarEquipo(int idEquipo) throws SQLException {
        equipoDAO.eliminarEquipo(idEquipo);
    }

    // Actualizar un equipo existente
    public void actualizarEquipo(EquipoChampions equipo) throws SQLException {
        equipoDAO.actualizarEquipo(equipo);
    }

    // Cargar un equipo por ID
    public EquipoChampions cargarEquipoPorId(int idEquipo) throws SQLException {
        return equipoDAO.cargarEquipoPorId(idEquipo);
    }
}
