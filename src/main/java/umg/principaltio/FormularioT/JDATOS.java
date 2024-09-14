package umg.principaltio.FormularioT;

import umg.principaltio.BaseDeDatos.Model.Datos;
import umg.principaltio.BaseDeDatos.Services.DatosService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDATOS {


    private JPanel Panel;
    private JTextField textFieldCodigo;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFechaNacimiento;
    private JButton CREARButton;
    private JButton CARGARButton;
    private JButton ACTUALIZARButton;
    private JButton ELIMINARButton;
    private JButton REGRESARButton;


    private Datos Datos;
    private DatosService DatosService;

public JDATOS() {

DatosService = new DatosService();
Datos = new Datos(0, "", "", "", null);

    CREARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Datos.setNombre(textFieldNombre.getText());
            Datos.setApellido(textFieldApellido.getText());
            Datos.setDepartamento(textFieldDepartamento.getText());
            Datos.setFechaNacimiento(java.sql.Date.valueOf(textFieldFechaNacimiento.getText()));

            try {
                DatosService.agregarDatos(Datos);
                JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al ingrresar los datos: " + ex.getMessage());
            }
        }
    });

    CARGARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idDatos = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());

            try {
                Datos UsuarioEncontrado = DatosService.obtenerDatosPorCodigo(idDatos);
                if (UsuarioEncontrado != null) {
                    textFieldCodigo.setText(String.valueOf(UsuarioEncontrado.getCodigo()));
                    textFieldNombre.setText(UsuarioEncontrado.getNombre());
                    textFieldApellido.setText(UsuarioEncontrado.getApellido());
                    textFieldDepartamento.setText(UsuarioEncontrado.getDepartamento());
                    textFieldFechaNacimiento.setText(String.valueOf(UsuarioEncontrado.getFechaNacimiento()));

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar el usuario: " + ex.getMessage());
            }
        }
    });

    ACTUALIZARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idDatos = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());
            Datos.setNombre(textFieldNombre.getText());
            Datos.setApellido(textFieldApellido.getText());
            Datos.setDepartamento(textFieldDepartamento.getText());
            Datos.setFechaNacimiento(java.sql.Date.valueOf(textFieldFechaNacimiento.getText()));
            Datos.setCodigo(idDatos);
            try {
                DatosService.actualizarDatos(Datos);
                JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + ex.getMessage());
            }
        }
    });

    ELIMINARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idDatos = textFieldCodigo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo.getText());

            if (idDatos == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un código válido para eliminar.");
                return;
            }

            try {
                DatosService.eliminarDatos(idDatos);
                JOptionPane.showMessageDialog(null, "Datos eliminados exitosamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar los datos: " + ex.getMessage());
            }
        }
    });

    REGRESARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("frmFormularioTarea");
            frame.setContentPane(new frmFormularioTarea().getPEGRILOSO());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            SwingUtilities.getWindowAncestor(Panel).dispose();
        }
    });




}







    public static void main(String[] args) {
        JFrame frame = new JFrame("JDATOS");
        frame.setContentPane(new JDATOS().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


